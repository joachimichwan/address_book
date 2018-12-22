package com.example.tkai.repository;

import com.example.tkai.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDb extends JpaRepository<AddressModel, Long> {
}
