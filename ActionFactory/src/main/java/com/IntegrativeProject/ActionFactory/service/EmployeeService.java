package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.model.Employee;
import com.IntegrativeProject.ActionFactory.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public  void createEmployee(Employee employee){
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
