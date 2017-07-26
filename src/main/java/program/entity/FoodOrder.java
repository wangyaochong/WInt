package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import program.entity.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrder {//交易记录
    @Id
    @GenericGenerator(name="generator",strategy = "increment")
    @GeneratedValue(generator = "generator")
    Long id;//数据id
    @ManyToOne(fetch = FetchType.EAGER)
    Customer customer;//顾客
    @ManyToOne(fetch = FetchType.EAGER)
    Employee cashier;//收银员

//    @ManyToOne(fetch = FetchType.EAGER)
//    Employee chef;//厨师

//    @ManyToOne(fetch = FetchType.EAGER)
//    Employee waiter;//服务员

    @OneToMany(fetch = FetchType.EAGER)
    List<FoodInstance> foodList ;//交易的食品列表
    //交易开始时间，预定时间，每一条交易都有开始时间
    Date orderBeginDateTime;
    //交易结束时间，交付时间，没有交付时间可能取消
    // 也可能正在进行，取决于取消字段
//    Date orderEndDateTime;
//    String feedBack;


    Integer tableNumber;//座位号，可能没有
    Double totalPrice;//总价格
    Boolean isCancelled;//交易是否取消
    String orderType;
    @ManyToOne
    BranchGroup branchGroup;
}
