package program.entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class AlertConfig {
    @Id
    @GenericGenerator(name="generator",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    String id;//数据id
    String name;
    String description;

    Boolean message;//是否要发送短信
    Boolean email;//是否要发送邮件

    @ManyToMany(fetch = FetchType.EAGER)
    List<Employee> employeeList;//报警人列表
}
