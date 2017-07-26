package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class SalarySendRecord implements IEntity{//薪水发放记录
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    Date sentDate;//发送日期

    @ManyToOne(fetch = FetchType.EAGER)
    Employee employee;

    Long amount;//发送薪资大小
}
