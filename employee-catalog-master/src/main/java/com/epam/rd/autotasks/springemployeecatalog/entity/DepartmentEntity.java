package com.epam.rd.autotasks.springemployeecatalog.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 14)
    private String fullName;

    @Column(name = "location", length = 13)
    private String location;

    @OneToMany(mappedBy = "department")
    private Set<EmployeeEntity> employees = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", employees=" + employees +
                '}';
    }
}