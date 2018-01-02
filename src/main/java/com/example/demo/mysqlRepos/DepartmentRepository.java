package com.example.demo.mysqlRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
