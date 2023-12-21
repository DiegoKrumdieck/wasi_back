package com.middevs.wasi.id;

import java.io.Serializable;

import com.middevs.wasi.repository.RealEstateImageJpaRepository;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.Search;

import com.middevs.wasi.table.SearchDetail;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchDetailId implements Serializable{
	private Search search;
	private RealEstate realEstate;

	public SearchDetailId(){
	}
}
