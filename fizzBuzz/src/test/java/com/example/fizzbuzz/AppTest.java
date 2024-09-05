package com.example.fizzbuzz;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void AppHappyDay(){
        App unitUnderTest = new App();
        String actual = unitUnderTest.getFizzBuzzFor(15);
        assertEquals("\"1\" \"2\" \"Fizz\" \"4\" \"Buzz\" \"Fizz\" " +
                "\"7\" \"8\" \"Fizz\" \"Buzz\" \"11\" \"Fizz\" " +
                "\"13\" \"14\" \"Fizz Buzz\" ", actual);
    }


}