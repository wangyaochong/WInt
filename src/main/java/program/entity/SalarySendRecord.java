package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.entityInterface.IEntity;

import javax.persistence.*;

@Data
@Entity
public class SalarySendRecord implements IEntity{//薪水发放记录
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    Long sentDate;//发送日期

    @ManyToOne(fetch = FetchType.EAGER)
    User user;//发送对象，对象有可能会变动职位

    Long amount;//发送薪资大小
}
