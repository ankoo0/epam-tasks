package com.epam.rd.autotasks.springemployeecatalog.controller;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("")
    public List<Employee> getAllEmployees(@RequestParam(name = "sort", required = false) String sort,
                                          @RequestParam(name = "page", required = false) Long page,
                                          @RequestParam(name = "size", required = false) Long size) {
        return employeeService.getAll(sort, page, size);
    }

    @GetMapping("/{employee_id}")
    public Employee getEmployee(@RequestParam(name = "full_chain", required = false) boolean isChain,
                                @PathVariable(name = "employee_id") Long employeeId){
        return employeeService.getEmployeeById(employeeId, isChain);

    }



    @GetMapping("/by_manager/{managerId}")
    public List<Employee> getByManager(@PathVariable(name = "managerId") Long managerId,
                                       @RequestParam(name = "page") Long page,
                                       @RequestParam(name = "size") Long size,
                                       @RequestParam(name = "sort") String sort) {
        return employeeService.getAllByManager(page, size, sort, managerId);
    }


    @GetMapping("/by_department/{department}")
    public List<Employee> getByDepartment(@PathVariable(name = "department") String department,
                                          @RequestParam(name = "page", required = false) Long page,
                                          @RequestParam(name = "size", required = false) Long size,
                                          @RequestParam(name = "sort", required = false) String sort) {
        return employeeService.getAllByDepartment(page, size, sort, department);
    }
}
