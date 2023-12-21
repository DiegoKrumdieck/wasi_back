package com.middevs.wasi.table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TYPE_REAL_ESTATE")
@Setter
@Getter
public class TypeRealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTypeRealEstate")
    private Integer idTypeRealEstate;

    @Column(name="nameTypeRealEstate", length = 50)
    private String nameTypeRealEstate;
}
