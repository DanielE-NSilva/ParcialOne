package com.example.parcialone.services;


import com.example.parcialone.exceptions.ParcialOneException;
import com.example.parcialone.helpers.ResponseBuild;
import com.example.parcialone.mapper.IMapper;
import com.example.parcialone.mapper.ProjectInDTOtoProject;
import com.example.parcialone.mapper.ProjectTaskInDTOtoProjectTask;
import com.example.parcialone.persistence.entity.Backlog;
import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.persistence.entity.ProjectTask;
import com.example.parcialone.persistence.entity.StatusProjectTask;
import com.example.parcialone.persistence.repository.BacklogRepository;
import com.example.parcialone.persistence.repository.ProjectTaskRepository;
import com.example.parcialone.services.dto.ProjectInDTO;
import com.example.parcialone.services.dto.ProjectTaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectTaskService {

    private final ProjectTaskRepository  projectTaskRepository;
    private final BacklogRepository backlogRepository;
    private final ProjectTaskInDTOtoProjectTask mapper;

    public ProjectTask CreatedProjectTask(ProjectTaskInDTO projectTaskInDTO){
        int priority = projectTaskInDTO.getPriority();
        double hoursRange = projectTaskInDTO.getHours();

        if(priority > 5 || priority <1)
            throw new ParcialOneException("400 Bad Request", HttpStatus.BAD_REQUEST);
        if(hoursRange > 8 || hoursRange < 1)
            throw new ParcialOneException("400 Bad Request", HttpStatus.BAD_REQUEST);
        Optional<Backlog> backlog = backlogRepository.findById(projectTaskInDTO.getBacklogId());
        if(backlog.isEmpty())
            throw new ParcialOneException("404 Bad Request", HttpStatus.NOT_FOUND);
        else
            if(!Objects.equals(backlog.get().getProjectIdentifier(), projectTaskInDTO.getProjectIdentifier()))
                throw new ParcialOneException("400 Bad Request", HttpStatus.BAD_REQUEST);

        ProjectTask projectTask = mapper.map(projectTaskInDTO);
        projectTask.setBacklog(backlog.get());
        return projectTaskRepository.save(projectTask);
    }

    public List<ProjectTask> findAll(){
        return this.projectTaskRepository.findAll();
    }

    public List<ProjectTask> findAllByProjectIdentifier(String projectIdentifier){
        return this.projectTaskRepository.findAllByProjectIdentifier(projectIdentifier);
    }

    public double findAllByProjectIdentifierAndStatusNotDelete(String projectIdentifier){
        List<ProjectTask> projectTas = this.projectTaskRepository.findAllByProjectIdentifier(projectIdentifier);
        if(projectTas.isEmpty()){
            throw new ParcialOneException("404 not found", HttpStatus.NOT_FOUND);
        }
        double temp = this.projectTaskRepository.findAllByProjectIdentifierAndStatusNotDelete(projectIdentifier);
        System.out.println(temp);
        return temp;
    }

    public double findAllByProjectIdentifierAndStatus(String projectIdentifier, StatusProjectTask statusProjectTask){
        List<ProjectTask> projectTas = this.projectTaskRepository.findAllByProjectIdentifier(projectIdentifier);
        if(projectTas.isEmpty()){
            throw new ParcialOneException("404 not found", HttpStatus.NOT_FOUND);
        }
        List<ProjectTask> projectTasks = this.projectTaskRepository.findAllByProjectIdentifierAndStatusL(projectIdentifier, statusProjectTask.ordinal());
        if(projectTasks.isEmpty()){
            throw new ParcialOneException("404 not found", HttpStatus.NOT_FOUND);
        }

        double temp = this.projectTaskRepository.findAllByProjectIdentifierAndStatus(projectIdentifier,statusProjectTask.ordinal());
        return temp;
    }

    @Transactional
    public void deleteProjectTaskBy(String projectIdentifier,int id){
        this.projectTaskRepository.deleteProjectTaskBy(projectIdentifier,id);
    }

}
