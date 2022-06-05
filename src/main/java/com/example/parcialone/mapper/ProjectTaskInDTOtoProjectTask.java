package com.example.parcialone.mapper;

import com.example.parcialone.persistence.entity.ProjectTask;
import com.example.parcialone.services.dto.ProjectTaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProjectTaskInDTOtoProjectTask implements IMapper<ProjectTaskInDTO, ProjectTask> {

    @Override
    public ProjectTask map(ProjectTaskInDTO in) {
        ProjectTask projectTask = new ProjectTask();
        projectTask.setName(in.getName());
        projectTask.setSummary(in.getSummary());
        projectTask.setAcceptanceCriteria(in.getAcceptanceCriteria());
        projectTask.setStatus(in.getStatus());
        projectTask.setPriority(in.getPriority());
        projectTask.setHours(in.getHours());
        projectTask.setStartDate(LocalDateTime.now());
        projectTask.setProjectIdentifier(in.getProjectIdentifier());
        return projectTask;
    }
}