package com.middevs.wasi.table;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "FEATURE")
@Setter
@Getter
public class Feature {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idFeature")
    private Integer idFeature;
	
	@Column(name="nameFeature")
	private String nameFeature;

	@ManyToMany(mappedBy = "listFeaturesSearchDetail")
	private Set<Search> listSearchFeature;
}
