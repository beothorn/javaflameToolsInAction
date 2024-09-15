package org.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;

public class PreMain {
    public static class PrintArgs {
        @Advice.OnMethodEnter
        public static void enter(@Advice.AllArguments Object[] allArguments) {
            Object[] allArgument = (Object[]) allArguments[0];
            System.out.println("Main called with args " + Arrays.toString(allArgument));
        }
    }

    public static void premain(
            String argumentParameter,
            Instrumentation instrumentation
    ) {
        System.out.println("Simple agent example");
        new AgentBuilder.Default()
                .type(ElementMatchers.any())
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.visit(
                                Advice.to(PrintArgs.class)
                                        .on(ElementMatchers.isMethod()
                                                .and(ElementMatchers.named("main")))
                        )
                )
                .installOn(instrumentation);
    }
}