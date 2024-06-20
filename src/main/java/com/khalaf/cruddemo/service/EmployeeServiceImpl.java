package com.khalaf.cruddemo.service;

import com.khalaf.cruddemo.dao.EmployeeRepository;
import com.khalaf.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        Employee employee = null;
        if (existingEmployee.isPresent()) {
            employee = existingEmployee.get();
        } else {
            throw new RuntimeException("Employee not found");
        }

        return employee;
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
