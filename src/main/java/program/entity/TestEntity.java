package program.entity;

import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IConsumingItemEntity;
import program.entity.entityInterface.IEntity;
import program.entity.entityInterface.IObjectEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by【王耀冲】on 【2017/6/13】 at 【23:04】.
 */
@Entity
public class TestEntity implements IEntity,IConsumingItemEntity,IObjectEntity{
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
    public Long getInDate() {
        return inDate;
    }

    @Override
    public void setInDate(Long inDate) {
        this.inDate = inDate;
    }

    @Override
    public Long getOutDate() {
        return outDate;
    }

    @Override
    public void setOutDate(Long outDate) {
        this.outDate = outDate;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
