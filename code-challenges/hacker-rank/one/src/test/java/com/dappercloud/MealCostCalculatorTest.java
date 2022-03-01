package com.dappercloud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * MealCostCalculatorTest
 */
public class MealCostCalculatorTest {

    @Test
    public void testCalculateExample1(){
        int cost = MealCostCalculator.calculate(12, 20, 8);
        assertEquals(15, cost);
    }

    @Test
    public void testCalculateExample2(){
        int cost = MealCostCalculator.calculate(15.50, 15, 10);
        assertEquals(19, cost);
    }
}