import java.io.*;
import java.util.ArrayList;
/**
 * Add Description here
 *
 *
 *
 **/
class Main{
    //All the numbers seen for a line
    private static ArrayList<Integer> number_seen = null; 

    //Kicking it all off
    public static void main(String[] args){
        try{
            FileReader fr = new FileReader(args[0]);
            BufferedReader bf = new BufferedReader(fr);

            String line = bf.readLine();

            while(line != null){
                number_seen = new ArrayList<Integer>();
                if(Integer.parseInt(line) > -1){
                    char[] charNums = line.toCharArray();                
                    checkHappyness(charNums);            
                    line = bf.readLine();
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    private static void checkHappyness(char[] charNums){
        int sum = 0;
        for(char charNum : charNums){
            int num = Character.getNumericValue(charNum);
            sum += num*num;
        }
        if(sum > 1 && !was_seen(sum)){
            //First time seen so add to numbers list
            number_seen.add(sum);

            //Makes the number a string to cast to char[]
            checkHappyness(("" + sum).toCharArray());
        } else if (was_seen(sum)){
            //print 0
            System.out.println(0);
        } else {
            //must be happy
            System.out.println(1);
        }
    }
    private static boolean was_seen(int key){
         return number_seen.contains(key);
    }
}
