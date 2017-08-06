package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import program.entity.Customer;

@RepositoryRestResource
public interface ICustomerRepo extends JpaRepository<Customer,String>,JpaSpecificationExecutor<Customer> {

    @RestResource(path = "findByName")
    Customer findByName(@Param("name") String name);
}
