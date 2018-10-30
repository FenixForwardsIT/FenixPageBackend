package com.fenixforwardit.landing.exception;

/**
 * @author Walter Finkbeiner
 */
public class ProjectTypeNotFoundException extends RuntimeException {

    public ProjectTypeNotFoundException(Integer id) {
        super("Could not find project type with ID = " + id);
    }
}
