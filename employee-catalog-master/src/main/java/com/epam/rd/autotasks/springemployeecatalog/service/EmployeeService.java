package com.epam.rd.autotasks.springemployeecatalog.service;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.FullName;
import com.epam.rd.autotasks.springemployeecatalog.domain.Position;
import com.epam.rd.autotasks.springemployeecatalog.entity.EmployeeEntity;
import com.epam.rd.autotasks.springemployeecatalog.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public Employee getEmployeeById(Long id, boolean isChain){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee manager = null;
        if (isChain){

            if (employeeEntity.getManagerId()!=null){
                manager = getEmployeeById(employeeEntity.getManagerId(), true);
            }

            return new Employee(
                    employeeEntity.getId(),
                    new FullName(employeeEntity.getFirstname(), employeeEntity.getLastName(), employeeEntity.getMiddlename()),
                    Position.valueOf(employeeEntity.getPosition()),
                    employeeEntity.getHired(),
                    BigDecimal.valueOf(employeeEntity.getSalary()).setScale(5, RoundingMode.HALF_UP),
                    manager,
                    departmentService.getDepartmentById(employeeEntity.getDepartment()));
        } else {
            if (employeeEntity.getManagerId()!=null){
                EmployeeEntity managerDTO=employeeRepository.findById(employeeEntity.getManagerId()).get();

                manager =  new Employee(
                        managerDTO.getId(),
                        new FullName(managerDTO.getFirstname(),managerDTO.getLastName(),managerDTO.getMiddlename()),
                        Position.valueOf(managerDTO.getPosition()),
                        managerDTO.getHired(),
                        BigDecimal.valueOf(managerDTO.getSalary()).setScale(5, RoundingMode.HALF_UP),
                        null,
                        departmentService.getDepartmentById(managerDTO.getDepartment()));

            }

        }
            return new Employee(
                    employeeEntity.getId(),
                    new FullName(employeeEntity.getFirstname(), employeeEntity.getLastName(), employeeEntity.getMiddlename()),
                    Position.valueOf(employeeEntity.getPosition()),
                    employeeEntity.getHired(),
                    BigDecimal.valueOf(employeeEntity.getSalary()).setScale(5, RoundingMode.HALF_UP),
                    manager,
                    departmentService.getDepartmentById(employeeEntity.getDepartment()));
        }




        public List<Employee> getAll(String sort, Long page, Long size){
            List<EmployeeEntity> employeeEntities;

            if (size!=null && page!=null){
                Pageable pageable = PageRequest.of(page.intValue(), size.intValue(), Sort.by(sort));
                employeeEntities = employeeRepository.findAll(pageable).toList();
                return employeeEntities.stream().map(eDTO-> this.getEmployeeById(eDTO.getId(),false)).collect(Collectors.toList());
            }
        if (sort==null){
            employeeEntities = employeeRepository.findAll();
            return employeeEntities.stream().map(eDTO-> this.getEmployeeById(eDTO.getId(),false)).collect(Collectors.toList());
        } else {
          employeeEntities = employeeRepository.findAll(Sort.by(Sort.Direction.ASC,sort));
            return employeeEntities.stream().map(eDTO-> this.getEmployeeById(eDTO.getId(),false)).collect(Collectors.toList());
        }

        }





    public List<Employee> employeesPagination(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll(pageable).toList();

        return employeeEntities.stream().map(eDTO-> this.getEmployeeById(eDTO.getId(),false)).collect(Collectors.toList());
    }


    public List<Employee> getAllByManager(Long page, Long size, String sortBy, Long managerId){
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue(), Sort.by(sortBy));
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByManagerId(pageable,managerId).toList();
        return employeeEntities.stream().map(eDTO-> this.getEmployeeById(eDTO.getId(),false)).collect(Collectors.toList());
    }



    public List<Employee> getAllByDepartment(Long page, Long size, String sortBy, String department){
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue(), Sort.by(sortBy));
        List<EmployeeEntity> employeeEntities;
        if (isNumeric(department)){
          employeeEntities = employeeRepository.findAllByDepartment(pageable,Long.valueOf(department)).toList();
        } else {
            Long deptId = departmentService.getDepartmentId(department);
            employeeEntities = employeeRepository.findAllByDepartment(pageable,deptId).toList();
        }


        return employeeEntities.stream().map(eDTO-> this.getEmployeeById(eDTO.getId(),false)).collect(Collectors.toList());
    }



    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

}
