package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GenericGenerator(name="generator",strategy = "increment")
    @GeneratedValue(generator = "generator")
    Long id;//数据id
    String password;//
    String name;//姓名
    String email;//邮件
    String identityNumber;//身份证号码
    String phoneNumber;//手机号码
    Integer age;//年龄
    Long birthDate;//生日
    String gender;//性别
    String role;//用户类型，可能是公司员工，可能是普通用户
    Integer salary;//薪水，如果是普通用户就没有薪水

    Integer totalWorkHourCount;//总工作小时
    Integer totalWorkDayCount;//工作天数
    Integer monthlyWorkDayCount;//当前周期工作天数

    @ManyToOne(fetch = FetchType.EAGER)
    BranchGroup branchGroup;//员工所在分公司
}
