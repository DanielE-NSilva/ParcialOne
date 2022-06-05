package com.example.parcialone.services;

import com.example.parcialone.persistence.entity.ProjectTask;
import com.example.parcialone.persistence.entity.StatusProjectTask;
import com.example.parcialone.services.dto.ProjectTaskInDTO;
import java.util.List;

public interface ProjectTaskService {

    ProjectTask CreatedProjectTask(ProjectTaskInDTO projectTaskInDTO);

    List<ProjectTask> findAll();

    List<ProjectTask> findAllByProjectIdentifier(String projectIdentifier);

    double findAllByProjectIdentifierAndStatusNotDelete(String projectIdentifier);

    double findAllByProjectIdentifierAndStatus(String projectIdentifier, StatusProjectTask statusProjectTask);

    void deleteProjectTaskBy(String projectIdentifier,int id);
}
