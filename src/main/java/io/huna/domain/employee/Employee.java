package io.huna.domain.employee;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {

    @Id
    private Long id;

    private String name;

    @Builder
    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee update(Long id, String name) {
        this.id = id;
        this.name = name;
        return this;
    }
}
