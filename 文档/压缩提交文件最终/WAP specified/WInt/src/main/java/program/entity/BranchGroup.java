package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import program.entity.base.EntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Data
public class BranchGroup extends EntityBase{
    @Id
    @GenericGenerator(name="generator",strategy = "increment")
    @GeneratedValue(generator = "generator")
    Long id;//数据id
    String name;//分支名称
    String description;//分支机构描述
    String address;//分支所在位置
    Long createDate;//分支机构成立时间
    Integer foodDiscount;//设置食品打折，最多100

    public BranchGroup() {
    }

    public BranchGroup(String name, String description, String address, Long createDate, Integer foodDiscount) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.createDate = createDate;
        this.foodDiscount = foodDiscount;
    }
}
