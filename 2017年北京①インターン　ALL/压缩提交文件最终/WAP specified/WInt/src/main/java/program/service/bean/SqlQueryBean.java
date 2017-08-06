package program.service.bean;

public class SqlQueryBean {
    public static final String getFoodSellingCashGlobal="SELECT name,sum(price*(100-discountPercent)/100*count) as amount  FROM `foodinstance` group by name order by amount desc limit 10";
    public static final String getFoodSellingCashGlobalByDayCount="SELECT name,sum(price*(100-discountPercent)/100*count) as amount   FROM `foodinstance` where  datediff(DATE_FORMAT(now(),'%Y-%c-%d'),DATE_FORMAT(createTime,'%Y-%c-%d'))<:dayCount group by name  order by amount desc limit 10 ";
    public static final String getFoodSellingCashByBranchGroup="SELECT name,sum(price*(100-discountPercent)/100*count)  as amount  FROM `foodinstance` where branchGroup_id=:branchGroupId  group by name   order by amount desc limit 10";
    public static final String getFoodSellingCashByBranchGroupAndDayCount="SELECT name,sum(price*(100-discountPercent)/100*count) as amount  FROM `foodinstance` where branchGroup_id=:branchGroupId and datediff(DATE_FORMAT(now(),'%Y-%c-%d'),DATE_FORMAT(createTime,'%Y-%c-%d'))<:dayCount  group by name order by amount desc limit 10";
    public static final String getDateAndOrderCountListByBranchGroup="SELECT DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d')  ,sum(totalPrice)   FROM `foodorder` where orderType not like 'SYSTEM_FOR_PREPARATION' and branchGroup_id=:branchGroupId group by DATE_FORMAT(orderBeginDateTime,'%Y/%c/%d'),branchGroup_id";
}
