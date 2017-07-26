package program.entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class FoodPackageDiscount {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    String packageName;
    Integer discount;//满100

    @ManyToMany
    List<Food> foodList;//打折列表
}
