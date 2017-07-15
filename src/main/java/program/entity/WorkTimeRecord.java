package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.interfaces.IEntity;

import javax.persistence.*;

@Entity
@Data
public class WorkTimeRecord implements IEntity {//每日工作时间记录
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    @ManyToOne(fetch = FetchType.EAGER)
    Employee employee;//员工

    Integer totalWorkDayCount;//工作天数
    Integer monthlyWorkDayCount;//当前周期工作天数

    Long firstLogInTime;//记录第一次登录时间
    Long lastLogOutTime;//记录最后一次登出时间

}
