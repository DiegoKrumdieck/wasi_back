package com.middevs.wasi.id;

import java.io.Serializable;

import com.middevs.wasi.table.Feature;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.Search;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RealEstateFeatureId implements Serializable{
	private RealEstate realEstate;
	private Feature feature;
}
