package com.investment.analytics.store.repository;


import com.investment.analytics.store.domain.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer> {

    Optional<Brand> findByName(@Param("name") String name);

}
