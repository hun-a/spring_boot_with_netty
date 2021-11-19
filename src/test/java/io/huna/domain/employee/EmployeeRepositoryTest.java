package io.huna.domain.employee;

import io.r2dbc.h2.H2ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DatabaseClient client;

    @Autowired
    private H2ConnectionFactory factory;

    @BeforeEach
    public void setup() {
        List<String> statements = Arrays.asList(
                "DROP TABLE IF EXISTS employee;",
                "CREATE TABLE employee (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR2);"
        );

        statements.forEach(it -> client.execute(it)
                .fetch()
                .rowsUpdated()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete());
    }

    @Test
    void whenDeleteAll_then0IsExpected() {
        employeeRepository.deleteAll()
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void whenInsert6_then6AreExpected() {
        insertEmployees();

        employeeRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(6)
                .verifyComplete();
    }

    private void insertEmployees() {
        List<Employee> employees = Arrays.asList(
                Employee.builder().name("One").build(),
                Employee.builder().name("Two").build(),
                Employee.builder().name("Three").build(),
                Employee.builder().name("Four").build(),
                Employee.builder().name("Five").build(),
                Employee.builder().name("Six").build()
        );

        employeeRepository.saveAll(employees).subscribe();
    }

    @Test
    void whenSearchForTwo_then1IsExpected() {
        insertEmployees();

        employeeRepository.findAllByName("Two")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void whenBatchHas2Operations_then2AreExpected() {
        String query = "select * from employee";

        Mono.from(factory.create())
                .flatMapMany(connection -> Flux.from(connection.createBatch().add(query).add(query).execute()))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }
}