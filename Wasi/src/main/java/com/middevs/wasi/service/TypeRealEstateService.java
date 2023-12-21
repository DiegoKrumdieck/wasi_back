package com.middevs.wasi.service;

import com.middevs.wasi.repository.SearchJpaRepository;
import com.middevs.wasi.repository.TypeRealEstateJPARepository;
import com.middevs.wasi.table.TypeRealEstate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeRealEstateService {

    @Autowired
    TypeRealEstateJPARepository typeRealEstateRepo;
    
    public TypeRealEstate findById(int id) {
    	return typeRealEstateRepo.findById(id).orElse(null);
    }
    
    public TypeRealEstate findByName(String name) {
    	return typeRealEstateRepo.findByNameTypeRealEstate(name);
    }
}
