package ru.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import ru.aop.model.MyClass;
import ru.aop.model.MyInterface;

/**
 * @author Marina Kotuseva
 */
public class MyIoc {

    static MyInterface createMyClass() {
        InvocationHandler handler = new MyInvocationHandler(new MyClass());
        return (MyInterface) Proxy.newProxyInstance(MyIoc.class.getClassLoader(),
                new Class<?>[]{MyInterface.class}, handler);
    }

}
