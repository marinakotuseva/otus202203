package org.marina;

import com.google.common.base.Joiner;

/**
 * Main class for lesson 1 task.
 */
public class HelloOtus {

    public static void main(String[] args) {
        Joiner joiner = Joiner.on(" ").skipNulls();
        System.out.println(joiner.join("Hello", null, null, "professor"));
    }
}
