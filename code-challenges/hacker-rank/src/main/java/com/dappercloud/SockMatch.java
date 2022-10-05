package com.dappercloud.hackerrank;

import java.util.HashMap;
import java.util.Map;


/** HackRank Challenge Easy completed 
 * John works at a clothing store. 
 * He has a large pile of socks that he must pair by color for sale.
 * Given an array of integers representing the color of each sock, determine how many pairs of socks with matching colors there are.
 * For example, there are n=7 socks with colors ar=[1,2,1,2,1,3,2]. 
 * There is one pair of color 1 and one of color 2. There are three odd socks left, one of each color.
 * The number of pairs is 2.
 */
public class SockMatch {
	
	/**
	 * 
	 * @param n
	 * @param ar
	 * @return Return the total number of matching pairs of socks that John can sell.
	 * 
	 * @implNote The first line contains an integer n, the number of socks represented in ar.
	 * The second line contains n space-separated integers describing the colors ar[i] of the socks in the pile.
	 * ar string is parsed by hacker rank
	 */
	public int match(int n, int[] ar) {
		//hmmm...I am not using n.
        Map<Integer, Integer> counts = new HashMap<>(); 
        
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
}
