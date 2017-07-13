package program.entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IConsumingItemEntity;
import program.entity.entityInterface.IEntity;
import program.entity.entityInterface.IObjectEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@Entity
public class TestEntity implements IEntity,IConsumingItemEntity,IObjectEntity,Comparable<TestEntity>{
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;
    @Column(columnDefinition = "comment '用户姓名' ")
    String name;
    Integer age;
    Long inDate;
    Long outDate;
    String title;
    String description;
    String type;
    public TestEntity() {
    }
    public TestEntity(String name, Integer age, Long inDate, Long outDate, String title, String description, String type) {
        this.name = name;
        this.age = age;
        this.inDate = inDate;
        this.outDate = outDate;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    @Override
    public int compareTo(TestEntity o) {
        return this.getAge()-o.getAge();
    }
}
