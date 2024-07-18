package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.model.Employee;
import com.IntegrativeProject.ActionFactory.model.Role;
import com.IntegrativeProject.ActionFactory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private RoleService roleService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleService roleService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
    }


    public  void createEmployee(Employee employee){
        Long roleId = employee.getRole().getId();
        Optional<Role> roleOptional = roleService.findById(roleId);
        if (roleOptional.isEmpty()) {
            throw new RuntimeException("Role not found");
        }
        Role role = roleOptional.get();
        employee.setRole(role);
        this.employeeRepository.save(employee);
    }

    public List<Employee>seeEmployees(){
        return this.employeeRepository.findAll();
    }

    public  void  deleteEmployee(Long id){
        Optional<Employee> employee= this.employeeRepository.findById(id);
        if(employee.isPresent()){
            this.employeeRepository.delete(employee.get());

        }
        else {
            System.out.println("employee not found");
        }
    }

}
