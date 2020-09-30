package ro.mariana.ppmtool.services;

import org.springframework.stereotype.Service;
import ro.mariana.ppmtool.domain.Backlog;
import ro.mariana.ppmtool.domain.ProjectTask;
import ro.mariana.ppmtool.repositories.BacklogRepository;
import ro.mariana.ppmtool.repositories.ProjectTaskRepository;


@Service
public class ProjectTaskService {


    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;

    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
    }

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){


        //Exceptions: Project not found

        //PTs to be added to a specific backlog(automatically to project), project != null, BL exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        //set the bl to pt
        projectTask.setBacklog(backlog);
        //we want our project/backlog sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
        Integer BacklogSequence = backlog.getPTSequence();
        // Update the BL SEQUENCE
        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);
        //Add Sequence to Project Task
        projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //INITIAL priority when priority null
       if(projectTask.getPriority()==null){
          projectTask.setPriority(3);
       }
        //INITIAL status when status is null

        if(projectTask.getStatus()=="" || projectTask.getStatus()==null){
            projectTask.setStatus("TODO");
        }

        return projectTaskRepository.save(projectTask);
    }
}
