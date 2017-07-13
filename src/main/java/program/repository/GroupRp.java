package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import program.entity.Group;


@Repository
public interface GroupRp extends JpaRepository<Group,String> {

}
