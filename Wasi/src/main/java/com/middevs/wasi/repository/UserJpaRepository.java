package com.middevs.wasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.middevs.wasi.table.ContactVendorBuyer;
import com.middevs.wasi.table.User;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer>{

    List<User> findByDniUser(Integer dniUser);

    List<User> findByEmailUser(String emailUser);

    User findByIdUser(Integer idUser);
}
