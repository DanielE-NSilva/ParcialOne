package com.example.parcialone.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Backlogs")
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "The name identifier must not be empty")
    @Column(name = "project_identifier", nullable = false)
    private String projectIdentifier;
    @OneToOne
    @JoinColumn(name = "project", updatable = false, nullable = false)
    private Project project;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Backlog)) return false;
        Backlog backlog = (Backlog) o;
        return id == backlog.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
