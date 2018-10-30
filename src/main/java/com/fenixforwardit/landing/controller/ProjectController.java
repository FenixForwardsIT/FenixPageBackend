package com.fenixforwardit.landing.controller;

import com.fenixforwardit.landing.exception.ProjectNotFoundException;
import com.fenixforwardit.landing.exception.ProjectStatusNotFoundException;
import com.fenixforwardit.landing.exception.ProjectTypeNotFoundException;
import com.fenixforwardit.landing.model.Project;
import com.fenixforwardit.landing.model.ProjectStatus;
import com.fenixforwardit.landing.model.ProjectType;
import com.fenixforwardit.landing.repository.ProjectRepository;
import com.fenixforwardit.landing.repository.ProjectStatusRepository;
import com.fenixforwardit.landing.repository.ProjectTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Walter Finkbeiner
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectTypeRepository projectTypeRepository;
    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @GetMapping
    public @ResponseBody
    Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project getOneProject(@PathVariable Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @PostMapping
    public @ResponseBody
    Project addNewProject(@RequestBody Project newProject) {
        Integer projectTypeId;
        Integer projectStatusId;
        ProjectType projectType = null;
        ProjectStatus projectStatus = null;
        if (newProject.getProjectType() != null && newProject.getProjectType().getId() != null) {
            projectTypeId = newProject.getProjectType().getId();
            projectType = projectTypeRepository.findById(projectTypeId).orElseThrow(() -> new ProjectTypeNotFoundException(projectTypeId));
        }
        if (newProject.getProjectStatus() != null && newProject.getProjectStatus().getId() != null) {
            projectStatusId = newProject.getProjectStatus().getId();
            projectStatus = projectStatusRepository.findById(projectStatusId).orElseThrow(() -> new ProjectStatusNotFoundException(projectStatusId));
        }
        newProject.setProjectType(projectType);
        newProject.setProjectStatus(projectStatus);
        return projectRepository.save(newProject);
    }

    @PutMapping("/{id}")
    public Project updateProject(@RequestBody Project newProject, @PathVariable Integer id) {
        return projectRepository.findById(id)
                .map(project -> {
                    Integer projectTypeId;
                    Integer projectStatusId;
                    ProjectType newProjectType = null;
                    ProjectStatus newProjectStatus = null;
                    if (newProject.getProjectType() != null && newProject.getProjectType().getId() != null) {
                        projectTypeId = newProject.getProjectType().getId();
                        newProjectType = projectTypeRepository.findById(projectTypeId).orElseThrow(() -> new ProjectTypeNotFoundException(projectTypeId));
                    }
                    if (newProject.getProjectStatus() != null && newProject.getProjectStatus().getId() != null) {
                        projectStatusId = newProject.getProjectStatus().getId();
                        newProjectStatus = projectStatusRepository.findById(projectStatusId).orElseThrow(() -> new ProjectStatusNotFoundException(projectStatusId));

                    }
                    BeanUtils.copyProperties(newProject, project, "id", "projectType", "projectStatus");
                    project.setProjectStatus(newProjectStatus);
                    project.setProjectType(newProjectType);
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Integer id) {
        projectRepository.deleteById(id);
    }
}