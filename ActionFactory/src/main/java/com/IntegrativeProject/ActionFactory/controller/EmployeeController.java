package com.IntegrativeProject.ActionFactory.controller;

import com.IntegrativeProject.ActionFactory.model.Employee;
import com.IntegrativeProject.ActionFactory.model.Role;
import com.IntegrativeProject.ActionFactory.service.EmployeeService;
import com.IntegrativeProject.ActionFactory.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private RoleService roleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
    }


    @PostMapping()
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        Long roleId = employee.getRole().getId();
        Optional<Role> roleOptional = roleService.findById(roleId);
        if (roleOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Role not found");
        }
        Role role = roleOptional.get();
        employee.setRole(role);
        employeeService.createEmployee(employee);
        return ResponseEntity.ok("Employee created");
    }


    @GetMapping()
    public List<Employee> seeEmployees(){
        return  this.employeeService.seeEmployees();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        this.employeeService.deleteEmployee(id);
        return  ResponseEntity.ok("employee successfully deleted");
    }


}
