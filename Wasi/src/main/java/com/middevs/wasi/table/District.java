package com.middevs.wasi.table;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DISTRICTS")
@Setter
@Getter
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDistrict")
    private Integer idDistrict;

    @Column(name="nameDistrict", length = 70)
    private String nameDistrict;

}
