package com.middevs.wasi.repository;

import com.middevs.wasi.table.TypeRealEstate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRealEstateJPARepository extends JpaRepository<TypeRealEstate, Integer> {
	TypeRealEstate findByNameTypeRealEstate(String nameTypeRealEstate);
}
