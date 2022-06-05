package com.example.parcialone.services.interf;

import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.services.dto.ProjectInDTO;

import java.util.List;

public interface ProjectService {
    Project createProject(ProjectInDTO projectInDTO);

    Project findByProjectName(ProjectInDTO projectInDTO,boolean create);

    Project findByProjectIdentifier(ProjectInDTO projectInDTO,boolean create);

    Project findById(long id);

    List<Project> findAll();

    void finishedProject(Long id);

}
