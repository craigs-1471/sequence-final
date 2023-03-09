package com.gss.sequencefinal.model.Dto;

import com.gss.sequencefinal.model.Department;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DepartmentDto {
    private Long id;
    private String name;
    private List<EmployeeDto> employeesDto = new ArrayList<>();

    public static DepartmentDto from(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setEmployeesDto(department.getEmployees().stream().map(EmployeeDto::from).collect(Collectors.toList()));
        return departmentDto;
    }

}






