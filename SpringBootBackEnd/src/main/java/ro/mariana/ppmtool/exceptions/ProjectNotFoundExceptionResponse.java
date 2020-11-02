package ro.mariana.ppmtool.exceptions;

public class ProjectNotFoundExceptionResponse {

    private final String projectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return projectNotFound;
    }
}
