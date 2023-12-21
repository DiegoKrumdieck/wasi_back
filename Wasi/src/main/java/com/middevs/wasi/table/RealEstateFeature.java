package com.middevs.wasi.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.middevs.wasi.id.RealEstateFeatureId;
import com.middevs.wasi.id.SearchDetailId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "REAL_ESTATE_FEATURE")
@IdClass(RealEstateFeatureId.class)
@Setter
@Getter
public class RealEstateFeature {
	@ManyToOne
	@Id
	@JoinColumn(name = "idRealEstate", insertable=false, updatable=false)
	private RealEstate realEstate;
	
	@ManyToOne
	@Id
	@JoinColumn(name = "idFeature")
	private Feature feature;
	
	@Column(name="sizeFeature")
	private Double sizeFeature;
}
