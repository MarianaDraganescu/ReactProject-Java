package ro.mariana.ppmtool.services;

import org.springframework.stereotype.Service;
import ro.mariana.ppmtool.domain.Project;
import ro.mariana.ppmtool.exceptions.ProjectIdException;
import ro.mariana.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID : " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project with Identifier ' " + projectIdentifier + "' does not exist");
        }
        return project;
    }
}
