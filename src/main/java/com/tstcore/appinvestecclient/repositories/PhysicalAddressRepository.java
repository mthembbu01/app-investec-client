package com.tstcore.appinvestecclient.repositories;

import com.tstcore.appinvestecclient.entities.PhysicalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalAddressRepository extends JpaRepository<PhysicalAddress,Long> {
}
