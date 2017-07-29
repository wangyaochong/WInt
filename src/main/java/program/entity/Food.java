package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    String name;//食品名称
    String description;//食品描述
    Double price;//产品价格
    Boolean isSuspended;//是否暂停出售
    Integer discountPercent;//打折百分数，最多100，默认是0，也就是不打折，打折后的价格等于 原价*(100-折扣数)/100
    String imagePath;//图片路径
    Integer count;//订购的数量
    @ManyToOne(fetch = FetchType.EAGER)
    BranchGroup branchGroup;//该食品所处在的分支
    @ManyToOne(fetch = FetchType.EAGER)
    Category category;//该产品所属类别
    Integer monthlySellCount;
}
