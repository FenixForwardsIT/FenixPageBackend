package com.fenixforwardit.landing.repository;

import com.fenixforwardit.landing.model.ProjectStatus;
import com.fenixforwardit.landing.model.ProjectType;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Walter Finkbeiner
 */
public interface ProjectStatusRepository extends CrudRepository<ProjectStatus, Integer> {


}
