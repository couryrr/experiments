package com.lolz404.cloudfn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class CloudfnApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudfnApplication.class, args);
	}
	/*
	@Bean
	public Function<String, Map<Character, Integer>> countLetters() {
		return value ->{
			var map = new HashMap<Character, Integer>();
			for(char c : value.toCharArray()){
				map.put(Character.valueOf(c), map.getOrDefault(Character.valueOf(c), 0)+1);
			}
			return map;
		};
	}
	*/
}
