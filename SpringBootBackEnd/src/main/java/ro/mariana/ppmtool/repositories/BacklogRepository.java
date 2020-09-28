package ro.mariana.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.mariana.ppmtool.domain.Backlog;

public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String identifier);
}
