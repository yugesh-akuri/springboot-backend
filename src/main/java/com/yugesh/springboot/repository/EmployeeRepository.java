package com.yugesh.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yugesh.springboot.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
