package program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import program.entity.FoodPackageDiscount;

@RepositoryRestResource
public interface IFoodPackageDiscountRepo extends JpaRepository<FoodPackageDiscount,Long>,JpaSpecificationExecutor<FoodPackageDiscount> {

}
