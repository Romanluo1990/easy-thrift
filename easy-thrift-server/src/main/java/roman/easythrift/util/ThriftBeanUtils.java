package roman.easythrift.util;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by roman.luo on 2017/2/27.
 */
public class ThriftBeanUtils {

    public static <T extends TBase> T copyProperties(final Class<T> clazz, final Object orig) throws TException {
        try {
            T t = clazz.newInstance();
            Map<String, String> propertiesMap =  BeanUtils.describe(orig);
            for (int i = 1; ; i++) {
                TFieldIdEnum tFieldIdEnum = t.fieldForId(i);
                if(tFieldIdEnum==null)
                    break;
                t.setFieldValue(tFieldIdEnum,propertiesMap.get(tFieldIdEnum.getFieldName()));
            }
            return t;
        } catch (IllegalAccessException| InvocationTargetException |NoSuchMethodException |InstantiationException e) {
            throw new TException("属性异常",e);
        }
    }
}
