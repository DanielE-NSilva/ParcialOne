package com.example.parcialone.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "The name must not be empty")
    @Column(name = "project_name", nullable = false, unique = true )
    private String projectName;

    @Size(min = 5, max = 7, message = "Not in range identifier")
    @NotEmpty(message = "The name identifier must not be empty")
    @Column(name = "project_identifier", nullable = false, unique = true ,updatable = false)
    private String projectIdentifier;

    @NotEmpty(message = "The description must not be empty")
    @Column(name ="description", nullable = false)
    private String description;

    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date" )
    private LocalDateTime endDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
