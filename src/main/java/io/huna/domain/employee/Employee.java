package io.huna.domain.employee;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {

    private String id;

    private String name;

    @Builder
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee update(String id, String name) {
        this.id = id;
        this.name = name;
        return this;
    }
}
