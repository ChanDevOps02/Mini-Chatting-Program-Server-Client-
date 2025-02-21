package sec01.exam01;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class URLInfoExample {
    public static void main(String[] args){
        String urlName;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write URL name : ");
        urlName = scanner.nextLine();

        if(!urlName.startsWith("https://") && !urlName.startsWith("http://")){
            urlName = "https://" + urlName;
        }

        try{
            URL url = new URL(urlName);
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            FileOutputStream fileOutputStream = new FileOutputStream("URLInfo");

            String str;
            while((str = bufferedReader.readLine()) != null){
                System.out.println(str);
                fileOutputStream.write(str.getBytes());
            }
            fileOutputStream.close();
        }catch(MalformedURLException me){
            System.out.println("Can't find!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
