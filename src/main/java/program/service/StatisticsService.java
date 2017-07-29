package program.service;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import program.bean.*;
import program.bean.resultTransformer.TowColTransformer;
import program.entity.BranchGroup;
import program.entity.Food;
import program.repository.IBranchGroupRepo;
import program.repository.IFoodRepo;
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
        Query nativeQuery = entityManager.createNativeQuery("SELECT DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d')  ,count(*)    FROM `foodorder` where branchGroup_id=:branchGroupId group by DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d'),branchGroup_id");
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

    public List<BranchGroupAndMap> getAllBranchGroupDateAndFoodCountList(){
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
