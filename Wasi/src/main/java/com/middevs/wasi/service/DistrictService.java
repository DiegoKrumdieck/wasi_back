package com.middevs.wasi.service;

import com.middevs.wasi.repository.DistrictJpaRepository;
import com.middevs.wasi.repository.SearchJpaRepository;
import com.middevs.wasi.repository.UserJpaRepository;
import com.middevs.wasi.table.District;
import com.middevs.wasi.table.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {

    @Autowired
    DistrictJpaRepository districtRepo ;
    
    public District findById(Integer id) {
        return districtRepo.findById(id).orElse(null);
    }
    
    public District findByName(String name) {
    	return districtRepo.findByNameDistrict(name);
    }
}
