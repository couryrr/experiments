package com.dappercloud;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SockMerchant {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
    	Map<Integer, Integer> counts = new HashMap<>(); 
    	
    	
    	//Took too much time trying to make this a stream. Focus!!! Just get the code done
    	for(int k : ar) {
    		if(counts.containsKey(k)){
    			int value = counts.get(k);
    			counts.put(k, value+1);
    		} else {
    			//one is false
    			counts.put(k, 1);
    		}
    	}
    	
    	int matches = counts.entrySet().stream()
    	.map(c->c.getValue()/2)
    	.reduce(0, Integer::sum);
        return matches;
    }

    public static void main(String[] args) throws IOException {
        
        int n = 9;

        int[] ar = {10,20,20,10,10,30,50,10,20};
        //int[] ar = {1,2,1,2,1,3,2};
        int result = sockMerchant(n, ar);
        
        System.out.println(result);
    }
}
