package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

public class FailedAfterClass {


    @Before
    void before1(){
        System.out.println("This is BEFORE 1");
    }

    @Before
    void before2(){
        System.out.println("This is BEFORE 2");
    }

    @Before
    void before3(){
        System.out.println("This is BEFORE 3");
    }

    @Test
    void test1(){
        System.out.println("This is TEST 1");
    }

    @Test
    void test2(){
        System.out.println("This is TEST 2");
    }

    @Test
    void test3(){
        System.out.println("This is TEST 3");
    }

    @After
    void after1(){
        System.out.println("This is AFTER 1");
    }

    @After
    void after2(){
        throw new RuntimeException("Some error");
    }

    @After
    void after3(){
        System.out.println("This is AFTER 3");
    }
}
