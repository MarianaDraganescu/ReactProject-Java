package ro.mariana.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.mariana.ppmtool.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
