package ru.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import ru.aop.annotation.LogAction;
import ru.aop.model.MyClass;
import ru.aop.model.MyInterface;

/**
 * @author Marina Kotuseva
 */
public class MyInvocationHandler implements InvocationHandler {

    private final MyClass clazz;

    private final Set<Method> annotatedMethods;

    public MyInvocationHandler(MyClass clazz) {

        this.clazz = clazz;
        this.annotatedMethods = Arrays.stream(clazz.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(LogAction.class))
                .collect(Collectors.toSet());
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (isAnnotatedMethodOfImplementation(method)) {
            System.out.println(
                    "Invoking method '" + method.getName() + "' with following arguments: " + Arrays.toString(args));
        }
        return method.invoke(clazz, args);
    }

    private boolean isAnnotatedMethodOfImplementation(Method method) {

        if (method.getDeclaringClass().isAssignableFrom(clazz.getClass())) {
            long methodsFound = annotatedMethods.stream()
                    .filter(m -> m.getName().equals(method.getName()))
                    .filter(m -> equalParamTypes(m.getParameterTypes(), method.getParameterTypes()))
                    .count();

            return methodsFound > 0;
        }
        return false;

    }

    boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
        /* Avoid unnecessary cloning */
        if (params1.length == params2.length) {
            for (int i = 0; i < params1.length; i++) {
                if (params1[i] != params2[i])
                    return false;
            }
            return true;
        }
        return false;

    }

}
