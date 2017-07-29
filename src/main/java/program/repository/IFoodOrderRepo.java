package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.FoodOrder;

import java.util.List;

@RepositoryRestResource
public interface IFoodOrderRepo extends JpaRepository<FoodOrder,Long>,JpaSpecificationExecutor<FoodOrder> {

    @Query(nativeQuery = true,value = "SELECT DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d') as'date'  ,count(*)   as number  FROM `foodorder` where branchGroup_id=:branchGroupId group by DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d'),branchGroup_id")
    List<Object[]> getDateAndOrderCountListByBranchGroup(@Param("branchGroupId") String branchGroupId);


}
