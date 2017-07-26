package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ProductInstance {//每一条被消耗的原材料都会有记录
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    String name;//产品名称
    String description;//产品描述
    Double unitPrice;//产品价格

    String imagePath;

    Date createDate;//可以用于过期报警，生产日期
    Integer daysToBad;//保质期，以天数为单位
    Date badDate;//可以用于过期报警

    Date inDate;//入库时间
    Date outDate;//出库时间
    @ManyToOne(fetch = FetchType.EAGER)
    BranchGroup branchGroup;//该产品所处在的分支
}
