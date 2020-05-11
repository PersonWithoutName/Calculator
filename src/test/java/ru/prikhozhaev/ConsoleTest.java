package ru.prikhozhaev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

 class ConsoleTest {
    private final Console console = new Console();

    @ParameterizedTest
    @MethodSource("ru.prikhozhaev.Providers#testEmptyExpression")
    @DisplayName("Test of the Console.getPreparedString(String stringToPrepare) with empty expression")
    void testEmptyExpression(String string){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> console.getPreparedString(string));
        assertThat("Expression is empty.", is(equalTo(exception.getMessage())));
    }

    @ParameterizedTest
    @MethodSource("ru.prikhozhaev.Providers#testNotEmptyExpression")
    @DisplayName("Test of the Console.getPreparedString(String stringToPrepare) with not empty expression")
    void testNotEmptyExpression(String string){
       assertDoesNotThrow(()->console.getPreparedString(string));
    }

    @ParameterizedTest
    @MethodSource("ru.prikhozhaev.Providers#testExpressionWithSpaces")
    @DisplayName("Test of the Console.getPreparedString(String stringToPrepare) with expression with spaces")
     void testExpressionWithSpaces(String expected, String expression){
        assertThat(expected, is(equalTo(console.getPreparedString(expression))));
    }

     @ParameterizedTest
     @MethodSource("ru.prikhozhaev.Providers#testExpressionWithMinusOrPlus")
     @DisplayName("Test of the method Console.getPreparedString(String stringToPrepare) with minus or plus")
     void testExpressionWithMinusOrPlus(String expected, String expression){
         assertThat(expected, is(equalTo(console.getPreparedString(expression))));
     }



 }
