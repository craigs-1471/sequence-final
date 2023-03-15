package com.gss.sequencefinal.controller;

import com.gss.sequencefinal.model.Department;
import com.gss.sequencefinal.model.Employee;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final int BATCH_SIZE = 4;
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<String> multiTableBatch() {
        for (int i = 0; i < 10; i++) {
            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }

            Employee firstEmployee = new Employee("Multi Table Batch - Emp 1");
            Employee secondEmployee = new Employee("Multi Table Batch - Emp 2");
            Department department = new Department("Multi Table Batch - Dept");
            firstEmployee.setDepartment(department);
            secondEmployee.setDepartment(department);
            department.addEmployee(firstEmployee);
            department.addEmployee(secondEmployee);
//            employee.setDepartment(department);
//            department.addEmployee(employee);
            entityManager.persist(department);
            entityManager.persist(firstEmployee);
            entityManager.persist(secondEmployee);

        }
        return new ResponseEntity<>("Batch Insert Complete", HttpStatus.CREATED);
    }
}
