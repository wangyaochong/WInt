package test;

import lombok.Data;
import org.junit.Test;
import program.entity.enums.EnumRole;

enum MyEnum{
    ONE(1,"1","一"),
    TWO(2,"2","二");

    private final Integer num;
    private final String digString;
    private final String  charString;

    public Integer getNum() {
        return num;
    }

    public String getDigString() {
        return digString;
    }

    public String getCharString() {
        return charString;
    }

    MyEnum(Integer num, String digString, String charString ){
        this.num=num;
        this.digString=digString;
        this.charString=charString;
    }

}
public class TestEnum {
    @Test
    public void testEnumRole(){
        System.out.println(EnumRole.values().toString());
        System.out.println(EnumRole.BRANCH_ADMIN);
    }
    @Test
    public void test(){
        System.out.println(MyEnum.ONE.getCharString());
    }
}
