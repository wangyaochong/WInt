package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductInstance {//每一条被消耗的原材料都会有记录
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    String name;//产品名称
    String description;//产品描述
    Double unitPrice;//产品价格

    String imagePath;

    Date productionDate;//可以用于过期报警，生产日期
    Integer daysToBad;//保质期，以天数为单位
    Date badDate;//可以用于过期报警

    Date inDate;//入库时间
    Date outDate;//出库时间，被消耗的时间
    @ManyToOne(fetch = FetchType.EAGER)
    BranchGroup branchGroup;//该产品所处在的分支
    public ProductInstance(Product product,Date consumingDate){
        this.name=product.name;
//        this.description=product.description;
        this.unitPrice=product.unitPrice;
        this.imagePath=product.imagePath;
        this.productionDate=product.productionDate;
//        this.daysToBad=product.daysToBad;
        this.badDate=product.badDate;
//        this.inDate=product.inDate;
        this.outDate=consumingDate;
        this.branchGroup=product.branchGroup;
    }
}
