package program.entity.util;

import java.lang.reflect.Field;

/**
 * Created by【王耀冲】on 【2016/12/15】 at 【2:46】.
 */
public class EntityUtil {
    //在有新值等情况下更新新值，没有就保持原来的值不变
    public static <T>void updateEntity(T oldObject,T newObject){
        Field[] declaredFields = oldObject.getClass().getDeclaredFields();
        for(Field  f:declaredFields){
            f.setAccessible(true);
            try {
                f.set(oldObject,f.get(newObject));//如果属性为null，则说明需要更新成null
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


    }

}
