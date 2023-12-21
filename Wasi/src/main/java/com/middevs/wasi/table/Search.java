package com.middevs.wasi.table;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SEARCH")
@Setter
@Getter
@NoArgsConstructor
public class Search {
	@Id
	@Column(name="idSearch")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSearch;

	@ManyToOne
	@JoinColumn(name = "idDistrict")
	private District idDistrict;

	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	@ManyToOne
	@JoinColumn(name = "idTypeRealEstate")
	private TypeRealEstate idTypeRealEstate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "SEARCH_INTEREST_DETAIL",
			joinColumns = @JoinColumn(name = "idSearch"),
			inverseJoinColumns = @JoinColumn(name = "idTypeInterestPlace"))
	private Set<TypeInterestPlace> listInterestSearch;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "SEARCH_FEATURES_DETAIL",
			joinColumns = @JoinColumn(name = "idSearch"),
			inverseJoinColumns = @JoinColumn(name = "idFeature"))
	private Set<Feature> listFeaturesSearchDetail;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "SEARCH_SERVICES_DETAIL",
			joinColumns = @JoinColumn(name = "idSearch"),
			inverseJoinColumns = @JoinColumn(name = "idService"))
	private Set<Service> listSearchServicesDetail;

	@Column(name="dateSearch")
	private LocalDateTime dateSearch; 
	
	@Column(name="maxBudgetSearch")
	private Integer maxBudgetSearch;

	@Column(name="minBudgetSearch")
	private Integer minBudgetSearch;

	@Column(name="numBedrooms")
	private Integer numBedrooms;

	@Column(name="numBathrooms")
	private Integer numBathrooms;
	
	@Column(name="antiquityRealStateSearch")
	private Integer antiquityRealStateSearch;

	@OneToMany(mappedBy = "search",fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval=true)
    private Set<SearchDetail>searchDetails;
	
	public Search(District idDistrict, User user, TypeRealEstate idTypeRealEstate,
			Set<TypeInterestPlace> listInterestSearch, Set<Feature> listFeaturesSearchDetail,
			Set<Service> listSearchServicesDetail, LocalDateTime dateSearch, Integer maxBudgetSearch,
			Integer minBudgetSearch, Integer numBedrooms, Integer numBathrooms, Integer antiquityRealStateSearch) {
		if(idDistrict != null) {
			this.idDistrict = idDistrict;
		}
		if(user != null) {
			this.user = user;
		}
		if(idTypeRealEstate != null) {
			this.idTypeRealEstate = idTypeRealEstate;
		}
		if(listInterestSearch != null) {
			this.listInterestSearch = listInterestSearch;
		}
		
		if(listFeaturesSearchDetail != null) {
			this.listFeaturesSearchDetail = listFeaturesSearchDetail;
		}
		if(listSearchServicesDetail != null) {
			this.listSearchServicesDetail = listSearchServicesDetail;
		}
		this.dateSearch = dateSearch;
		if(maxBudgetSearch != null) {
			this.maxBudgetSearch = maxBudgetSearch;
		}
		if(minBudgetSearch != null) {
			this.minBudgetSearch = minBudgetSearch;
		}
		if(numBedrooms != null) {
			this.numBedrooms = numBedrooms;
		}
		if(numBathrooms != null) {
			this.numBathrooms = numBathrooms;
		}
		if(antiquityRealStateSearch != null) {
			this.antiquityRealStateSearch = antiquityRealStateSearch;
		}
	}
	
	
}
