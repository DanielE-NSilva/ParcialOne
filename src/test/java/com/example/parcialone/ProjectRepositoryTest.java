package com.example.parcialone;

import com.example.parcialone.persistence.entity.Project;
import com.example.parcialone.persistence.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void when_findByProjectName_return_Project(){
        Project project = Project.builder()
                .projectName("test")
                .projectIdentifier("test01")
                .description("this is test")
                .startDate(LocalDateTime.now())
                .endDate(null).build();
        this.projectRepository.save(project);
        Project projectResul = this.projectRepository.findByProjectName("test");
        Assertions.assertThat(projectResul.getProjectName().equals(project.getProjectName()));
    }

}
