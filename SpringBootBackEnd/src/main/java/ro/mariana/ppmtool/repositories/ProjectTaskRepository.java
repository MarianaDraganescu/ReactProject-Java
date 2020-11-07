package ro.mariana.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.mariana.ppmtool.domain.ProjectTask;

import java.util.List;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String pt_id);
}
