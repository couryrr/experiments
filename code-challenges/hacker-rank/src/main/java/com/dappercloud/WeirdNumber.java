package com.dappercloud;

/**
 * WeirdNumber
 */
public class WeirdNumber {

    public static String check(int number) {
        final String WEIRD = "Weird";
        final String NOT_WEIRD = "Not Weird";
        String response = "";
        if(number%2 == 0){
            if(number > 20) response = NOT_WEIRD;
            if(number >= 6 && number <= 20) response = WEIRD;
            if(number >= 2 && number <= 6) response = NOT_WEIRD;
        } else {
            response = WEIRD;
        }  

        System.out.println(response);
        return response;
    }
}