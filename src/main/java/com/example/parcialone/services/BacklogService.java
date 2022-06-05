package com.example.parcialone.services;

import com.example.parcialone.persistence.entity.Backlog;
import com.example.parcialone.services.dto.BacklogInDTO;
import java.util.List;

public interface BacklogService {
    Backlog createBacklogId(BacklogInDTO backlogInDTO);

    List<Backlog> findAll();

    Backlog findById(Long id);

}
