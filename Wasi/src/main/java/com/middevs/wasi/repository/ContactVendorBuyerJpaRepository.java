package com.middevs.wasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.middevs.wasi.table.ContactVendorBuyer;

@Repository
public interface ContactVendorBuyerJpaRepository extends JpaRepository<ContactVendorBuyer, Integer>{

}
