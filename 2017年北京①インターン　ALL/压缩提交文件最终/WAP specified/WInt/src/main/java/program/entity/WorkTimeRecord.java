package program.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class WorkTimeRecord {//每日工作时间记录，一天一条记录
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id

    @ManyToOne(fetch = FetchType.EAGER)
    Employee employee;//员工


    Date firstLogInTime;
    Date lastLogOutTime;
    Integer workHour;

    Date date;//当日日期

}
