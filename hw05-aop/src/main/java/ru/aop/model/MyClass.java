package ru.aop.model;

import org.springframework.stereotype.Component;

/**
 * @author Marina Kotuseva
 */
@Component
public class MyClass implements MyInterface {

    @Override
    public void sayHi() {
        System.out.println("Hi there!");
    }

    @Override
    public void sayHi(String name) {
        System.out.println("Hi there " + name + "!");
    }

    @Override
    public void sayHi(String name, String lastName) {

        System.out.println("Hi there " + lastName + " " + name + "!");

    }

    @Override
    public void sayHi(String name, String lastName, String patrName) {
        System.out.println("Hi there " + lastName + " " + name + " " + patrName + "!");
    }
}
