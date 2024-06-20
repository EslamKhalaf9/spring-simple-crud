package com.khalaf.cruddemo.rest;

import com.khalaf.cruddemo.entity.Employee;
import com.khalaf.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();

        for(Employee employee: employees) {
            System.out.println(employee);
        }

        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new RuntimeException(STR."Employee is not found - \{id}");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);

        return employeeService.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee editEmployee(@RequestBody Employee employee, @PathVariable int id) {
        Employee existingEmployee = employeeService.findById(id);

        if (existingEmployee == null) {
            throw new RuntimeException(STR."Employee is not found - \{id}");
        }

        employee.setId(existingEmployee.getId());

        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee existingEmployee = employeeService.findById(id);

        if (existingEmployee == null) {
            throw new RuntimeException(STR."Employee is not found - \{id}");
        }

        employeeService.deleteById(id);

        return STR."Employee with id: \{id} is deleted successfully";
    }
}
