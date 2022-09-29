package com.tstcore.appinvestecclient.repositories;

import com.tstcore.appinvestecclient.entities.Client;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> findByFullName(String fullName);
    Optional<Client> findByMobileNumber(String mobileNumber);
    Optional<Client> findByIdNumber(String idNumber);
}

