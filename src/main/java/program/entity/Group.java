package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@Entity
public class Group implements IEntity{
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    String name;//分支名称
    String description;//分支机构描述
    String address;//分支所在位置
    Long createTime;//分支机构成立时间
}
