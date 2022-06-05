package com.example.parcialone.persistence.repository;

import com.example.parcialone.persistence.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Modifying
    @Query (value = "UPDATE projects SET end_date=:endDate WHERE ID=:id",nativeQuery = true)
    void finishedProject(@Param("id") Long id,@Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT * FROM projects WHERE project_name=:project_name", nativeQuery = true)
    Project findByProjectName(@Param("project_name") String project_name);

    @Query(value = "SELECT * FROM projects WHERE project_identifier=:project_identifier", nativeQuery = true)
    Project findByProjectIdentifier(@Param("project_identifier") String project_identifier);

}