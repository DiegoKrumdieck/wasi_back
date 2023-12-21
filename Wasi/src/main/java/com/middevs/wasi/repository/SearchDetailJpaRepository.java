package com.middevs.wasi.repository;

import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.SearchDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SearchDetailJpaRepository extends JpaRepository<SearchDetail, Integer> {
    @Query("select r from RealEstate r inner join SearchDetail s on r.idRealEstate = s.realEstate.idRealEstate where s.search.idSearch = :idSearch")
    List<RealEstate> findRealStateByIdSearch(@Param("idSearch") int idSearch);


    @Query("select sd from SearchDetail sd where sd.search.idSearch = :idSearch and sd.realEstate.idRealEstate = :idRealEstate")
    SearchDetail findByIdSearchAndIdRealEstate(@Param("idSearch") int idSearch, @Param("idRealEstate") int idRealEstate);

    @Transactional
    @Modifying
    @Query(value = "delete from SEARCH_DETAILS sd where sd.idSearch = ?1 ;", nativeQuery = true)
    Integer deleteByIdSearchDetail(Integer idSearch);

}
