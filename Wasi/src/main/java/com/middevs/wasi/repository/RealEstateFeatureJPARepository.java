package com.middevs.wasi.repository;

import com.middevs.wasi.table.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RealEstateFeatureJPARepository  extends JpaRepository<Feature, Integer> {
    @Query(value ="select f.idFeature as idFeature, f.nameFeature as nameFeature from FEATURE f left join REAL_ESTATE_FEATURE rea on rea.idFeature =f.idFeature left join REAL_ESTATE re on re.idRealEstate = rea.idRealEstate where re.idRealEstate = ?1 ;", nativeQuery = true)
    List<Feature> findByIdRealEstate(int idRealEstate);
}
