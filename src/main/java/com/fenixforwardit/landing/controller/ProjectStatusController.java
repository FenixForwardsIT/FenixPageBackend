package com.fenixforwardit.landing.controller;

import com.fenixforwardit.landing.exception.ProjectStatusNotFoundException;
import com.fenixforwardit.landing.model.ProjectStatus;
import com.fenixforwardit.landing.repository.ProjectStatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Walter Finkbeiner
 */
@RestController
@RequestMapping("/project-status")
public class ProjectStatusController {

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @GetMapping
    public @ResponseBody
    Iterable<ProjectStatus> getAllProjectStatuses() {
        return projectStatusRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProjectStatus getOneProjectStatus(@PathVariable Integer id) {
        return projectStatusRepository.findById(id)
                .orElseThrow(() -> new ProjectStatusNotFoundException(id));
    }

    @PostMapping
    public @ResponseBody
    ProjectStatus addNewProjectStatus(@RequestBody ProjectStatus newProjectStatus) {
        return projectStatusRepository.save(newProjectStatus);
    }

    @PutMapping("/{id}")
    public ProjectStatus updateProjectStatus(@RequestBody ProjectStatus newProjectStatus, @PathVariable Integer id) {
        return projectStatusRepository.findById(id)
                .map(projectStatus -> {
                    BeanUtils.copyProperties(newProjectStatus, projectStatus, "id");
                    return projectStatusRepository.save(projectStatus);
                })
                .orElseThrow(() -> new ProjectStatusNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProjectStatus(@PathVariable("id") Integer id) {
        projectStatusRepository.deleteById(id);
    }
}