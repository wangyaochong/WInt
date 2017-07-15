package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.Employee;

@RepositoryRestResource
public interface IEmployeeRepo extends JpaRepository<Employee,String>,JpaSpecificationExecutor<Employee>{


}
