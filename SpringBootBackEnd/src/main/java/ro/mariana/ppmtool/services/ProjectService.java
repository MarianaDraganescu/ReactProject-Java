package ro.mariana.ppmtool.services;

import org.springframework.stereotype.Service;
import ro.mariana.ppmtool.domain.Project;
import ro.mariana.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project){
        return projectRepository.save(project);
    }
}
