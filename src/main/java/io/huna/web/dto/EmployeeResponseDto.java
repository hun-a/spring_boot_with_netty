package io.huna.web.dto;

import io.huna.domain.employee.Employee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeResponseDto {

    private String id;

    private String name;

    public EmployeeResponseDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
    }
}
