package program.entity;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Customer {
    public Customer() {
    }
    public Customer(String email, String name,String phoneNumber, String address, Long birthDate, String gender, Boolean isVip) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.isVip = isVip;
    }

    @Id
    String email;//邮箱，同时作为登陆帐号
    String name;//姓名
    String phoneNumber;//手机号码
    String address;
    Long birthDate;//生日
    String gender;//性别
    Boolean isVip;//是否是会员
}