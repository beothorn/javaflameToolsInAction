/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example.intercept;

import java.lang.reflect.Executable;
import java.util.Arrays;

public class ConsoleInterceptor {

    public static void interceptMethod(
            Object self,
            Executable method,
            Object[] allArguments,
            Object returnValueFromMethod
    ){
        System.out.println(">>>>");
        System.out.println("self: "+self);
        System.out.println("method: "+method.getName());
        System.out.println("allArguments: "+ Arrays.toString(allArguments));
        System.out.println("returnValueFromMethod: "+returnValueFromMethod);
        System.out.println("<<<<");
    }

}
