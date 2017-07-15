package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.base.EntityBase;
import program.entity.interfaces.IEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Category extends EntityBase {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    String name;//分类名称
    String description;//分类描述
}
