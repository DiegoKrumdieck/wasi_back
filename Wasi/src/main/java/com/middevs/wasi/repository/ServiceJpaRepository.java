package com.middevs.wasi.repository;

import com.middevs.wasi.table.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceJpaRepository extends JpaRepository<Service, Integer> {
}
