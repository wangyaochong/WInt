package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import program.entity.interfaces.IEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalConfig {
    @Id
    String id;//数据id
    Integer salarySendingDate;//发放工资的日期
    String salaryPayCardNumber;//发放工资的帐号
    Integer salaryNextSendingDate;//修改发放工资的日期只在下一个月生效

    Integer everydayOnlineOrderBeginTimeHour;//网上订单开始时间0-23
    Integer everydayOnlineOrderBeginTimeMinute;//0-59

    Integer everydayOnlineOrderEndTimeHour;//网上订单结束时间
    Integer everydayOnlineOrderEndTimeMinute;//

    Boolean isMessageChanelOpen;//是否打开消息通道

}
