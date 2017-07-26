package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.BranchGroup;
import program.entity.Employee;
import program.repository.projection.Employee_BranchGroup;

import java.util.List;

@RepositoryRestResource(excerptProjection = Employee_BranchGroup.class)
public interface IEmployeeRepo extends JpaRepository<Employee,Long>,JpaSpecificationExecutor<Employee>{
    List<Employee> queryEmployeesByBranchGroupAndRole(BranchGroup branchGroup,String role);
}
