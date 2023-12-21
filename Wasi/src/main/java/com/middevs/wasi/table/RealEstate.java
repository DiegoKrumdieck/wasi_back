package com.middevs.wasi.table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "REAL_ESTATE")
@Setter
@Getter
@NoArgsConstructor
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRealEstate")
    private Integer idRealEstate;

    @ManyToOne
    @JoinColumn(name="idUserVendor")
    private User idUserVendor;

    @ManyToOne
    @JoinColumn(name="idDistrict")
    private District idDistrict;

    @ManyToOne
    @JoinColumn(name = "idTypeRealEstate")
    private TypeRealEstate idTypeRealEstate;

    @ManyToMany
    @JoinTable(
            name = "REAL_ESTATE_USER_FAVS",
            joinColumns = @JoinColumn(name = "idRealEstate"),
            inverseJoinColumns = @JoinColumn(name = "idUser"))
    private Set<User> listUserFavs;

    @ManyToMany
    @JoinTable(
            name = "REAL_ESTATE_INTEREST_DETAIL",
            joinColumns = @JoinColumn(name = "idRealEstate"),
            inverseJoinColumns = @JoinColumn(name = "idTypeInterestPlace"))
    private Set<TypeInterestPlace> listEstateInteredDetail;

    @OneToMany(mappedBy = "realEstate",fetch = FetchType.EAGER)
    private Set<RealEstateFeature> listFeatures;
    
    @Column(name="priceRealEstate")
    private Double priceRealEstate;

    @Column(name="sizeRealEstate")
    private Double sizeRealEstate;

    @Column(name="numBedrooms")
    private Integer numBedrooms;

    @Column(name="descriptionRealEstate", columnDefinition = "TEXT")
    private String descriptionRealEstate;

    @Column(name="numBathrooms")
    private Integer numBathrooms;

    @Column(name="antiquityRealEstate")
    private Integer antiquityRealEstate;

    @Column(name="publishDate")
    private LocalDateTime publishDate;

    @Column(name="numFloors")
    private Integer numFloors;

    @Column(name="latitudeRealEstate")
    private String latitudeRealEstate;

    @Column(name="longitudeRealEstate")
    private String longitudeRealEstate;

    @Column(name="addressRealEstate",length=500)
    private String addressRealEstate;

    @ManyToMany
    @JoinTable(
            name = "REAL_ESTATE_SERVICE",
            joinColumns = @JoinColumn(name = "idRealEstate"),
            inverseJoinColumns = @JoinColumn(name = "idService"))
    private Set<Service> listServices;

	public RealEstate(Integer idRealEstate, String longitudeRealEstate, String latitudeRealEstate) {
		this.idRealEstate = idRealEstate;
		this.longitudeRealEstate = longitudeRealEstate;
		this.latitudeRealEstate = latitudeRealEstate;
	}
    
    
}
