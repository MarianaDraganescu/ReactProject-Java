package ro.mariana.ppmtool.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.mariana.ppmtool.domain.ProjectTask;
import ro.mariana.ppmtool.services.MapValidationErrorService;
import ro.mariana.ppmtool.services.ProjectTaskService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/backlogs")
public class BacklogController {


    private final ProjectTaskService projectTaskService;
    private final MapValidationErrorService mapValidationErrorService;

    public BacklogController(ProjectTaskService projectTaskService,
                             MapValidationErrorService mapValidationErrorService) {
        this.projectTaskService = projectTaskService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("/{backlog_id}") //the same as project_id
    public ResponseEntity<?> createAddProjectTask(@Valid @RequestBody final ProjectTask projectTask,
                                                  BindingResult result, @PathVariable String backlog_id) {

        ResponseEntity<?> mapError = mapValidationErrorService.mapValidation(result);
        if (mapError != null) return mapError;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);

        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<Iterable<ProjectTask>> getProjectBacklog(@PathVariable String backlog_id) {
        return new ResponseEntity(projectTaskService.findBacklogById(backlog_id), HttpStatus.OK);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id,pt_id);
        return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
    }

    @PutMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody final ProjectTask projectTask, BindingResult bindingResult,
                                               @PathVariable String backlog_id,
                                               @PathVariable String pt_id){
        ResponseEntity<?> mapError = mapValidationErrorService.mapValidation(bindingResult);
        if (mapError != null) return mapError;

        ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask,backlog_id,pt_id);
        return new ResponseEntity<ProjectTask>(updatedTask,HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
        projectTaskService.deletePTByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<String>("Project Task "+pt_id+" was deleted successfully", HttpStatus.OK);
    }

}
