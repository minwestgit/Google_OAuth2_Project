package com.my.oauth.repository;

import java.util.Optional;

import com.my.oauth.entity.EmpMaster;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpMasterRepository extends JpaRepository<EmpMaster, Long>
{
  Optional<EmpMaster> findByEmail(String email);
}
