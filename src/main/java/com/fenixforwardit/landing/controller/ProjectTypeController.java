package com.fenixforwardit.landing.controller;

import com.fenixforwardit.landing.exception.ProjectTypeNotFoundException;
import com.fenixforwardit.landing.model.ProjectType;
import com.fenixforwardit.landing.repository.ProjectTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Walter Finkbeiner
 */
@RestController
@RequestMapping("/project-type")
public class ProjectTypeController {

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @GetMapping
    public @ResponseBody
    Iterable<ProjectType> getAllProjectTypes() {
        return projectTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProjectType getOneProjectType(@PathVariable Integer id) {
        return projectTypeRepository.findById(id)
                .orElseThrow(() -> new ProjectTypeNotFoundException(id));
    }

    @PostMapping
    public @ResponseBody
    ProjectType addNewProjectType(@RequestBody ProjectType newProjectType) {
        return projectTypeRepository.save(newProjectType);
    }

    @PutMapping("/{id}")
    public ProjectType updateProjectType(@RequestBody ProjectType newProjectType, @PathVariable Integer id) {
        return projectTypeRepository.findById(id)
                .map(projectType -> {
                    BeanUtils.copyProperties(newProjectType, projectType, "id");
                    return projectTypeRepository.save(projectType);
                })
                .orElseThrow(() -> new ProjectTypeNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProjectType(@PathVariable("id") Integer id) {
        projectTypeRepository.deleteById(id);
    }
}