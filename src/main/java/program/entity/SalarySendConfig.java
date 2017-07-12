package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class SalarySendConfig implements IEntity{

    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")

    String id;//数据id
    Integer sendingDate;//发放工资的日期
    String payCardNumber;//发放工资的帐号
    Integer nextSendingDate;//修改发放工资的日期只在下一个月生效

}
