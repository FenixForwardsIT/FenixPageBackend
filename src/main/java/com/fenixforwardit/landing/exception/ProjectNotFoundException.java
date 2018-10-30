package com.fenixforwardit.landing.exception;

/**
 * @author Walter Finkbeiner
 */
public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Integer id) {
        super("Could not find project with ID = " + id);
    }
}
