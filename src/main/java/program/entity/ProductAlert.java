package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ProductAlert {//把报警配置和合并到一起，到时演示时说明这个地方是可以改进的
    @Id
    @GenericGenerator(name="generator",strategy = "increment")
    @GeneratedValue(generator = "generator")
    Long id;//数据id
    Integer inventoryForAlert;//库存报警数量
    Integer remainDayForAlert;//保质期报警天数
    Integer predictSustainDayAlert;//预测维持天数报警
    @ManyToOne(fetch = FetchType.EAGER)
    AlertConfig alertConfig;//报警配置
    @ManyToMany
    List<Product> products;//需要报警的产品
}
