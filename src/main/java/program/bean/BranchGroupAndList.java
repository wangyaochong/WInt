package program.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import program.entity.BranchGroup;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchGroupAndList<T>{
    BranchGroup branchGroup;
    List<T> list;
}
