package ru.aop.model;

import org.springframework.stereotype.Component;

import ru.aop.annotation.LogAction;

/**
 * @author Marina Kotuseva
 */
@Component
public class MyClass implements MyInterface {

    @LogAction
    @Override
    public void sayHi() {
        System.out.println("Hi there!");
    }

    @LogAction
    @Override
    public void sayHi(String name) {
        System.out.println("Hi there " + name + "!");
    }

    @LogAction
    @Override
    public void sayHi(String name, String lastName) {

        System.out.println("Hi there " + lastName + " " + name + "!");

    }

    @LogAction
    @Override
    public void sayHi(String name, String lastName, String patrName) {
        System.out.println("Hi there " + lastName + " " + name + " " + patrName + "!");
    }
}
