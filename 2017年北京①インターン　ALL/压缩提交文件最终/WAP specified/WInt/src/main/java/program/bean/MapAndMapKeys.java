package program.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapAndMapKeys<T> {
    List<String> mapKeys;
    Map<String,List<T>> map;
}
