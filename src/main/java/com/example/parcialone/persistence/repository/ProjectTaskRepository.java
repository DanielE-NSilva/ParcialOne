package com.example.parcialone.persistence.repository;

import com.example.parcialone.persistence.entity.ProjectTask;
import com.example.parcialone.persistence.entity.StatusProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
    List<ProjectTask> findAllByProjectIdentifier(String projectIdentifier);

    @Query(value = "SELECT SUM(hours) FROM tasks WHERE project_identifier=:project_identifier AND status !=3", nativeQuery = true)
    double findAllByProjectIdentifierAndStatusNotDelete(@Param("project_identifier") String project_identifier);

    @Query(value = "SELECT SUM(hours) FROM tasks WHERE project_identifier=:project_identifier AND status =:status", nativeQuery = true)
    double findAllByProjectIdentifierAndStatus(@Param("project_identifier") String project_identifier, @Param("status")int statusProjectTask);

    @Query(value = "SELECT * FROM tasks WHERE project_identifier=:project_identifier AND status =:status", nativeQuery = true)
    List<ProjectTask> findAllByProjectIdentifierAndStatusL(@Param("project_identifier") String project_identifier, @Param("status")int statusProjectTask);

    @Modifying
    @Query (value = "UPDATE tasks SET  status=3 WHERE Id=:id AND project_identifier=:project_identifier",nativeQuery = true)
    void deleteProjectTaskBy(@Param("project_identifier") String project_identifier, @Param("id")int id);

}