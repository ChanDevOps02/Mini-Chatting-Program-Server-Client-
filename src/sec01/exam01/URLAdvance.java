package sec01.exam01;

import java.io.*;
import java.net.*;

public class URLAdvance {
    public static void main(String[] args){
        FileOutputStream fileOutputStream = null;
        BufferedReader bufferedReader = null;

        try{
            URL url = new URL("https://www.google.com");

            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            fileOutputStream = new FileOutputStream("URLAdvance.txt");
            String input;

            while((input = bufferedReader.readLine())!=null){
                System.out.println(input);
                fileOutputStream.write(input.getBytes());
            }
            fileOutputStream.close();
        }catch(MalformedURLException me){
            me.printStackTrace();
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
}
