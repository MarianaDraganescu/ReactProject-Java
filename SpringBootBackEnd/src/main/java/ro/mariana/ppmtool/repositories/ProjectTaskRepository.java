package ro.mariana.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.mariana.ppmtool.domain.ProjectTask;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
}
