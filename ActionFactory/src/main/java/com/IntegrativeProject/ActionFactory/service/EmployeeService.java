package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.Exceptions.EmployeeException;
import com.IntegrativeProject.ActionFactory.model.Employee;
import com.IntegrativeProject.ActionFactory.model.Role;
import com.IntegrativeProject.ActionFactory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
            throw new EmployeeException("Role not found");
        }
        Role role = roleOptional.get();
        employee.setRole(role);
        validateEmployee(employee);
        this.employeeRepository.save(employee);
    }
    public  void createEmployees(List<Employee> employees){
        List<Employee> employeeList= new ArrayList<>();
        for (Employee employee: employees){
            Long roleId = employee.getRole().getId();
            Optional<Role> roleOptional = roleService.findById(roleId);
            if (roleOptional.isEmpty()) {
                throw new EmployeeException("Role not found for employee"+ employee.getName());
            }
            Role role = roleOptional.get();
            employee.setRole(role);
            validateEmployee(employee);
            employeeList.add(employee);
        }
        this.employeeRepository.saveAll(employeeList);
    }

    public List<Employee>seeEmployees(){
        List<Employee> employees=this.employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new EmployeeException("There is no employees to show, try saving one");
        }
        return employees;
    }

    public  void  deleteEmployee(Long id){
        Optional<Employee> employee= this.employeeRepository.findById(id);
        if(employee.isPresent()){
            this.employeeRepository.delete(employee.get());

        }
        else {
            throw new EmployeeException("Employee not found");
        }
    }
    public void validateEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty() || employee.getName().length() < 2) {
            throw new EmployeeException("Name not valid, check field");
        }
        if (employee.getEmail() == null || employee.getEmail().isEmpty() || !employee.getEmail().contains("@")) {
            throw new EmployeeException("Email not valid, check field");
        }
        if (employee.getPassword() == null || employee.getPassword().length() < 8 || !employee.getPassword().matches(".*\\d.*") || !employee.getPassword().matches(".*[a-zA-Z].*")) {
            throw new EmployeeException("Password not valid, check field");
        }
        if (employee.getRole() == null || roleService.findById(employee.getRole().getId()).isEmpty()) {
            throw new EmployeeException("Role not valid, check field");
        }
        if (employee.getHireDate() == null || employee.getHireDate().isAfter(LocalDate.now())) {
            throw new EmployeeException("Hire date not valid, check field");
        }
        if (employee.getLastAccess() == null || employee.getLastAccess().isAfter(LocalDateTime.now())) {
            throw new EmployeeException("Last access not valid, check field");
        }
        if (employee.getStatus() == null || employee.getStatus().isEmpty()) {
            throw new EmployeeException("Status not valid, check field");
        }
        if (employeeRepository.findAll().stream()
                .anyMatch(existingEmployee -> existingEmployee.getEmail().equals(employee.getEmail()))) {
            throw new EmployeeException("Email already in use");
        }
    }


}
