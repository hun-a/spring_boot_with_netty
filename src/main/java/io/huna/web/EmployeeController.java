package io.huna.web;

import io.huna.service.EmployeeService;
import io.huna.web.dto.EmployeeRequestDto;
import io.huna.web.dto.EmployeeResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    private Mono<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return employeeService.findEmployeeById(id)
                .map(EmployeeResponseDto::new);
    }

    @GetMapping("/name/{name}")
    private Flux<EmployeeResponseDto> getAllEmployeesByName(@PathVariable String name) {
        return employeeService.findAllEmployeesByName(name)
                .map(EmployeeResponseDto::new);
    }

    @GetMapping
    private Flux<EmployeeResponseDto> getAllEmployees() {
        return employeeService.findAllEmployees()
                .map(EmployeeResponseDto::new);
    }

    @PostMapping("/update")
    private Mono<EmployeeResponseDto> updateEmployee(@RequestBody EmployeeRequestDto body) {
        return employeeService.updateEmployee(body.toEntity())
                .map(EmployeeResponseDto::new);
    }

    @PostMapping("/save")
    private Mono<Long> saveEmployee(@RequestBody EmployeeRequestDto body) {
        return employeeService.saveEmployee(body.toEntity());
    }
}
