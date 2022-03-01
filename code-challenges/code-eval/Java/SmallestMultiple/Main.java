import java.io.*;

class Main{
    public static void main(String[] args){
        //Input is a file with 2 number x and n
        //Print the samllest multiple of n 
        //greater than or equal to x
        //Do not us division or modulo
        try{
            FileReader fr = new FileReader(args[0]);
            BufferedReader bf = new BufferedReader(fr);
            
            String line = bf.readLine();

            while (line != null){
                String[] lArray = line.split(",");
                int x = Integer.parseInt(lArray[0]);
                int n = Integer.parseInt(lArray[1]);
                int m = 1;
                int mn = 0;
                while (mn < x){
                    mn = m*n;
                    m++;
                } 

                System.out.println(mn);

                line = bf.readLine();
            }
        }
        catch(IOException e){
            //Not doint anything here
        }
    }
}
