import java.io.*;
class Main{
    public static void main(String[] args){
        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader bf = new BufferedReader(fr);

            String line = bf.readLine();

            while(line != null){
                int position = 0;
                String[] arr = line.split(",");
                line = arr[0];
                char key = arr[1].charAt(0);
                int keyPosition = -1;
                for (char value : line.toCharArray()){
                    if(key == value){
                        keyPosition = position;
                    }
                    position++;
                }
                System.out.println(keyPosition);
                line = bf.readLine();
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
