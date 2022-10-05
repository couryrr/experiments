package com.dappercloud;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayLeftRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
    	return IntStream
    			.concat(Arrays
					.stream(IntStream
			    			.rangeClosed(d+1, a.length)
			    			.map(i -> a[i-1])
			    			.toArray()), 
					Arrays
					.stream(IntStream
			    			.rangeClosed(1, d)
			    			.map(i -> a[i-1])
			    			.toArray()))
    			.toArray();
    }

    public static void main(String[] args){
        
        //int n = 20;

        int d = 10;

        int[] a = {41,73,89,7,10,1,59, 58, 84, 77, 77, 97, 58, 1, 86, 58, 26, 10, 86, 51};



        int[] result = rotLeft(a, d);

        System.out.println(Arrays.toString(result));
    }
}
