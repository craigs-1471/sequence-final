package com.gss.sequencefinal.controller;

import com.gss.sequencefinal.model.Department;
import com.gss.sequencefinal.model.Dto.EmployeeDto;
import com.gss.sequencefinal.model.Employee;
import com.gss.sequencefinal.service.DepartmentService;
import com.gss.sequencefinal.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody final EmployeeDto employeeDto){
        Employee employee = employeeService.addEmployee(Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<Employee> employees = employeeService.getEmployees();
        List<EmployeeDto> employeesDto = employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }
    @PostMapping("/add-many-employees")
    public ResponseEntity<List<EmployeeDto>> addManyEmployees() {
        List<Employee> employees = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            employees.add(new Employee("employee latest" + i));
        }
        employeeService.addManyEmployees(employees);
        List<EmployeeDto> employeesDto = employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDto, HttpStatus.CREATED);
    }
    @PostMapping("/add-emps-with-depts")
    public ResponseEntity<List<EmployeeDto>> addEmployeesWithDepartments() {
        List<Employee> employees = new ArrayList<>();
        Department department = null;
        Employee employee = null;
        for(int i = 0; i < 20; i++) {
            if(i % 2 == 0) {
                department = new Department("Dept Multi Add");
                departmentService.addDepartment(department);
            }
            employee = new Employee("employee " + i, department);
//            department.addEmployee(employee);
            employees.add(employee);
        }
        employeeService.addManyEmployees(employees);
        List<EmployeeDto> employeesDto = employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDto, HttpStatus.CREATED);
    }
}
