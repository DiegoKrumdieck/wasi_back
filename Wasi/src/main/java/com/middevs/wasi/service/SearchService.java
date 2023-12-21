package com.middevs.wasi.service;

import com.middevs.wasi.repository.SearchJpaRepository;
import com.middevs.wasi.repository.UserJpaRepository;
import com.middevs.wasi.table.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    SearchJpaRepository searchJpaRepository;
    
    public Search findByIdSearch(int idSearch) {
        return searchJpaRepository.findById(idSearch).orElse(null);
    }
    
    public Search register(Search search) {
    	return searchJpaRepository.save(search);
    }
    
}
