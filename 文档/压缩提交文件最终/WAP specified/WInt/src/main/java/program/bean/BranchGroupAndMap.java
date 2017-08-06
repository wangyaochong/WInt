package program.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import program.entity.BranchGroup;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchGroupAndMap<T> {
    BranchGroup branchGroup;
    Map<String,List<T>> map;
    List<String> mapKeys;
}
