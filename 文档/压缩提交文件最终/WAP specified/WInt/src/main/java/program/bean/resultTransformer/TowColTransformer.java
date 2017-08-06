package program.bean.resultTransformer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.transform.ResultTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Data
@AllArgsConstructor
public class TowColTransformer implements ResultTransformer {
    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        Object o=null;
        try {
            Constructor constructor  = clazz.getConstructor(String.class, String.class);
            o= constructor.newInstance(objects[0].toString(), objects[1].toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }


    @Override
    public List transformList(List list) {
        return list;
    }
    Class clazz;

}
