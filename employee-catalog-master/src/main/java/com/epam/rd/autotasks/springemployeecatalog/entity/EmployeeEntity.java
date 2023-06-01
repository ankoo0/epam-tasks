package com.epam.rd.autotasks.springemployeecatalog.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstname", length = 10)
    private String firstname;

    @Column(name = "lastname", length = 10)
    private String lastName;

    @Column(name = "middlename", length = 10)
    private String middlename;

    @Column(name = "position", length = 9)
    private String position;

    @Column(name = "hiredate")
    private LocalDate hired;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "department")
    private Long department;

    @Column(name = "manager")
    private Long managerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public LocalDate getHired() {
        return hired;
    }

    public void setHired(LocalDate hiredate) {
        this.hired = hiredate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }


    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastName + '\'' +
                ", middlename='" + middlename + '\'' +
                ", position='" + position + '\'' +
                ", hiredate=" + hired +
                ", salary=" + salary +
                ", department=" + department +
                ", managerId=" + managerId +
                '}';
    }
}