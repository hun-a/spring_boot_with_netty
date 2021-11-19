package io.huna.domain.employee;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {

    Flux<Employee> findAllByName(String name);
}
