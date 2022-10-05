package com.dappercloud;

/**
 * MealCostCalculator
 */
public class MealCostCalculator {
    public static int calculate(double meal_cost, int tip_percent, int tax_percent){
        double tip = meal_cost * (tip_percent / 100.0);
        double tax = meal_cost * (tax_percent / 100.0);
        int totalCost = (int) Math.round(meal_cost+tip+tax);
        System.out.println(totalCost);
        return totalCost;
    }
    
}