import java.io.*;

class Main{
    
    public static void main(String[] args){
        try{
            FileReader InputFile = new FileReader(args[0]);
            
            BufferedReader bf = new BufferedReader(InputFile);
            String line = bf.readLine();
            while(line != null){
                if(line.length() > 0){
                    String rLine = "";
                    String[] lArray = line.split(" ");
                    
                    for (int i = lArray.length - 1; i > 0; i--)
                        rLine += lArray[i] + " ";
                    rLine += lArray[0];
                    System.out.println(rLine);
                }
                line = bf.readLine();
            }
        } 
        catch(IOException e) {
        //Not doing anything    
        }

    }
}
