package com.example.parcialone.controller;


import com.example.parcialone.helpers.Common;
import com.example.parcialone.helpers.Response;
import com.example.parcialone.helpers.ResponseBuild;
import com.example.parcialone.persistence.entity.ProjectTask;
import com.example.parcialone.persistence.entity.StatusProjectTask;
import com.example.parcialone.services.ProjectTaskService;
import com.example.parcialone.services.dto.ProjectTaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/Task")
@RequiredArgsConstructor
public class ProjectTaskController {

    private final ProjectTaskService projectsTaskService;
    private final ResponseBuild builder;
    private final Common common;

    @PostMapping
    public Response createdBacklog(@Valid @RequestBody ProjectTaskInDTO projectTaskInDTO, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(common.formatMessage(result));
        }
        return builder.success(this.projectsTaskService.CreatedProjectTask(projectTaskInDTO));
    }

    @GetMapping
    public Response findAll() {
        return builder.success(this.projectsTaskService.findAll());
    }

    @GetMapping("/project/{projectIdentifier}")
    public Response findAllByProjectIdentifier(@PathVariable("projectIdentifier") String projectIdentifier){
        List<ProjectTask> projectTask = this.projectsTaskService.findAllByProjectIdentifier(projectIdentifier);
        if(projectTask.isEmpty())
            return builder.failed(NOT_FOUND.value());
        return builder.success(projectTask);
    }

    @GetMapping("/project/hours/{projectIdentifier}")
    public Response findAllByProjectIdentifierAndStatus(@PathVariable("projectIdentifier") String projectIdentifier){
        double time = this.projectsTaskService.findAllByProjectIdentifierAndStatusNotDelete(projectIdentifier);
        if(time == 0)
            return builder.failed(NOT_FOUND.value());
        return builder.success(time+" hours, time all project "+projectIdentifier);
    }

    @GetMapping("/project/hours/{projectIdentifier}/{status}")
    public Response findAllByProjectIdentifierAndStatus(@PathVariable("projectIdentifier") String projectIdentifier,@PathVariable("status") StatusProjectTask statusProjectTask){
        double time = this.projectsTaskService.findAllByProjectIdentifierAndStatus(projectIdentifier,statusProjectTask);
        if(time == 0)
            return builder.failed(NOT_FOUND.value());
        return builder.success(time+" hours, time all project "+projectIdentifier+ "witch status "+statusProjectTask);
    }

    @PatchMapping("/{idTask}/{projectIdentifier}")
    public Response deleteProjectTaskBy(@PathVariable("idTask") int id, @PathVariable("projectIdentifier") String projectIdentifier){
        this.projectsTaskService.deleteProjectTaskBy(projectIdentifier,id);
        return builder.success();
    }

}