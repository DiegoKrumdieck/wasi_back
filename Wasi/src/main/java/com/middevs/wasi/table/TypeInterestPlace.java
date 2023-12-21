package com.middevs.wasi.table;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TYPES_INTEREST_PLACES")
@Setter
@Getter
public class TypeInterestPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTypeInterestPlace")
    private Integer idTypeInterestPlace;

    @ManyToMany(mappedBy = "listInterestSearch")
    private Set<Search> listTypeInterestSearch;

    @ManyToMany(mappedBy = "listEstateInteredDetail")
    private Set<RealEstate> listTypesInterestToRealEstate;

    @Column(name="nameTypeInterestPlace",length=25)
    private String nameTypeInterestPlace;

}
