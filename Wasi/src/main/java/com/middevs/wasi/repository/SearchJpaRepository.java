package com.middevs.wasi.repository;

import com.middevs.wasi.table.District;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SearchJpaRepository extends JpaRepository<Search, Integer> {

    @Query("select s from Search s where s.user.idUser = :idUser")
    List<Search> findByIdUser(Integer idUser);

    @Transactional
    @Modifying
    @Query(value = "delete from `SEARCH` s where s.idSearch = ?1 ;", nativeQuery = true)
    Integer deleteByIdSearch(Integer idSearch);


    @Transactional
    @Modifying
    @Query(value = "delete from SEARCH_FEATURES_DETAIL sfd where sfd.idSearch = ?1 ;;", nativeQuery = true)
    Integer deleteByIdSearchFeature(Integer idSearch);

    @Transactional
    @Modifying
    @Query(value = "delete from SEARCH_INTEREST_DETAIL sid where sid.idSearch = ?1 ;", nativeQuery = true)
    Integer deleteByIdSearchInterest(Integer idSearch);

    @Transactional
    @Modifying
    @Query(value = "delete from SEARCH_SERVICES_DETAIL ssd  where ssd.idSearch = ?1 ;", nativeQuery = true)
    Integer deleteByIdSearchService(Integer idSearch);

}
