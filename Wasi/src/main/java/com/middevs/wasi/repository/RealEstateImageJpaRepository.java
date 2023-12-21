package com.middevs.wasi.repository;

import com.middevs.wasi.table.RealEstateImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RealEstateImageJpaRepository extends JpaRepository<RealEstateImage, Integer> {
    @Query("select r from RealEstateImage r where r.idRealEstate.idRealEstate = :idRealEstate")
    List<RealEstateImage> findByIdRealEstate(Integer idRealEstate);
}
