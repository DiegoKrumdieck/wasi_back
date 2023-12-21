package com.middevs.wasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.middevs.wasi.dto.RealEstateTOCSVDTO;
import com.middevs.wasi.table.ContactVendorBuyer;
import com.middevs.wasi.table.District;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.Service;
import com.middevs.wasi.table.TypeRealEstate;

@Repository
public interface RealEstateJpaRepository extends JpaRepository<RealEstate, Integer>{
	
	@Query("select re from RealEstate re "
			+ "WHERE "
			+ "(:minBudget is null OR re.priceRealEstate >= :minBudget) "
			+ "AND (:maxBudget is null OR re.priceRealEstate <= :maxBudget) "
			+ "AND (:idDistrict is null OR re.idDistrict = :idDistrict) "
			+ "AND (:numBedrooms is null OR re.numBedrooms >= :numBedrooms) "
			+ "AND (:numBathrooms is null OR re.numBathrooms >= :numBathrooms) "
			+ "AND (:antiquity is NULL OR (re.antiquityRealEstate >= :antiquity - 5 "
			+ "OR re.antiquityRealEstate <= :antiquity + 5)) "
			+ "AND (:typeRealEstate is NULL OR re.idTypeRealEstate = :typeRealEstate)")
	List<RealEstate> findCoincidences(@Param("minBudget")Double minBudget,
										@Param("maxBudget")Double maxBudget, 
										@Param("antiquity")Integer antiquity, 
										@Param("numBedrooms")Integer numBedrooms,
										@Param("numBathrooms")Integer numBathrooms,
										@Param("idDistrict")District district,
										@Param("typeRealEstate")TypeRealEstate type);
	@Query(value = "select new RealEstate(re.idRealEstate, re.longitudeRealEstate, re.latitudeRealEstate) "
					+ "FROM RealEstate re")
	List<RealEstate> findRealEstateWithLongAndLng();
	
	
	/*
	@Query("select re from RealEstate re "
			+ "WHERE "
			+ "(:minBudget is null OR re.priceRealEstate >= :minBudget) "
			+ "AND (:maxBudget is null OR re.priceRealEstate <= :maxBudget) "
			+ "AND (:idDistrict is null OR re.idDistrict = :idDistrict) "
			+ "AND (:idTypeRealEstate is null OR re.idTypeRealEstate = :idTypeRealEstate) "
			+ "AND (:numBedrooms is null OR re.numBedrooms = :numBedrooms) "
			+ "AND (:numBathrooms is null OR re.numBedrooms = :numBedrooms) "
			+ "AND (:antiquity is NULL OR (re.antiquityRealEstate >= :antiquity - 5 "
			+ "AND re.antiquityRealEstate <= :antiquity + 5)) ")
	List<RealEstate> findCoincidences(@Param("minBudget")Double minBudget,
										@Param("maxBudget")Double maxBudget, 
										@Param("antiquity")Integer antiquity, 
										@Param("numBedrooms")Integer numBedrooms,
										@Param("numBathrooms")Integer numBathrooms,
										@Param("idDistrict")Integer idDistrict,
										@Param("idTypeRealEstate")Integer idTypeRealEstate, 
										@Param("typesInterestPlaces")List<Integer>typesInterestPlaces,
										@Param("services")List<Integer>services,
										@Param("features")List<Integer>features);*/
	
}
