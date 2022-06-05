package com.example.parcialone.controller;

import com.example.parcialone.helpers.Common;
import com.example.parcialone.helpers.Response;
import com.example.parcialone.helpers.ResponseBuild;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.services.ProjectService;
import com.example.parcialone.services.dto.ProjectInDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/Projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ResponseBuild builder;
    private final Common common;

    @PostMapping
    public Response createProject(@Valid @RequestBody ProjectInDTO projectInDTO, BindingResult result){
        if (result.hasErrors()) {
            return builder.failed(common.formatMessage(result));
        }
        Project project = this.projectService.createProject(projectInDTO);
        return builder.success(project);
    }

    @GetMapping
    public Response findAll() {
        return builder.success(this.projectService.findAll());
    }

    @PatchMapping("/finished_Project/{id}")
    public Response finishedProject(@PathVariable("id") Long id){
        this.projectService.finishedProject(id);
        return builder.success();
    }

    @PatchMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        return builder.success(this.projectService.findById(id));
    }

}