package com.middevs.wasi.repository;

import com.middevs.wasi.dto.ServiceRealEstateDTO;
import com.middevs.wasi.table.RealEstateImage;
import com.middevs.wasi.table.Service;
import com.middevs.wasi.table.TypeInterestPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RealEstateServiceRepository extends JpaRepository<Service, Integer> {

    @Query(value ="SELECT s.idService as idService,s.nameService as nameService FROM SERVICE s, REAL_ESTATE re, REAL_ESTATE_SERVICE res WHERE (re.idRealEstate = ?1) and (re.idRealEstate=res.idRealEstate) and (res.idService=s.idService);", nativeQuery = true)
    List<Service> findByIdRealEstate(Integer idRealEstate);
}
