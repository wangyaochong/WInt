package program.entity.test;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.base.EntityBase;
import program.entity.interfaces.IEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Data
public class TestEntity extends EntityBase{
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;
    String name;
    Integer age;
    Long inDate;
    Long outDate;
    String title;
    String description;
    String type;
}
