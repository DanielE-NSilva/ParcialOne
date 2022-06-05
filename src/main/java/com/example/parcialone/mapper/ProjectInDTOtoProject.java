package com.example.parcialone.mapper;

import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.services.dto.ProjectInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProjectInDTOtoProject implements IMapper<ProjectInDTO, Project> {
    @Override
    public Project map(ProjectInDTO in) {
        Project project = new Project();
        project.setProjectName(in.getProjectName());
        project.setProjectIdentifier(in.getProjectIdentifier());
        project.setDescription(in.getDescription());
        project.setStartDate(LocalDateTime.now());
        return project;
    }
}
