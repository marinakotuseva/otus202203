package ru.aop.model;

import ru.aop.annotation.LogAction;

/**
 * @author Marina Kotuseva
 */
public interface MyInterface {

    @LogAction
    void sayHi();

    @LogAction
    void sayHi(String name);

    @LogAction
    void sayHi(String name, String lastName);

    @LogAction
    void sayHi(String name, String lastName, String patrName);

    default void noLogMethod() {

        System.out.println("My execution must not be logged");
    }
}
