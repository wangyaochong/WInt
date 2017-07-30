package program.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FoodPackageDiscount {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    String packageName;
    String packageDescription;
    Integer discount;//满100
    Boolean isSuspended;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Food> foodList;//打折列表
    @ManyToOne(fetch =  FetchType.EAGER)
    BranchGroup branchGroup;//在哪个分店的配置
}
