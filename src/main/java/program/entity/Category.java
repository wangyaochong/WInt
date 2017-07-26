package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GenericGenerator(name="generator",strategy = "increment")
    @GeneratedValue(generator = "generator")
    Long id;//数据id
    String name;//分类名称
    String description;//分类描述
}
