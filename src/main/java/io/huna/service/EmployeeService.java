package io.huna.service;

import io.huna.domain.employee.Employee;
import io.huna.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<Employee> findEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> updateEmployee(Employee employee) {
        return employeeRepository.update(employee);
    }

    public Mono<String> saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
