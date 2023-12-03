package com.web.tourism.repository;

import com.web.tourism.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /*Optional<Address> findById(Long addressId);*/
}
