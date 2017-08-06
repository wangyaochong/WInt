package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.BranchGroup;
import program.entity.ProductInstance;

import java.util.List;

@RepositoryRestResource
public interface IProductInstanceRepo  extends JpaRepository<ProductInstance,String>,JpaSpecificationExecutor<ProductInstance>{
    List<ProductInstance> findAllByBranchGroup(BranchGroup branchGroup);


    @Query(value = "select count(*)/:dayCount from productinstance where branchGroup_id=:branchGroupId  and name like :name and datediff(DATE_FORMAT(now(),'%Y-%c-%d'),DATE_FORMAT(outDate,'%Y-%c-%d'))<:dayCount ",nativeQuery = true)
    Double getAverageConsumeForOneDayInBranchByName(@Param("branchGroupId")Long branchGroupId, @Param("dayCount") Integer dayCount,@Param("name") String name);

}
