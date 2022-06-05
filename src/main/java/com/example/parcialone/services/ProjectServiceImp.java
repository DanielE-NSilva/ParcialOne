package com.example.parcialone.services;

import com.example.parcialone.exceptions.ParcialOneException;
import com.example.parcialone.mapper.ProjectInDTOtoProject;
import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.persistence.repository.ProjectRepository;
import com.example.parcialone.services.dto.ProjectInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectInDTOtoProject mapper;

    @Override
    public Project createProject(ProjectInDTO projectInDTO) {
        int length = projectInDTO.getProjectIdentifier().length();
        if(length < 5 || length  > 7)
            throw new ParcialOneException("400 Bad Request", HttpStatus.BAD_REQUEST);

        if(this.findByProjectName(projectInDTO,true) != null  || this.findByProjectIdentifier(projectInDTO,true) != null)
            throw new ParcialOneException("400 Bad Request", HttpStatus.BAD_REQUEST);

        Project project = mapper.map(projectInDTO);
        return this.projectRepository.save(project);
    }

    @Override
    public Project findByProjectName(ProjectInDTO projectInDTO,boolean create){
        Project project = this.projectRepository.findByProjectName(projectInDTO.getProjectName());
        if(!create)
            if(project == null)
                throw new ParcialOneException("404 Not Found", HttpStatus.BAD_REQUEST);
        return project;
    };

    @Override
    public Project findByProjectIdentifier(ProjectInDTO projectInDTO,boolean create){
        Project project = this.projectRepository.findByProjectIdentifier(projectInDTO.getProjectName());
        if(!create)
            if(project == null)
                throw new ParcialOneException("404 Not Found", HttpStatus.BAD_REQUEST);
        return project;
    };

    @Override
    public Project findById(long id){
        Optional<Project> project = this.projectRepository.findById(id);
        if(project.isEmpty())
            throw new ParcialOneException("404 not found", HttpStatus.NOT_FOUND);
        return project.get();
    }

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    @Transactional
    public void finishedProject(Long id){
        Optional<Project> project = this.projectRepository.findById(id);
        if(project.isEmpty())
            throw new ParcialOneException("project not found", HttpStatus.NOT_FOUND);
        this.projectRepository.finishedProject(id,LocalDateTime.now());
    }
}
