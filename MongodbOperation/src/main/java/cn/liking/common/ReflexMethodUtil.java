package cn.liking.common;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author liking
 * @date 2023/11/26 17:37
 */
@Slf4j
public class ReflexMethodUtil {

    /**
     * 通过反射执行方法
     *
     * @param beanName   执行方法的对象
     * @param methodName 方法名
     * @param obj        方法需要的参数
     * @param methodName 方法需要的参数类型
     * @author song
     * @date 2023/10/30 15:06
     */
    public static Object executeMethod(String beanName, String methodName, Object obj, Class<?>... parameterTypes) {
        try {
            Object bean = SpringUtil.getBean(beanName);
            Method method = getDeclaredMethod(bean, methodName, parameterTypes);
            //bean是调用这个方法的对象
            Object result = method.invoke(bean, obj);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param obj            子类对象
     * @param methodName     父类方法名
     * @param parameterTypes 父类方法参数类型
     * @return 父类中的方法对象
     */
    public static Method getDeclaredMethod(Object obj, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }
}
