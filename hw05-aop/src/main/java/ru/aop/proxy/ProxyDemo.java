package ru.aop.proxy;

import ru.aop.model.MyInterface;

/**
 * @author Marina Kotuseva
 */
public class ProxyDemo {

    public static void main(String[] args) {

        MyInterface myClass = MyIoc.createMyClass();

        myClass.sayHi();
        myClass.sayHi("Иван");
        myClass.sayHi("Иванов", "Иван");
        myClass.sayHi("Иванов", "Иван", "Иванович");

        myClass.noLogMethod();
    }

}
