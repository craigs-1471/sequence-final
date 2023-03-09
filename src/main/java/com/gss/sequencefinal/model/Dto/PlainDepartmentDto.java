package com.gss.sequencefinal.model.Dto;

import com.gss.sequencefinal.model.Department;
import lombok.Data;

@Data
public class PlainDepartmentDto {
    private Long id;
    private String name;

    public static PlainDepartmentDto from(Department department){
        PlainDepartmentDto plainDepartmentDto = new PlainDepartmentDto();
        plainDepartmentDto.setId(department.getId());
        plainDepartmentDto.setName(department.getName());
        return plainDepartmentDto;
    }
}

