package com.gss.sequencefinal.controller;

import com.gss.sequencefinal.model.Department;
import com.gss.sequencefinal.model.Dto.DepartmentDto;
import com.gss.sequencefinal.model.Dto.EmployeeDto;
import com.gss.sequencefinal.model.Employee;
import com.gss.sequencefinal.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody final DepartmentDto departmentDto){
        Department department = departmentService.addDepartment(Department.from(departmentDto));
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments(){
        List<Department> departments = departmentService.getDepartments();
        List<DepartmentDto> departmentsDto = departments.stream().map(DepartmentDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    @PostMapping("add-many-departments")
    public ResponseEntity<List<DepartmentDto>> addManyDepartments() {
        List<Department> departments = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            departments.add(new Department("Department" + i));
        }
        departmentService.addManyDepartments(departments);
        List<DepartmentDto> departmentsDto = departments.stream().map(DepartmentDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(departmentsDto, HttpStatus.CREATED);
    }

}
