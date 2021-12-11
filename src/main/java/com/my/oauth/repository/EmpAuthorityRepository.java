package com.my.oauth.repository;

import java.util.List;

import com.my.oauth.entity.EmpAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpAuthorityRepository extends JpaRepository<EmpAuthority, Long>
{
    List<EmpAuthority> findByEmail(String email);
}
