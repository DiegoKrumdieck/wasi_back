package com.middevs.wasi.table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;

    @ManyToMany(mappedBy = "listUserFavs")
    private Set<RealEstate> userFavs;

    @Column(name = "nameUser")
    private String nameUser;

    @Column(name = "emailUser")
    private String emailUser;

    @Column(name = "passwordUser")
    private String passwordUser;

    @Column(name = "dniUser")
    private Integer dniUser;

    @Column(name = "cellphoneUser")
    private Integer cellphoneUser;

}
