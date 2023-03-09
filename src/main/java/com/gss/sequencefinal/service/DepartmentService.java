package com.gss.sequencefinal.service;

import com.gss.sequencefinal.model.Department;
import com.gss.sequencefinal.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getDepartments(){
        return StreamSupport
                .stream(departmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Department> addManyDepartments(List<Department> departments) {
        return departmentRepository.saveAll(departments);
    }
}
