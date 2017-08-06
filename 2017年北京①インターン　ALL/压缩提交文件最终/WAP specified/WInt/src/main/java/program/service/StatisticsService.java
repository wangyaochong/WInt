package program.service;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import program.bean.*;
import program.bean.resultTransformer.TowColTransformer;
import program.entity.*;
import program.repository.*;
import program.service.bean.SqlQueryBean;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Service
public class StatisticsService {
    @Resource
    IBranchGroupRepo branchGroupRepo;
    @Resource
    IFoodRepo foodRepo;
    @Resource
    IProductInstanceRepo productInstanceRepo;
    @Resource
    IProductRepo productRepo;
    @Resource
    ICategoryRepo categoryRepo;

    @Resource
    EntityManager entityManager;
    public List<BranchGroupAndList> getAllBranchGroupDateAndOrderCountList(){
        List<BranchGroup> all = branchGroupRepo.findAll();
        List<BranchGroupAndList> result=new ArrayList<>();
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            result.add(new BranchGroupAndList(branchGroup,getDateAndOrderAmountByBranchGroup(branchGroup)));
        });
        return result;
    }
    public List<BranchGroupAndList> getAllBranchGroupDateAndOrderAmountList(){
        List<BranchGroupAndList> result=new ArrayList<>();
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            result.add(new BranchGroupAndList(branchGroup,getDateAndOrderCountListByBranchGroup(branchGroup)));
        });
        return result;
    }

    public List<BranchGroupAndList> getAllBranchGroupFoodSellingCash(){
        List<BranchGroupAndList> result=new ArrayList<>();
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            result.add(new BranchGroupAndList(branchGroup,getFoodSellingCashByBranchGroup(branchGroup)));
        });
        return result;
    }
    public List<BranchGroupAndList> getAllBranchGroupFoodSellingCashByDayCount(Integer dayCount){
        List<BranchGroupAndList> result=new ArrayList<>();
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            result.add(new BranchGroupAndList(branchGroup,getFoodSellingCashByBranchGroupAndDayCount(branchGroup,dayCount)));
        });
        return result;
    }
    public List<StringAndDouble> getFoodSellingCashGlobal(){
        Query nativeQuery = entityManager.createNativeQuery(SqlQueryBean.getFoodSellingCashGlobal);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndDouble.class));
        List<StringAndDouble> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<StringAndDouble> getFoodSellingCashGlobalByDayCount(Integer dayCount){
        Query nativeQuery = entityManager.createNativeQuery(SqlQueryBean.getFoodSellingCashGlobalByDayCount);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndDouble.class));
        nativeQuery.setParameter("dayCount",dayCount);
        List<StringAndDouble> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<StringAndDouble> getFoodSellingCashByBranchGroup(BranchGroup branchGroup){
        Query nativeQuery = entityManager.createNativeQuery(SqlQueryBean.getFoodSellingCashByBranchGroup);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndDouble.class));
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        List<StringAndDouble> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<StringAndDouble> getFoodSellingCashByBranchGroupAndDayCount(BranchGroup branchGroup,Integer dayCount){
        Query nativeQuery = entityManager.createNativeQuery(SqlQueryBean.getFoodSellingCashByBranchGroupAndDayCount);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndDouble.class));
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        nativeQuery.setParameter("dayCount",dayCount);
        List<StringAndDouble> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<StringAndNumber> getDateAndOrderCountListByBranchGroup(BranchGroup branchGroup){
        Query nativeQuery = entityManager.createNativeQuery(SqlQueryBean.getDateAndOrderCountListByBranchGroup);
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndDouble.class));
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        List<StringAndNumber> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<StringAndNumber> getDateAndOrderAmountByBranchGroup(BranchGroup branchGroup){
        Query nativeQuery = entityManager.createNativeQuery("SELECT DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d')  ,count(*)    FROM `foodorder` where orderType not like 'SYSTEM_FOR_PREPARATION' and  branchGroup_id=:branchGroupId group by DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d'),branchGroup_id");
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndNumber.class));
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        List<StringAndNumber> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<StringAndNumber> getDateAndFoodCountListByBranchGroupAndFoodName(BranchGroup branchGroup, String foodName){
            Query nativeQuery = entityManager.createNativeQuery("SELECT DATE_FORMAT(createTime,'%Y/%c/%d')  ,sum(count)  FROM `foodinstance` where branchGroup_id=:branchGroupId and name like :name group by DATE_FORMAT(createTime,'%Y/%c/%d') ");
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndNumber.class));
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        nativeQuery.setParameter("name",foodName);
        List<StringAndNumber> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<StringAndNumber> getDateAndFoodCountListByBranchGroupAndFoodCategory(BranchGroup branchGroup, Category category){
        Query nativeQuery = entityManager.createNativeQuery("SELECT DATE_FORMAT(createTime,'%Y/%c/%d')  ,sum(count)  FROM `foodinstance` where branchGroup_id=:branchGroupId and category_id=:categoryId group by DATE_FORMAT(createTime,'%Y/%c/%d') ");
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndNumber.class));
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        nativeQuery.setParameter("categoryId",category.getId());
        List<StringAndNumber> resultList = nativeQuery.getResultList();
        return resultList;
    }


    public List<StringAndNumber> getDateAndProductInstanceCountListByBranchGroupName(BranchGroup branchGroup,String productName){
        Query nativeQuery = entityManager.createNativeQuery("SELECT DATE_FORMAT(createTime,'%Y/%c/%d')  ,count(*)  FROM `productinstance` where branchGroup_id=:branchGroupId and name like :name group by DATE_FORMAT(createTime,'%Y/%c/%d') ");
        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndNumber.class));
        nativeQuery.setParameter("branchGroupId",branchGroup.getId());
        nativeQuery.setParameter("name",productName);
        List<StringAndNumber> resultList = nativeQuery.getResultList();
        return resultList;
    }
    public List<BranchGroupAndMap> getAllBranchGroupDateAndProductInstanceCountList(){
        List<BranchGroupAndMap> result=new ArrayList<>();
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            List<ProductInstance> branchGroupProductInstance = productInstanceRepo.findAllByBranchGroup(branchGroup);
            Map<String,List<StringAndNumber>> foodCountMap=new HashMap<>();
            ArrayList<String> mapKeys=new ArrayList<>();
            branchGroupProductInstance.forEach(productInstance -> {
                List<StringAndNumber> dateAndOrderAmountListByBranchGroupAndFoodName = getDateAndProductInstanceCountListByBranchGroupName(branchGroup, productInstance.getName());
                mapKeys.add(productInstance.getName());
                foodCountMap.put(productInstance.getName(), dateAndOrderAmountListByBranchGroupAndFoodName);
            });
            result.add(new BranchGroupAndMap(branchGroup,foodCountMap,mapKeys));
        });
        return result;
    }
    public MapAndMapKeys globalDateAndFoodCategoryCountList(){

        return null;
    }
    public List<BranchGroupAndMap> branchGroupDateAndFoodCategoryCountList(){
        List<BranchGroupAndMap> result=new ArrayList<>();
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            List<Category> categories = categoryRepo.findAllByBranchGroup(branchGroup);
            Map<String,List<StringAndNumber>> foodCountMap=new HashMap<>();
            ArrayList<String> mapKeys=new ArrayList<>();
            categories.forEach(category -> {
                List<StringAndNumber> dateAndOrderAmountListByBranchGroupAndFoodName = getDateAndFoodCountListByBranchGroupAndFoodCategory(branchGroup, category);
                mapKeys.add(category.getName());
                foodCountMap.put(category.getName(), dateAndOrderAmountListByBranchGroupAndFoodName);
            });
            result.add(new BranchGroupAndMap(branchGroup,foodCountMap,mapKeys));
        });
        return result;
    }
    public List<BranchGroupAndMap> branchGroupDateAndFoodCountList(){
        List<BranchGroupAndMap> result=new ArrayList<>();
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            List<Food> foods = foodRepo.queryFoodsByBranchGroup(branchGroup);
            Map<String,List<StringAndNumber>> foodCountMap=new HashMap<>();
            ArrayList<String> mapKeys=new ArrayList<>();
            foods.forEach(food -> {
                List<StringAndNumber> dateAndOrderAmountListByBranchGroupAndFoodName = getDateAndFoodCountListByBranchGroupAndFoodName(branchGroup, food.getName());
                mapKeys.add(food.getName());
                foodCountMap.put(food.getName(), dateAndOrderAmountListByBranchGroupAndFoodName);
            });
            result.add(new BranchGroupAndMap(branchGroup,foodCountMap,mapKeys));
        });
        return result;
    }
    public MapAndMapKeys globalDateAndProductInstanceCountList(){
        Set<String> productNames=new HashSet<>();
        branchGroupRepo.findAll().forEach(branchGroup -> {
            List<Product> branchProduct = productRepo.findAllByBranchGroup(branchGroup);
            for (Product product : branchProduct) {
                productNames.add(product.getName());
            }
        });
        MapAndMapKeys<StringAndNumber> result=new MapAndMapKeys();
        result.setMap(new HashMap<>());
        result.setMapKeys(new ArrayList<>());
        for (Object name : productNames.toArray()) {
            Query nativeQuery = entityManager.createNativeQuery("SELECT DATE_FORMAT(outDate,'%Y/%c/%d')  ,count(*)    FROM `productinstance` where name like :name group by DATE_FORMAT(outDate,'%Y/%c/%d') ");
            nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndNumber.class));
            nativeQuery.setParameter("name",name);
            List<StringAndNumber> resultList = nativeQuery.getResultList();
            result.getMapKeys().add((String) name);
            result.getMap().put((String) name,resultList);
        }
        return result;
    }
    public MapAndMapKeys globalDateAndFoodCountList(){
        Set<String> foodNameSet=new HashSet<>();
        branchGroupRepo.findAll().forEach(branchGroup -> {
            List<Food> foods = foodRepo.queryFoodsByBranchGroup(branchGroup);
            for (Food food : foods) {
                foodNameSet.add(food.getName());
            }
        });
        MapAndMapKeys<StringAndNumber> result=new MapAndMapKeys();
        result.setMap(new HashMap<>());
        result.setMapKeys(new ArrayList<>());
        for (Object name : foodNameSet.toArray()) {
            Query nativeQuery = entityManager.createNativeQuery("SELECT DATE_FORMAT(createTime,'%Y/%c/%d')  ,sum(count)    FROM `foodinstance` where name like :name group by DATE_FORMAT(createTime,'%Y/%c/%d') ");
            nativeQuery.unwrap(SQLQuery.class).setResultTransformer(new TowColTransformer(StringAndNumber.class));
            nativeQuery.setParameter("name",name);
            List<StringAndNumber> resultList = nativeQuery.getResultList();
            result.getMapKeys().add((String) name);
            result.getMap().put((String) name,resultList);
        }
        return result;
    }
}
