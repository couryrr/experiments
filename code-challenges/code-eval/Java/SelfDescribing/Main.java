import java.io.*;
import java.util.*;
/**
 * Add Description here
 *
 *
 *
 **/
class Main{
    //Kicking it all off
    public static void main(String[] args) throws FileNotFoundException, IOException{
            FileReader fr = new FileReader(args[0]);
            BufferedReader bf = new BufferedReader(fr);

            String line = bf.readLine();

            Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
            while(line != null){
                char[] values = line.toCharArray();
                
                int counter = 0;//Keeps track of the index

                for(char value : values){
                    numMap.put(counter, value - 48);//The index(number) and value(occurance)
                    counter++;
                }
                Iterator<Map.Entry<Integer, Integer>> iter = numMap.entrySet().iterator();
                boolean isSelfDescribing = true;
                while(iter.hasNext()){
                    Map.Entry<Integer, Integer> pair = iter.next();
                    int occurance = 0;
                    for(char value : values){
                        if(value - 48 == pair.getKey()){
                            occurance++;
                        }
                    }
                    if(!(occurance == pair.getValue())){
                        isSelfDescribing = false;
                        break;
                    }

               }
               numMap = new HashMap<Integer, Integer>();
               if(isSelfDescribing){
                   System.out.println("1");
               } else {
                   System.out.println("0");
               }
               line = bf.readLine();
            }
        }
    }
