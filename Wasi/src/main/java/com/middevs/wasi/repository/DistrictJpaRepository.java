package com.middevs.wasi.repository;

import com.middevs.wasi.table.District;
import com.middevs.wasi.table.RealEstateImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictJpaRepository extends JpaRepository<District, Integer> {
    @Query("select d from District d where d.idDistrict = :idDistrict")
    District findByIdDistrict(Integer idDistrict);
    
    District findByNameDistrict(String nameDistrict);
}
