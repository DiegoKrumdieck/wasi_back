package com.middevs.wasi.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONTACT_VENDOR_BUYER")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactVendorBuyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idContact")
    private Integer idContact;

    @ManyToOne
    @JoinColumn(name="idBuyer")
    private User idBuyer;

    @ManyToOne
    @JoinColumn(name="idRealEstate")
    private RealEstate idRealEstate;

    @Column(name="dateContact")
    private LocalDateTime dateContact;

    @Column(name="message",length=500)
    private String message;

	public ContactVendorBuyer(User idBuyer, RealEstate idRealEstate, LocalDateTime dateContact, String message) {
		if(idBuyer != null) {
			this.idBuyer = idBuyer;	
		}
		if(idRealEstate != null) {
			this.idRealEstate = idRealEstate;	
		}
		this.dateContact = dateContact;
		this.message = message;
	}
    
}
