package com.fenixforwardit.landing.exception;

/**
 * @author Walter Finkbeiner
 */
public class ProjectStatusNotFoundException extends RuntimeException {

    public ProjectStatusNotFoundException(Integer id) {
        super("Could not find project status with ID = " + id);
    }
}
