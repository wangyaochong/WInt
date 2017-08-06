package program.repository.projection;

import org.springframework.data.rest.core.config.Projection;
import program.entity.BranchGroup;
import program.entity.Employee;

@Projection( types= {Employee.class,BranchGroup.class})
public interface Employee_BranchGroup {
    String getName();
    String getIdentityNumber();
    String getPhoneNumber();//手机号码
    Integer getAge();//年龄
    Long getBirthDate();//生日
    String getGender();//性别
    String getEmail();
    String getRole();//用户类型，可能是公司员工，可能是普通用户
    Integer getSalary();//薪水，如果是普通用户就没有薪水
    BranchGroup getBranchGroup();//获得所在分公司
}
