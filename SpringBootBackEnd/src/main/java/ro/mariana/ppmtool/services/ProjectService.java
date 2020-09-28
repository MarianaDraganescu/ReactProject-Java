package ro.mariana.ppmtool.services;

import org.springframework.stereotype.Service;
import ro.mariana.ppmtool.domain.Backlog;
import ro.mariana.ppmtool.domain.Project;
import ro.mariana.ppmtool.exceptions.ProjectIdException;
import ro.mariana.ppmtool.repositories.BacklogRepository;
import ro.mariana.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    public ProjectService(final ProjectRepository projectRepository,final BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier());
            }
            if(project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(getProjectIdentifierUpperCase(project.getProjectIdentifier())));
            }

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

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId);

        if(project == null){
            throw new ProjectIdException("Project with Identifier'" + projectId + "' does not exist");
        }

        projectRepository.delete(project);
    }

    private String getProjectIdentifierUpperCase(String identifier){
        Project project = projectRepository.findByProjectIdentifier(identifier);
        return project.getProjectIdentifier().toUpperCase();
    }
}
