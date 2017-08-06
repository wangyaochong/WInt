package program.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class ObjectUtil {
    public static  <T>  boolean checkAllFieldNull(T obj){
        if(obj==null){
            return true;
        }
        Field[] allFields = FieldUtils.getAllFields(obj.getClass());
        for (Field allField : allFields) {
            allField.setAccessible(true);
            try {
                if(allField.get(obj)==null){
                }else{
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    public static <T> boolean checkAllFieldNullOrEmptyAndSetEmptyNull(T obj){
        if(obj==null){
            return true;
        }
        Field[] allFields = FieldUtils.getAllFields(obj.getClass());
        for (Field allField : allFields) {
            allField.setAccessible(true);
            try {
                if(allField.get(obj)==null|| org.springframework.util.ObjectUtils.isEmpty(allField.get(obj))){
                    allField.set(obj,null);
                }else{
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
