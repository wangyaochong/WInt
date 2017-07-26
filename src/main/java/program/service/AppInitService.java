package program.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ServletContextAware;
import program.entity.*;
import program.entity.enums.EnumGender;
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
    FoodInstanceService foodInstanceService;
    @Resource
    IFoodOrderRepo foodOrderRepo;
    public static final String nameStoreWangFuJing = "Chain Store around Wang Fu Jing";
    public static final String nameStoreDongSi = "Chain Store at Dong Si Street";
    public static final String nameStoreYongHeGong = "Chain Store around Yong He Gong";
    public static final String nameStoreTsingHua="Chain Store around Tsinghua University";
    public static final String nameStoreXiDanJoyCity="Chain Store around Xi Dan Joy City";
    public static final String nameStoreBeiJingRailStation="Chain Store around Bei Jing Rail Station";


    final String imageBasePath = "/WInt/dist/img/餐厅图片/";
    final Integer totalOrderDay = 30;


    @Override
    public void setServletContext(ServletContext servletContext) {
        if (ObjectUtils.isEmpty(globalConfigRepo.findAll())) {//如果没有初始化数据
            initGlobalConfig();
            initBranchGroup();
            initCustomer();
            initEmployee();
            initCategory();
            initFood();
            initProduct();
            initOrder();
        }
    }

    public List<FoodInstance> randomCreateFoodInstanceFromFoodList(List<Food> foodList, Integer foodCount, Integer fluctuateCount) {
        ArrayList<FoodInstance> foodInstanceList = new ArrayList<>();
        Integer count = foodCount + fluctuateCount / 2 - RandomUtils.nextInt(0, fluctuateCount + 1);//最终选取的食品数量可能多可能少，有波动
        while (count > 0) {
            foodInstanceList.add(foodInstanceRepo.save(new FoodInstance(foodList.get(RandomUtils.nextInt(0, foodList.size())))));
            count--;
        }
        return foodInstanceList;
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
        calendar.add(Calendar.DATE, -totalOrderDay);
        Date orderCreateDate = calendar.getTime();
        Integer totalOrderCount = 0;
        for (int i = 0; i < totalOrderDay; i++) {
            Integer tempOrderCount = initOrderCountEveryDay + orderCountFluctuation / 2 - RandomUtils.nextInt(0, orderCountFluctuation + 1);
            while (tempOrderCount > 0) {
                List<FoodInstance> foodInstanceList = randomCreateFoodInstanceFromFoodList(foodList, foodCountPerOrder, foodCountFluctuation);//每一个订单都是不同的食物列表
                List<Employee> employees = employeeRepo.queryEmployeesByBranchGroupAndRole(branchToSet, EnumRole.CASHIER.toString());
                Employee employee = employees.get(RandomUtils.nextInt(0, employees.size()));
                FoodOrder foodOrder = new FoodOrder(null, null, employee, foodInstanceList, orderCreateDate, null, foodInstanceService.getTotalFoodListPrice(foodInstanceList), false, EnumOrderType.STORE.toString(), branchToSet);
                foodOrderRepo.save(foodOrder);
                tempOrderCount--;
                totalOrderCount++;
//                System.out.println("orders created："+totalOrderCount);
            }
            initOrderCountEveryDay += orderCountGrowthEveryDay;
            calendar.add(Calendar.DATE,1);
            orderCreateDate=calendar.getTime();
        }
    }

    public void initOrder() {
        initOneStoreOrder(nameStoreWangFuJing, 6, 4, 150, 1, 30);
        initOneStoreOrder(nameStoreDongSi, 6, 4, 150, 0, 30);
        initOneStoreOrder(nameStoreYongHeGong, 6, 4, 150, -1, 30);
    }

    public void initProduct() {
        branchGroupRepo.findAll().stream().forEach(branchGroup -> {
            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            calendar.add(Calendar.DATE, 180);
            Date timeToBadDate = calendar.getTime();
            calendar.add(Calendar.DATE, -270);
            Date productionDate = calendar.getTime();
            productRepo.save(new Product(null, "Vinegar", "Vinegar", imageBasePath + "/消耗品/醋.jpg", 20d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Chicken essence", "Chicken essence", imageBasePath + "/消耗品/鸡精.jpg", 10d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Soy sauce", "Soy sauce", imageBasePath + "/消耗品/酱油.jpg", 13d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Disposable bag", "Disposable bag", imageBasePath + "/消耗品/垃圾袋.jpg", 11.5d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Detergent", "Detergent", imageBasePath + "/消耗品/洗洁精.jpg", 14.9d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Salt", "Salt", imageBasePath + "/消耗品/盐.jpg", 3.5d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "One-off chopsticks", "One-off chopsticks", imageBasePath + "/消耗品/一次性筷子.jpg", 12d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Disposable gloves", "Disposable gloves", imageBasePath + "/消耗品/一次性手套.jpg", 5.9d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Disposable gloves", "Disposable gloves", imageBasePath + "/消耗品/一次性纸杯.jpg", 9.9d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Tissue", "Tissue", imageBasePath + "/消耗品/纸巾.jpg", 36.9d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Cola", "Cola", imageBasePath + "/消耗品/可乐.jpg", 5.8d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));
            productRepo.save(new Product(null, "Beer", "Beer", imageBasePath + "/消耗品/啤酒.jpg", 4.5d, productionDate, 180, timeToBadDate, new Date(), 5, null, branchGroup));

        });
    }

    public void initFood() {
        categoryRepo.findAll().stream().forEach(category ->
                branchGroupRepo.findAll().stream().forEach(branchGroup -> {//也许食品会空行，但都是按照顺序来排列
                    if (category.getId() == 1) {//早餐
                        foodRepo.save(new Food(null, "Crisp potato cake", "Crisp potato cake", 5d, false, 0, imageBasePath + "/早餐/脆薯饼.png", 1, branchGroup, category));
                        foodRepo.save(new Food(null, "Crispy fritters", "Crispy fritters", 5.5d, false, 0, imageBasePath + "/早餐/脆香油条.png", 1, branchGroup, category));
                        foodRepo.save(new Food(null, " Crispy chicken with wheat", "Crispy chicken with grilled wheat", 8d, false, 0, imageBasePath + "/早餐/大脆鸡扒麦满分.png", 1, branchGroup, category));
                        foodRepo.save(new Food(null, "Cereal chicken meat porridge", "Cereal chicken meat porridge", 6d, false, 0, imageBasePath + "早餐/谷物鸡肉麦鲜粥.png", 1, branchGroup, category));
                    }
                    if (category.getId() == 2) {//汉堡包
                        foodRepo.save(new Food(null, "Cheeseburger", "Cheeseburger", 8d, false, 0, imageBasePath + "/汉堡/吉士汉堡.png", 1, branchGroup, category));
                        foodRepo.save(new Food(null, "Big mac burger", "Big mac burger", 20d, false, 0, imageBasePath + "/汉堡/巨无霸汉堡.png", 1, branchGroup, category));
                        foodRepo.save(new Food(null, "Spicy chicken burger", "Spicy chicken burger", 17d, false, 0, imageBasePath + "/汉堡/麦辣鸡腿堡.png", 1, branchGroup, category));
                        foodRepo.save(new Food(null, "Fish burger", "Fish burger", 17d, false, 0, imageBasePath + "/汉堡/麦香鱼堡.png", 1, branchGroup, category));
                    }
                    if (category.getId() == 3) {//小食
                        foodRepo.save(new Food(null, "Spicy chicken wings", "Spicy chicken wings", 11d, false, 0, imageBasePath + "/小食/麦辣鸡翅.png", 1, branchGroup, category));
                        foodRepo.save(new Food(null, "French fries", "French fries", 11d, false, 0, imageBasePath + "/小食/薯条.png", 1, branchGroup, category));
                    }
                })
        );
    }

    public void initCategory() {//初始化食品分类
        categoryRepo.save(new Category(1l, "Breakfast", "Breakfast"));
        categoryRepo.save(new Category(2l, "Hamburger", "Hamburger"));
        categoryRepo.save(new Category(3l, "Snack", "Snack"));
        categoryRepo.save(new Category(4l, "Dessert", "Dessert"));
        categoryRepo.save(new Category(5l, "Drinks", "Drinks"));
        categoryRepo.save(new Category(6l, "Coffee", "Coffee"));
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
                for (int i = 0; i < 3; i++) {
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

    public void initBranchGroup() {
        if (ObjectUtils.isEmpty(branchGroupRepo.findAll())) {
            branchGroupRepo.save(new BranchGroup(nameStoreDongSi, "store No.1", "海淀区花园路2号翠微大厦牡丹园店1楼(近牡丹园地铁站西北口)", new Date().getTime(), 100));
            branchGroupRepo.save(new BranchGroup(nameStoreTsingHua, "store No.2", "北京市海淀区双清路30号", new Date().getTime(), 100));
            branchGroupRepo.save(new BranchGroup(nameStoreWangFuJing, "store No.3", "王府井大街99号首层、地下一层", new Date().getTime(), 100));
            branchGroupRepo.save(new BranchGroup(nameStoreYongHeGong, "store No.4", "雍和宫西南角翻建商业A区1层", new Date().getTime(), 100));
            branchGroupRepo.save(new BranchGroup(nameStoreBeiJingRailStation, "store No.5", "北京市东城区北京火车站西街北京站站前广场西侧", new Date().getTime(), 100));
            branchGroupRepo.save(new BranchGroup(nameStoreXiDanJoyCity, "store No.6", "北京市西城区西单横二条59号西单明珠商品市场地下1层", new Date().getTime(), 100));
        }
    }
    public void initCustomer() {
        if (ObjectUtils.isEmpty(customerRepo.findAll())) {
            for (Integer i = 0; i < 100; i++) {
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
