package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.interfaces.IEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Product implements IEntity{
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    String name;//产品名称
    String description;//产品描述
    Double price;//产品价格
    Long createDate;//可以用于过期报警

    Long timeToBad;//保质期（保存两个字段没关系，后续更方便，因此保质期和过期时间一旦录入就不会变了）
    Long badDate;//过期日期时间

    Long inDate;//入库时间
    Long outDate;//出库时间

    //因为一个产品可能就有一条交易记录，因此不能记录count字段

    @ManyToOne
    BranchGroup branchGroup;//该产品所处在的分支

    @ManyToOne
    Category category;//该产品所属类别
}
