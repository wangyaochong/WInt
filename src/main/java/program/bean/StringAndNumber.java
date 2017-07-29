package program.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringAndNumber {
    String string;
    Integer number;
    public StringAndNumber(String s, String n){
        this.string= s;
        this.number = Integer.parseInt(n);
    }
}
