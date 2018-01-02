package com.example.demo.mysqlRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
