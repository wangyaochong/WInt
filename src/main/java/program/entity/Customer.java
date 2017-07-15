package program.entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import program.entity.base.EntityBase;
import program.entity.interfaces.IEntity;

import javax.persistence.*;
@Entity
@Data
public class Customer extends EntityBase {
    @Id
    String email;//邮箱，同时作为登陆帐号
    String name;//姓名
    String identityNumber;//身份证号码
    String phoneNumber;//手机号码
    Integer age;//年龄
    Long birthDate;//生日
    String gender;//性别

}
