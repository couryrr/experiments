package com.lolz404.cloudfn;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StringStuff implements Function<String, Map<Character, Integer>> {
    @Override
    public Map<Character, Integer> apply(String value) {
            var map = new HashMap<Character, Integer>();
            for(char c : value.toCharArray()){
                map.put(Character.valueOf(c), map.getOrDefault(Character.valueOf(c), 0)+1);
            }
            return map;
    }
}
