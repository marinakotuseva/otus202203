package ru.aop.proxy;

import ru.aop.model.MyInterface;

/**
 * @author Marina Kotuseva
 */
public class ProxyDemo {

    public static void main(String[] args) {

        MyInterface myInterface = MyIoc.createMyInterface();

        myInterface.sayHi();
        myInterface.sayHi("Иван");
        myInterface.sayHi("Иванов", "Иван");
        myInterface.sayHi("Иванов", "Иван", "Иванович");

        myInterface.noLogMethod();
    }

}
