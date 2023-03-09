package com.gss.sequencefinal.model.Dto;

import com.gss.sequencefinal.model.Employee;
import lombok.Data;

import java.util.Objects;

@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private PlainDepartmentDto plainDepartmentDto;

    public static EmployeeDto from(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        if(Objects.nonNull(employee.getDepartment())){
            employeeDto.setPlainDepartmentDto(PlainDepartmentDto.from(employee.getDepartment()));
        }
        return employeeDto;
    }
}





