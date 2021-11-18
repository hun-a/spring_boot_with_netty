package io.huna.web.dto;

import io.huna.domain.employee.Employee;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeRequestDto {

    private String id;

    private String name;

    @Builder
    public EmployeeRequestDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee toEntity() {
        return Employee.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
