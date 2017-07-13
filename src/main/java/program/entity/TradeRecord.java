package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TradeRecord implements IEntity{//交易记录
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;

    @ManyToOne(fetch = FetchType.EAGER)
    User ordinaryUser;//客户

    @ManyToOne(fetch = FetchType.EAGER)
    User employee;//员工

    @OneToMany(fetch = FetchType.EAGER)
    List<Product> products;//交易的商品列表

    Long tradingDateTime;//交易日期以及时间
    Long tradingBranch;//交易分支

}
