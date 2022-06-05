package com.example.parcialone.services.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectInDTO {
    private String projectName;
    private String projectIdentifier;
    private String description;
}
