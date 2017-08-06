package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodInstance {//食品的实例，记录订单生成时食品的状态
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    String name;//食品名称
    String description;//食品描述
    Double price;//产品价格
    Integer discountPercent;//打折百分数，最多100
    String imagePath;//图片路径
    Integer count;//订购的数量
    Date createTime;//食品卖出的日期
    @ManyToOne(fetch = FetchType.EAGER)
    Category category;//该产品所属类别
    @ManyToOne(fetch = FetchType.EAGER)
    BranchGroup branchGroup;
    public FoodInstance(Food food,Date orderCreateDate){
        this.name=food.name;
        this.description=food.description;
        this.price=food.price;
        this.discountPercent=food.discountPercent;
        this.imagePath=food.imagePath;
        this.count=food.count;
        this.category=food.category;
        this.branchGroup=food.branchGroup;
        this.createTime=orderCreateDate;
    }
}
