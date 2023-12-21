package com.middevs.wasi.table;

import com.middevs.wasi.id.SearchDetailId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SEARCH_DETAILS")
@IdClass(SearchDetailId.class)
@Setter
@Getter
@NoArgsConstructor
public class SearchDetail {

    @ManyToOne
    @Id
    @JoinColumn(name = "idSearch")
    private Search search;

    @ManyToOne
    @Id
    @JoinColumn(name = "idRealEstate")
    private RealEstate realEstate;

    @Column(name="coincidenceRealState")
    private Integer coincidenceRealState;

	public SearchDetail(Search search, RealEstate realEstate, Integer coincidenceRealState) {
		this.search = search;
		this.realEstate = realEstate;
		this.coincidenceRealState = coincidenceRealState;
	}
    
    
}
