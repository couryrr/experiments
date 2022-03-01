import java.io.*;

class Main{
    public static void main(String[] args){
        //Bits are the same for p1 p2
        
        try{
            FileReader fr = new FileReader(args[0]);
            BufferedReader bf = new BufferedReader(fr);
            
            String line = bf.readLine();

            while (line != null){
                String[] lArray = line.split(",");
                
                //Get the int values from file 
                int bit = Integer.parseInt(lArray[0]);
                int p1 = Integer.parseInt(lArray[1]);
                int p2 = Integer.parseInt(lArray[2]);

                //Change the int to a string bit value to use charAt
                String sBit = Integer.toBinaryString(bit);
                
                //get the chars from specified location
                //Starts from end
                char b1 = sBit.charAt(sBit.length() - p1);
                char b2 = sBit.charAt(sBit.length() - p2);


                if (b1 == b2){
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
                line = bf.readLine();
            }
        }
        catch(IOException e){
            //Not doint anything here
        }
    }
}
