package program.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentAsset {
    @Id
    @GenericGenerator(name="generator",strategy = "increment")
    @GeneratedValue(generator = "generator")
    Long id;//数据id
    String name;
    Double price;//价格
    Boolean isBroken;//是否损坏
    Integer count;
    String imagePath;
    @ManyToOne(fetch = FetchType.EAGER)
    BranchGroup branchGroup;
}
