package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import program.entity.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    //这里的product就是可以消耗的原材料了
    //包括各种视频佐料，包装盒、垃圾袋、纸巾、手套等
    //这张表记录的是仓库中正有的库存
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    String name;//产品名称
//    String description;//产品描述
    String imagePath;
    Double unitPrice;//产品单价

    Date productionDate;//可以用于过期报警，生产日期
//    Integer daysToBad;//保质期，以天数为单位
    Date badDate;//可以用于过期报警
//    Date inDate;//入库时间
    Integer count;//可用数量
    Integer countToAlarm;//数量小于多少时报警，当数量小于等于多少时需要报警
    Integer daysLeftToAlarm;
    Integer daysToRecharge;//要重新买满多少天的
    Double predictedConsumingDays;
    Double predictedConsumingRatePerDay;
    @ManyToOne(fetch = FetchType.EAGER)
    BranchGroup branchGroup;//该产品所处在的分支
}
