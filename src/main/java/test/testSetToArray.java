package test;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class testSetToArray {
    @Test
    public void test(){
        Set<String> strings=new HashSet<>();
        strings.add("123");
        strings.add("123");
        strings.add("124");
        System.out.println(strings.toArray());
        for (Object o : strings.toArray()) {
            System.out.println(o);
        }

    }
}
