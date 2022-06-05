package com.example.parcialone.controller;


import com.example.parcialone.helpers.Common;
import com.example.parcialone.helpers.Response;
import com.example.parcialone.helpers.ResponseBuild;
import com.example.parcialone.persistence.entity.Backlog;
import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.services.BacklogService;
import com.example.parcialone.services.dto.BacklogInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Backlog")
@RequiredArgsConstructor
public class BacklogController {
    private final BacklogService backlogService;
    private final ResponseBuild builder;
    private final Common common;

    @PostMapping
    public Response createdBacklog(@Valid @RequestBody BacklogInDTO backlogInDTO, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(common.formatMessage(result));
        }
        return builder.success(this.backlogService.createBacklogId(backlogInDTO));
    }

    @GetMapping
    public Response findAll() {

        return builder.success(this.backlogService.findAll());
    }

    @PatchMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        return builder.success(this.backlogService.findById(id));
    }

}