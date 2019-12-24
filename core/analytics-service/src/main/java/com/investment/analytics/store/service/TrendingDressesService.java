package com.investment.analytics.store.service;

import com.investment.analytics.store.repository.DressRepository;
import com.investment.analytics.store.view.DressDetailView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrendingDressesService {


    private final DressRepository dressRepository;

    @Transactional(readOnly = true)
    public List<DressDetailView> getTrending(int count, String interval) {
        return dressRepository.findTopNTrendingTimeInterval(count, interval)
                .stream().map(DressDetailView::map).collect(toList());
    }
}
