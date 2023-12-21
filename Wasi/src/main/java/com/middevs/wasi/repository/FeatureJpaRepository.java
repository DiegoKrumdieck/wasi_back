package com.middevs.wasi.repository;

import com.middevs.wasi.table.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureJpaRepository extends JpaRepository<Feature, Integer> {
}
