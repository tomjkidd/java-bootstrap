package com.tomjkidd;

import java.util.Arrays;
import clojure.java.api.Clojure;
import clojure.lang.IFn;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Boom shaka lakka!" );
        Arrays.asList(args).forEach(System.out::println);

        IFn plus = Clojure.var("clojure.core", "+");
        Object result = plus.invoke(1, 2);

        System.out.println(result);
    }
}
