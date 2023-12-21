package com.middevs.wasi.service;

import com.middevs.wasi.repository.FeatureJpaRepository;
import com.middevs.wasi.repository.SearchJpaRepository;
import com.middevs.wasi.repository.ServiceJpaRepository;
import com.middevs.wasi.repository.TypeInterestPlaceJpaRepository;
import com.middevs.wasi.repository.TypeRealEstateJPARepository;
import com.middevs.wasi.table.Feature;
import com.middevs.wasi.table.TypeInterestPlace;
import com.middevs.wasi.table.TypeRealEstate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    ServiceJpaRepository serviceRepo;
    
    public List<com.middevs.wasi.table.Service> findByIds(List<Integer> ids) {
    	return serviceRepo.findAllById(ids);
    }
}
