package main.java.com.lolz404.threadder;

import java.util.Arrays;

public class Adder {

    public static int add(String nums) {
        var arrOfStrNums = nums.split(",");
        int sum = Arrays.stream(arrOfStrNums).map(Integer::parseInt).reduce(0, Integer::sum);
        System.out.println(sum);
        return 0;
    }

}
