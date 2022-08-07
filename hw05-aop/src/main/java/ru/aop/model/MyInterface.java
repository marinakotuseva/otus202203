package ru.aop.model;

import ru.aop.annotation.LogAction;

/**
 * @author Marina Kotuseva
 */
public interface MyInterface {

    void sayHi();

    void sayHi(String name);

    void sayHi(String name, String lastName);

    void sayHi(String name, String lastName, String patrName);

    default void noLogMethod() {

        System.out.println("My execution must not be logged");
    }
}
