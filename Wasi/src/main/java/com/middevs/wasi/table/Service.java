package com.middevs.wasi.table;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SERVICE")
@Setter
@Getter
public class Service {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idService")
    private Integer idService;
	
	@Column(name="nameService")
	private String nameService; 
	
	@ManyToMany(mappedBy = "listServices")
	private Set<RealEstate> listRealEstates;

	@ManyToMany(mappedBy = "listSearchServicesDetail")
	private Set<Search> listServicesDetailSearch;
}
