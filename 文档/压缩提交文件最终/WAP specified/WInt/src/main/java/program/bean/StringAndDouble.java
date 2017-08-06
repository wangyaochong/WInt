package program.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringAndDouble {
    String string;
    Double number;
    public StringAndDouble(String s, String n){
        this.string= s;
        this.number = Double.parseDouble(n);
    }
}