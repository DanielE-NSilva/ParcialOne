package com.example.parcialone.services;

import com.example.parcialone.exceptions.ParcialOneException;
import com.example.parcialone.persistence.entity.Backlog;
import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.persistence.repository.BacklogRepository;
import com.example.parcialone.persistence.repository.ProjectRepository;
import com.example.parcialone.services.dto.BacklogInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BacklogServiceImp implements  BacklogService {
    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    @Override
    public Backlog createBacklogId(BacklogInDTO backlogInDTO){
        Backlog backlog = new Backlog();
        Optional<Project> project = projectRepository.findById(backlogInDTO.getProjectId());
        if(project.isEmpty())
            throw new ParcialOneException("404 Bad Request", HttpStatus.NOT_FOUND);
        else
            if(!Objects.equals(project.get().getProjectIdentifier(), backlogInDTO.getProjectIdentifier()))
                throw new ParcialOneException("400 Bad Request", HttpStatus.BAD_REQUEST);
        backlog.setProject(project.get());
        backlog.setProjectIdentifier(project.get().getProjectIdentifier());
        return this.backlogRepository.save(backlog);
    }

    @Override
    public List<Backlog> findAll(){
        return this.backlogRepository.findAll();
    }

    @Override
    public Backlog findById(Long id){
        Optional<Backlog> backlog = this.backlogRepository.findById(id);
        if(backlog.isEmpty())
            throw new ParcialOneException("404 not found", HttpStatus.NOT_FOUND);
        return backlog.get();
    }
}