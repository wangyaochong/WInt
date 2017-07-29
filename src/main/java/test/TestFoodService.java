package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import program.entity.BranchGroup;
import program.entity.Category;
import program.entity.Food;
import program.entity.enums.EnumRole;
import program.repository.IBranchGroupRepo;
import program.repository.ICategoryRepo;
import program.repository.IEmployeeRepo;
import program.repository.IFoodRepo;
import program.service.AppInitService;
import program.service.GenericService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:hibernate.xml","classpath:spring.xml"})
public class TestFoodService {
    @Resource
    IBranchGroupRepo branchGroupRepo;
    @Resource
    IFoodRepo foodRepo;
    @Resource
    IEmployeeRepo employeeRepo;


    @Test
    public void testQueryFoodsByBranchGroup(){
        BranchGroup branchGroup=new BranchGroup();
        branchGroup.setName(AppInitService.nameStoreWangFuJing);
        BranchGroup one = branchGroupRepo.findOne(Example.of(branchGroup, ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase()));
        System.out.println("branchGroup："+one);
        List<Food> foods = foodRepo.queryFoodsByBranchGroup(one);
        System.out.println(foods);
    }
    @Test
    public void testQueryEmployeesByBranchGroupAndRole(){
        BranchGroup branchGroup=new BranchGroup();
        branchGroup.setName(AppInitService.nameStoreWangFuJing);
        BranchGroup one = branchGroupRepo.findOne(Example.of(branchGroup, ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase()));
        System.out.println(employeeRepo.queryEmployeesByBranchGroupAndRole(one, EnumRole.CASHIER.toString()));
    }




    @Resource
    ICategoryRepo categoryRepo;
    @Resource
    GenericService<IFoodRepo,Food> foodService;
    @Test
    public void testFood(){
        Food food=new Food();
        Category category=new Category();
        category.setName("2");
//        Category category2=new Category();
//        category2.setName("1");
        ArrayList<Category> categories=new ArrayList<>();
        categories.add(category);
//        categoryRepo.save(category);
//        categoryRepo.save(category2);
//        categories.add(category);
//        categories.add(category2);
//        food.setCategories(categories);
        System.out.println(food);
//        food.setName("1");
//        foodService.getRepo().saveAndFlush(food);

        System.out.println(foodService.queryPage(food, 0, 2, null, null).getContent());


    }

}
