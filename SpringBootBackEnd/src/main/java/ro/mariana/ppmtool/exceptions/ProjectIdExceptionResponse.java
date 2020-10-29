package ro.mariana.ppmtool.exceptions;

public class ProjectIdExceptionResponse {

    private final String projectIdentifier;


    public ProjectIdExceptionResponse(String projectIdentifier) {

        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {

        return projectIdentifier;
    }

}
