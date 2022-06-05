package com.example.parcialone.services.dto;

import com.example.parcialone.persistence.entity.StatusProjectTask;
import lombok.Data;

@Data
public class ProjectTaskInDTO {
    private String name;
    private String summary;
    private String acceptanceCriteria;
    private StatusProjectTask status;
    private int priority=1;
    private double hours=1;
    private String projectIdentifier;
    private long BacklogId=1;
}