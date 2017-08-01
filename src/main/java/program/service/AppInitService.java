package program.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ServletContextAware;
import program.entity.*;
import program.entity.enums.EnumGender;
import program.entity.enums.EnumOrderStatus;
import program.entity.enums.EnumOrderType;
import program.entity.enums.EnumRole;
import program.repository.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class AppInitService implements ServletContextAware {
    @Resource
    IGlobalConfigRepo globalConfigRepo;
    @Resource
    IBranchGroupRepo branchGroupRepo;
    @Resource
    ICustomerRepo customerRepo;
    @Resource
    IEmployeeRepo employeeRepo;
    @Resource
    ICategoryRepo categoryRepo;
    @Resource
    IFoodRepo foodRepo;
    @Resource
    IProductRepo productRepo;
    @Resource
    IFoodInstanceRepo foodInstanceRepo;
    @Resource
    IFoodOrderRepo foodOrderRepo;
    @Resource
    FoodInstanceService foodInstanceService;
    @Resource
    IFoodPackageDiscountRepo foodPackageDiscountRepo;
    @Resource
    IProductInstanceRepo productInstanceRepo;
    @Resource
    IEquipmentAssetRepo equipmentAssetRepo;
    public static final String nameStoreWangFuJing = "Wang Fu Jing Store";
    public static final String nameStoreDongSi = "Dong Si Street Store";
    public static final String nameStoreYongHeGong = "Yong He Gong Store";
    public static final String nameStoreTianAnMen="Tian An Men Store";
    public static final String nameStoreXiDanJoyCity="Xi Dan Joy City Store";
    public static final String nameStoreBeiJingRailStation="Bei Jing Rail Station Store";
    public static final int foodDiscount =0;
    final String imageBasePath = "/WInt/dist/img/餐厅图片/";
    final Integer totalDataDayCount = 31;
    public static  boolean initFinished=false;
    @Override
    public void setServletContext(ServletContext servletContext) {
        if (ObjectUtils.isEmpty(globalConfigRepo.findAll())) {//如果没有初始化数据
            System.out.println("进行全局初始化");
            initGlobalConfig();
            System.out.println("完成全局初始化");
            System.out.println("进行分店初始化");
            initBranchGroup();
            System.out.println("完成分店初始化");
            System.out.println("进行顾客信息初始化");
            initCustomer();
            System.out.println("完成顾客信息初始化");
            System.out.println("进行员工信息初始化");
            initEmployee();
            System.out.println("完成员工信息初始化");
            System.out.println("进行食物菜单初始化");
            initFood();
            System.out.println("完成食物菜单初始化");
            System.out.println("进行库存初始化");
            initProduct();
            System.out.println("完成库存初始化");
            System.out.println("进行订单初始化");
            initOrder();
            System.out.println("完成订单初始化");
            System.out.println("进行食品销售额初始化");
            initFoodMonthlySellNumber();
            System.out.println("完成食品销售额初始化");
            System.out.println("进行打包折扣初始化");
            initPackageDiscount();
            System.out.println("完成打包折扣初始化");
            System.out.println("进行库存消耗历史初始化");
            initProductInstance();
            System.out.println("完成库存消耗历史初始化");
            System.out.println("进行设备资产初始化");
            initEquipmentAsset();
            System.out.println("完成设备资产初始化");
        }
        initFinished=true;
    }
    public Food getFishBurgerFromFoodList(List<Food> foodList){
        for(int i=0;i<foodList.size();i++){
            if(foodList.get(i).getName().equals("Fish burger")){
                return foodList.get(i);
            }
        }
        System.out.println("Fish burger not found");
        return null;
    }
    public void initOneStoreOrder(
            String storeName,//需要初始化的商店的名字
            Integer foodCountPerOrder,//每单平均点餐个数
            Integer foodCountFluctuation,//每单点餐个数震动幅度
            Integer initOrderCountEveryDay,//初始每单的数量
            Integer orderCountGrowthEveryDay,//后续每单的递增量
            Integer orderCountFluctuation) {//每单震动幅度
        BranchGroup branchToSet = branchGroupRepo.queryBranchGroupByName(storeName);
        List<Food> foodList = foodRepo.queryFoodsByBranchGroup(branchToSet);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -totalDataDayCount +1);
        Date orderCreateDate = calendar.getTime();
        Integer totalOrderCount = 0;
        for (int i = 0; i < totalDataDayCount; i++) {
            Integer tempOrderCount = initOrderCountEveryDay + orderCountFluctuation / 2 - RandomUtils.nextInt(0, orderCountFluctuation + 1);
            while (tempOrderCount > 0) {
                List<FoodInstance> foodInstanceList = foodInstanceService.randomCreateFoodInstanceFromFoodList(foodList, foodCountPerOrder, foodCountFluctuation,orderCreateDate);//每一个订单都是不同的食物列表
                List<Employee> employees = employeeRepo.queryEmployeesByBranchGroupAndRole(branchToSet, EnumRole.CASHIER.toString());
                Employee employee = employees.get(RandomUtils.nextInt(0, employees.size()));
                FoodOrder foodOrder = new FoodOrder(null, null, employee, foodInstanceList, orderCreateDate, null, foodInstanceService.getTotalFoodListPrice(foodInstanceList), false, EnumOrderType.STORE.toString(), EnumOrderStatus.DELIVERED.toString(), branchToSet);
                foodOrderRepo.save(foodOrder);
                tempOrderCount--;
                totalOrderCount++;
//                System.out.println("orders created："+totalOrderCount);
            }
            initOrderCountEveryDay += orderCountGrowthEveryDay;
            calendar.add(Calendar.DATE,1);
            orderCreateDate=calendar.getTime();
        }
        foodInstanceService.setCurrentFoodIndex(foodInstanceService.getCurrentFoodIndex()+1);
    }
    public void initBranchGroup() {
        if (ObjectUtils.isEmpty(branchGroupRepo.findAll())) {
            branchGroupRepo.save(new BranchGroup(nameStoreDongSi, "store No.1", "海淀区花园路2号翠微大厦牡丹园店1楼(近牡丹园地铁站西北口)", new Date().getTime(), foodDiscount));
            branchGroupRepo.save(new BranchGroup(nameStoreTianAnMen, "store No.2", "北京市海淀区双清路30号", new Date().getTime(), foodDiscount));
            branchGroupRepo.save(new BranchGroup(nameStoreWangFuJing, "store No.3", "王府井大街99号首层、地下一层", new Date().getTime(), foodDiscount));
            branchGroupRepo.save(new BranchGroup(nameStoreYongHeGong, "store No.4", "雍和宫西南角翻建商业A区1层", new Date().getTime(), foodDiscount));
            branchGroupRepo.save(new BranchGroup(nameStoreBeiJingRailStation, "store No.5", "北京市东城区北京火车站西街北京站站前广场西侧", new Date().getTime(), foodDiscount));
            branchGroupRepo.save(new BranchGroup(nameStoreXiDanJoyCity, "store No.6", "北京市西城区西单横二条59号西单明珠商品市场地下1层", new Date().getTime(), foodDiscount));
//            initCategory(save);
            for (BranchGroup branchGroup : branchGroupRepo.findAll()) {
                initCategory(branchGroup);
            }
        }
    }
    public void initOrder() {
        Integer initOrderCount=150;
        initOneStoreOrder(nameStoreWangFuJing, 6, 4, initOrderCount, 3, 30);
        initOneStoreOrder(nameStoreDongSi, 6, 6, initOrderCount, 0, 30);
        initOneStoreOrder(nameStoreXiDanJoyCity, 6, 2, initOrderCount, 0, 30);
        initOneStoreOrder(nameStoreTianAnMen, 6, 4, initOrderCount, -1, 30);
        initOneStoreOrder(nameStoreBeiJingRailStation, 6, 4, initOrderCount,6, 30);
        initOneStoreOrder(nameStoreYongHeGong, 6, 4, initOrderCount, -2, 30);
    }
    public void initEquipmentAsset(){
        for (BranchGroup branchGroup : branchGroupRepo.findAll()) {
            equipmentAssetRepo.save(new EquipmentAsset(null,"Freezer",1199d,false,1,imageBasePath+"/资产/冰柜.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Coffee machine",1499d,false,1,imageBasePath+"/资产/咖啡机.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Oven",288d,false,1,imageBasePath+"/资产/烤箱.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Air conditioner",6399d,false,1,imageBasePath+"/资产/立式空调.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Ordering machine",1358d,false,1,imageBasePath+"/资产/点餐机.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Mop",1199d,false,1,imageBasePath+"/资产/拖把.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Tables and chairs",500d,false,1,imageBasePath+"/资产/一套桌椅.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Beverage machine",2399d,false,1,imageBasePath+"/资产/饮料机.jpg",branchGroup));
            equipmentAssetRepo.save(new EquipmentAsset(null,"Fry pan",499d,false,1,imageBasePath+"/资产/炸锅.jpg",branchGroup));
        }
    }
    public void initProductInstance(){//初始化库存消耗数据
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -totalDataDayCount +1);
        Date dataCreateDate = calendar.getTime();
        for (int i = 0; i < totalDataDayCount; i++) {
            for (BranchGroup branchGroup : branchGroupRepo.findAll()) {
                for (Product product : productRepo.findAllByBranchGroup(branchGroup)) {
                    for(int k=0;k<RandomUtils.nextInt(1,5);k++){
                        ProductInstance productInstance=new ProductInstance(product,dataCreateDate);
                        productInstanceRepo.save(productInstance);
                    }
                }
            }
            calendar.add(Calendar.DATE,1);
            dataCreateDate=calendar.getTime();
        }
    }
    public void initProduct() {
        Integer initCount=10;
        Integer countToAlarm=5;
        Integer daysLeftToAlarm=2;
        Integer daysToRecharge=5;
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            calendar.add(Calendar.DATE, 180);
            Date timeToBadDate = calendar.getTime();
            calendar.add(Calendar.DATE, -270);
            Date productionDate = calendar.getTime();
            productRepo.save(new Product(null, "Vinegar",  imageBasePath + "/消耗品/醋.jpg", 20d, productionDate, timeToBadDate,  initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,null,null, branchGroup));
            productRepo.save(new Product(null, "Chicken essence", imageBasePath + "/消耗品/鸡精.jpg", 10d, productionDate, timeToBadDate,  initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,null, null, branchGroup));
            productRepo.save(new Product(null, "Soy sauce", imageBasePath + "/消耗品/酱油.jpg", 13d, productionDate,  timeToBadDate, initCount,countToAlarm,daysLeftToAlarm,daysToRecharge, null,null, branchGroup));
            productRepo.save(new Product(null, "Disposable bag",  imageBasePath + "/消耗品/垃圾袋.jpg", 11.5d, productionDate, timeToBadDate,  initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,  null,null, branchGroup));
            productRepo.save(new Product(null, "Detergent",  imageBasePath + "/消耗品/洗洁精.jpg", 14.9d, productionDate, timeToBadDate, initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,  null,null, branchGroup));
            productRepo.save(new Product(null, "Salt", imageBasePath + "/消耗品/盐.jpg", 3.5d, productionDate, timeToBadDate,  initCount,countToAlarm,daysLeftToAlarm,daysToRecharge, null,null, branchGroup));
            productRepo.save(new Product(null, "One-off chopsticks",  imageBasePath + "/消耗品/一次性筷子.jpg", 12d, productionDate,  timeToBadDate,  initCount,countToAlarm,daysLeftToAlarm,daysToRecharge, null, null,branchGroup));
            productRepo.save(new Product(null, "Disposable gloves", imageBasePath + "/消耗品/一次性手套.jpg", 5.9d, productionDate, timeToBadDate, initCount,countToAlarm,daysLeftToAlarm,daysToRecharge, null,null, branchGroup));
            productRepo.save(new Product(null, "Disposable paper cup",  imageBasePath + "/消耗品/一次性纸杯.jpg", 9.9d, productionDate, timeToBadDate, initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,  null,null, branchGroup));
            productRepo.save(new Product(null, "Tissue", imageBasePath + "/消耗品/纸巾.jpg", 36.9d, productionDate,  timeToBadDate, initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,  null,null, branchGroup));
            productRepo.save(new Product(null, "Cola", imageBasePath + "/消耗品/可乐.jpg", 5.8d, productionDate,  timeToBadDate, initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,  null,null, branchGroup));
            productRepo.save(new Product(null, "Beer",  imageBasePath + "/消耗品/啤酒.jpg", 4.5d, productionDate, timeToBadDate, initCount,countToAlarm,daysLeftToAlarm,daysToRecharge,  null, null,branchGroup));
        });
    }
    public void initFoodMonthlySellNumber(){
        branchGroupRepo.findAll().forEach(branchGroup -> {
            foodRepo.queryFoodsByBranchGroup(branchGroup).forEach(food -> {
                food.setMonthlySellNumber(foodInstanceService.getFoodSellingCountInBranch(branchGroup,food));
                foodRepo.save(food);
            });
        });
    }
    public void initPackageDiscount(){
        branchGroupRepo.findAll().forEach(branchGroup ->{
            List<Food> foods = foodRepo.queryFoodsByBranchGroup(branchGroup);
            List<Food> foodListForOnePerson=new ArrayList<>();
            List<Food> foodListForTwoPerson=new ArrayList<>();
            for (Food food : foods) {
                if(food.getName().equals("Cola cool")){
                    foodListForOnePerson.add(food);
                    foodListForTwoPerson.add(food);
                }
                if(food.getName().equals("Sprite cool")){
                    foodListForTwoPerson.add(food);
                }
                if(food.getName().equals("Cheeseburger")){
                    foodListForOnePerson.add(food);
                    foodListForTwoPerson.add(food);
                }
                if(food.getName().equals("Fish burger")){
                    foodListForTwoPerson.add(food);
                }
            }
            foodPackageDiscountRepo.save(new FoodPackageDiscount(null,"One person package","One person package",10,false,foodListForOnePerson,branchGroup));
            foodPackageDiscountRepo.save(new FoodPackageDiscount(null,"Two person package","Two person package",13,false,foodListForTwoPerson,branchGroup));
        });
    }
    public void initFood() {
        Integer discountPercent=22;
        categoryRepo.findAll().stream().forEach(category ->
                branchGroupRepo.findAll().stream().forEach(branchGroup -> {//也许食品会空行，但都是按照顺序来排列
                    if (category.getId().equals(1l) ) {//早餐
                        foodRepo.save(new Food(null, "Crisp potato cake", "Crisp potato cake", 5d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/早餐/脆薯饼.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Crispy fritters", "Crispy fritters", 5.5d, false, 0, imageBasePath + "/早餐/脆香油条.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Crispy chicken with wheat", "Crispy chicken with wheat", 8d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/早餐/大脆鸡扒麦满分.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Cereal chicken meat porridge", "Cereal chicken meat porridge", 6d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "早餐/谷物鸡肉麦鲜粥.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Ham egg pancake", "Ham egg pancake", 11d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "早餐/火腿扒蛋麦煎饼.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Ham cheeseburger", "Ham cheeseburger", 17d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "早餐/火腿扒麦满分.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Egg cheeseburger", "Egg cheeseburger", 16d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "早餐/吉士蛋麦满分.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Bacon egg pancake", "Bacon egg pancake", 11d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "早餐/培根蛋麦煎饼.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Gold bun", "Gold bun", 5d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "早餐/金馒头.png", 1, branchGroup, category,null));
                    }
                    if (category.getId().equals(2l) ) {//汉堡包
                        foodRepo.save(new Food(null, "Cheeseburger", "Cheeseburger", 8d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/吉士汉堡.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Big mac burger", "Big mac burger", 20d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/巨无霸汉堡.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Spicy chicken burger", "Spicy chicken burger", 17d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/麦辣鸡腿堡.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Fish burger", "Fish burger", 17d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/麦香鱼堡.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Bacon beef burger", "Bacon beef burger", 21d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/培根蔬萃双层牛肉堡.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Long chicken burger", "Long chicken burger", 20d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/香满嫩鸡加长堡.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Origin chicken burger", "Origin chicken burger", 18d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/原味板烧鸡腿堡.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Long cheese ham burger", "Long cheese ham burger", 19d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/汉堡/芝士火腿加长堡.png", 1, branchGroup, category,null));
                    }
                    if (category.getId().equals(3l) ) {//小食
                        foodRepo.save(new Food(null, "Spicy chicken wings", "Spicy chicken wings", 16d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/小食/麦辣鸡翅.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Happy chicken", "Happy chicken", 15d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/小食/麦乐鸡.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Big chicken", "Big chicken", 17d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/小食/那么大鸡排.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "French fries", "French fries", 11d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/小食/薯条.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Drumstick", "Drumstick", 13d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/小食/香骨鸡腿.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Corn cup", "Corn cup", 12d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/小食/玉米杯.png", 1, branchGroup, category,null));
                    }
                    if(category.getId().equals(4l)){//甜品
                        foodRepo.save(new Food(null, "McFlurry Oreo", "McFlurry Oreo", 12d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/奥利奥麦旋风.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "McFlurry Strawberry", "McFlurry Strawberry", 12d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/草莓奥利奥麦旋风.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Pineapple Pie", "Pineapple Pie", 7d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/菠萝派.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Sundae Strawberry", "Sundae Strawberry", 9d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/草莓新地.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Double Ice Cream Cone", "Double Ice Cream Cone", 6d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/双旋圆筒冰淇淋.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Taro Pie", "Taro Pie", 7d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/香芋派.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Ice Cream Cone", "Ice Cream Cone", 4d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/圆筒冰淇淋.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Sundae Chocolate", "Sundae Chocolate", 9d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/甜品/朱古力新地.png", 1, branchGroup, category,null));
                    }
                    if(category.getId().equals(5l)){//饮料
                        foodRepo.save(new Food(null, "Ice dew water", "Ice dew water", 6.5d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/冰露矿泉水.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Strawberry cool", "Strawberry cool", 6d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/红莓缤纷酷.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Coca-Cola", "Coca-Cola", 7d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/可口可乐.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Cola cool", "Cola cool", 6d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/可乐麦乐酷.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Sunshine Orange Juice", "Sunshine Orange Juice", 11d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/美汁源阳光橙.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Melon cool", "Melon cool", 8d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/蜜瓜缤纷酷.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Mocha cool", "Mocha cool", 9d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/摩卡缤纷酷.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Lemon black tea", "Lemon black tea", 9d, false,RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/柠檬红茶味饮料.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Taro cool", "Taro cool", 8.5d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/香芋缤纷酷.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Sprite cool", "Sprite cool", 7.5d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/雪碧麦乐酷.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Soybean milk", "Soybean milk", 5.5d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/饮料/优品豆浆.png", 1, branchGroup, category,null));
                    }
                    if(category.getId().equals(6l)){//咖啡
                        foodRepo.save(new Food(null, "Icy mocha", "Soybean Milk", 19d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/冰摩卡.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Icy latte", "Icy latte", 19d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/冰拿铁.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "Cappuccino", "Cappuccino", 17d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/卡布奇诺.png", 1, branchGroup, category,null));
                        foodRepo.save(new Food(null, "American style coffee", "American style coffee", 15d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/美式经典咖啡.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "mocha", "mocha", 19d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/摩卡.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "latte", "latte", 19d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/拿铁.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Espresso", "Espresso", 20d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/浓缩咖啡.png", 1, branchGroup, category,null));
//                        foodRepo.save(new Food(null, "Milk coffee", "Milk coffee", 21d, false, RandomUtils.nextInt(0,discountPercent), imageBasePath + "/咖啡/特浓香奶咖啡.png", 1, branchGroup, category,null));
                    }
                })
        );
    }
    public void initCategory(BranchGroup branchGroup) {//初始化食品分类
        categoryRepo.save(new Category(1l, "Breakfast", "Breakfast",branchGroup));
        categoryRepo.save(new Category(2l, "Hamburger", "Hamburger",branchGroup));
        categoryRepo.save(new Category(3l, "Snack", "Snack",branchGroup));
        categoryRepo.save(new Category(4l, "Dessert", "Dessert",branchGroup));
        categoryRepo.save(new Category(5l, "Drinks", "Drinks",branchGroup));
        categoryRepo.save(new Category(6l, "Coffee", "Coffee",branchGroup));
    }
    public void initEmployee() {
        List<BranchGroup> all = branchGroupRepo.findAll();
        all.stream().forEach(branchGroup -> {
            //保存一个收银员
            if (branchGroup.getName().equals(nameStoreWangFuJing)) {//生成一个王府井的收银员
                employeeRepo.save(new Employee(null, "111", "wangyaochong", "1162025261@qq.com", "360123199408090330", "17600401248", 23, new Date().getTime(),
                        EnumGender.MALE.toString(), EnumRole.CASHIER.toString(), 3500, 0, 0, 0, branchGroup));
            }
            for (EnumRole enumRole : EnumRole.values()) {
                for (int i = 0; i < 1; i++) {
                    Employee employee = new Employee();
                    employee.setPassword("123456");
                    employee.setName(branchGroup.getName() + " " + enumRole.toString() + RandomUtils.nextInt(1, 100));
                    employee.setPhoneNumber(RandomUtils.nextInt(1000000000, 2000000000) + "");
                    employee.setGender("MALE");
                    employee.setRole(enumRole.toString());
                    employee.setSalary(RandomUtils.nextInt(10000, 50000));
                    employee.setBranchGroup(branchGroup);
                    employee.setMonthlyWorkDayCount(0);
                    employee.setTotalWorkDayCount(0);
                    employeeRepo.save(employee);
                }
            }
        });
    }

    public void initGlobalConfig() {
        if (ObjectUtils.isEmpty(globalConfigRepo.findAll())) {//如果没有记录就插入一条
            globalConfigRepo.save(new GlobalConfig("1", 1, "aaaa-bbbb-cccc-dddd", 1, 9, 0, 19, 0, Boolean.FALSE));
        }
    }
    public void initCustomer() {
        if (ObjectUtils.isEmpty(customerRepo.findAll())) {
            for (Integer i = 0; i < 12; i++) {
                if (RandomUtils.nextInt(1, 4) == 1) {
                    customerRepo.save(new Customer(i.toString() + "@email.com", i.toString(), ((Integer) (i * 12345)).toString(), "beijing", new Date().getTime(), "FEMALE", Boolean.FALSE));
                } else {
                    customerRepo.save(new Customer(i.toString() + "@email.com", i.toString(), ((Integer) (i * 12345)).toString(), "beijing", new Date()
                            .getTime(), EnumGender.MALE.toString(), Boolean.FALSE));
                }
            }
        }
    }
}
