package com.epam.rd.autotasks.springemployeecatalog.service;

import com.epam.rd.autotasks.springemployeecatalog.domain.Department;
import com.epam.rd.autotasks.springemployeecatalog.entity.DepartmentEntity;
import com.epam.rd.autotasks.springemployeecatalog.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Long getDepartmentId(String name){
        return departmentRepository.findByFullName(name).getId();
    }

    public Department getDepartmentById(Long id){
        if (id==null){
            return null;
        }else {
            DepartmentEntity departmentDTO = departmentRepository.findById(id).get();
            return new Department(departmentDTO.getId(),departmentDTO.getFullName(), departmentDTO.getLocation());
        }

    }
}
