package program.entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.*;
@Entity
@Data
public class User implements IEntity {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    String number;//编号
    String name;//姓名
    String identityNumber;//身份证号码
    String phoneNumber;//手机号码
    Integer age;//年龄

    String gender;//性别
    String userType;//用户类型，可能是公司员工，可能是普通用户
    Integer salary;//薪水，如果是普通用户就没有薪水

    @ManyToOne
    Group group;//所处于的分支机构，一个用户只可能同时处于一个分支机构，也可能不处于任何分支
}
