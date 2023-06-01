package com.epam.rd.autotasks.springemployeecatalog.repository;

import com.epam.rd.autotasks.springemployeecatalog.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {


    Optional<EmployeeEntity> findById(Long id);

    Page<EmployeeEntity> findAll(Pageable pageable);

    Page<EmployeeEntity> findAllByManagerId(Pageable pageable, Long managerId);

    Page<EmployeeEntity>  findAllByDepartment(Pageable pageable, Long departmentId);
}
