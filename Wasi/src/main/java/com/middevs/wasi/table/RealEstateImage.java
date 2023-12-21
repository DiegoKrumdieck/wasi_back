package com.middevs.wasi.table;

import com.middevs.wasi.id.RealEstateImageId;
import com.middevs.wasi.id.SearchDetailId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "REAL_ESTATE_IMAGES")
@IdClass(RealEstateImageId.class)
@Setter
@Getter
public class RealEstateImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOrder")
    private Integer idOrder;

    @ManyToOne
    @Id
    @JoinColumn(name="idRealEstate")
    private RealEstate idRealEstate;

    @Column(name="imageURL",length=300)
    private String imageURL;

}
