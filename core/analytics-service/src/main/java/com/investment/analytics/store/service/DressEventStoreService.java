package com.investment.analytics.store.service;

import com.investment.analytics.DressStatus;
import com.investment.analytics.store.domain.Brand;
import com.investment.analytics.store.repository.BrandRepository;
import com.investment.analytics.store.repository.DressRepository;
import com.investment.analytics.store.repository.RatingRepository;
import com.investment.analytics.store.domain.Dress;
import com.investment.analytics.worker.event.DressEventType;
import com.investment.analytics.worker.event.DressMessageEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@Transactional
public class DressEventStoreService {


    @Autowired
    private DressRepository dressRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional
    public boolean apply(DressMessageEvent dressMessageEvent) {
        Dress dress = fromDressMessageEvent(
                dressMessageEvent, dressMessageEvent.getEventType() == DressEventType.CREATED);
        if (dress == null) {
            return false;
        }
        OptionalDouble averageRating = getAverageRating(dress.getUuid());
        dress.setAverageRating(averageRating.isPresent() ? (int) Math.round(averageRating.getAsDouble()) : 0);
        dressRepository.save(dress);
        return true;
    }

    private Dress fromDressMessageEvent(DressMessageEvent dressMessageEvent, boolean isCreate) {
        if (dressMessageEvent == null || dressMessageEvent.getPayloadKey() == null) {
            return null;
        }

        // is dress persisted due to a previous CREATE or UPDATE event?
        Optional<Dress> dressOptional = dressRepository.findById(dressMessageEvent.getPayloadKey());

        if (isCreate && dressOptional.isPresent()) {
            // handle out-of-order, CREATE message arrived after UPDATED message has already been processed, skip CREATE
            return null;
        }

        Dress dress = dressOptional.orElseGet(() -> new Dress(dressMessageEvent.getPayloadKey(), fromDressMessageEvent(dressMessageEvent.getPayload().getBrand())));
        dress.setStatus(isCreate ? DressStatus.CREATED : DressStatus.UPDATED);

        dress.setName(dressMessageEvent.getPayload().getName());
        dress.setSeason(dressMessageEvent.getPayload().getSeason());
        dress.setPrice(dressMessageEvent.getPayload().getPrice());
        dress.setColor(dressMessageEvent.getPayload().getColor());

        // any image thumbnails?
        if (dressMessageEvent.getPayload().getImages() != null) {
            dressMessageEvent.getPayload().getImages().forEach(
                    i -> {
                        if (dress.getThumbnails() == null) { // init
                            dress.setThumbnails(new ArrayList<>());
                        } else if (!isCreate && dress.getThumbnails() != null && !dress.getThumbnails().isEmpty()) {
                            // when updating dress, current message thumbs will overwrite old ones
                            dress.getThumbnails().clear();
                        }

                        dress.getThumbnails().add(i.getThumbUrl());
                    });
        }

        return dress;
    }

    private Brand fromDressMessageEvent(com.investment.analytics.worker.domain.Brand eventBrand) {
        if (eventBrand == null) {
            return null;
        }

        // does brand already exist?
        Optional<Brand> brandOptional = brandRepository.findByName(eventBrand.getName());
        // found one by unique name
        return brandOptional.orElseGet(() -> brandRepository.save(new Brand(eventBrand.getName(), eventBrand.getLogoUrl())));
    }

    /**
     * A mechanism to calculate the average for a dress based on the stored ratings and not relying on Postgres aggregate function
     * as in {@link RatingRepository#getAverageRating(String)}
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public OptionalDouble getAverageRating(String dressId) {
        List<Integer> allStars = ratingRepository.listStarsByDressId(dressId);

        return allStars.stream().mapToDouble(s -> s).average();
    }

}
