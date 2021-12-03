package io.huna.config;

import io.huna.domain.employee.Employee;
import io.huna.domain.employee.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class FunctionalRouterConfig {

    private final EmployeeRepository employeeRepository;

    public FunctionalRouterConfig(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Bean
    public RouterFunction<ServerResponse> composeRoutes() {
        return
                route(GET("/employees"),
                        req -> ok().body(
                                employeeRepository.findAll(), Employee.class))

                .and(route(GET("/employees/{id}"),
                        req -> ok().body(
                                employeeRepository.findById(Long.parseLong(req.pathVariable("id"))), Employee.class)))

                .and(route(POST("/employees/update"),
                        req -> req.body(toMono(Employee.class))
                                .doOnNext(employeeRepository::save)
                                .then(ok().build())));
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf()
                .disable()
                .authorizeExchange()
                .anyExchange()
                .permitAll();
        return http.build();
    }
}
