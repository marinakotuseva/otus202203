package ru.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import ru.aop.annotation.LogAction;
import ru.aop.model.MyInterface;

/**
 * @author Marina Kotuseva
 */
public class MyInvocationHandler implements InvocationHandler {

    private final MyInterface clazz;

    public MyInvocationHandler(MyInterface clazz) {

        this.clazz = clazz;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(LogAction.class) != null) {
            System.out.println(
                    "Invoking method '" + method.getName() + "' with following arguments: " + Arrays.toString(args));
        }
        return method.invoke(clazz, args);
    }
}
