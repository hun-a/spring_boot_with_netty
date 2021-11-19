package io.huna.service;

import io.huna.domain.employee.Employee;
import io.huna.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Mono<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> findAllEmployeesByName(String name) {
        return employeeRepository.findAllByName(name);
    }

    public Flux<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> updateEmployee(Employee employee) {
        return ObjectUtils.isEmpty(employee.getId()) ? Mono.empty() : employeeRepository.save(employee);
    }

    public Mono<Long> saveEmployee(Employee employee) {
        return ObjectUtils.isEmpty(employee.getId()) ? employeeRepository.save(employee).map(Employee::getId) : Mono.empty();
    }
}
