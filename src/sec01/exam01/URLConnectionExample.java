package sec01.exam01;

import com.sun.source.doctree.EscapeTree;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class URLConnectionExample {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write URL Name : ");
        String urlName = scanner.nextLine();

        if(!(urlName.startsWith("https://"))){
            urlName = "https://" + urlName;
        }
        URL url = null;
        try{
            url = new URL(urlName);
        }catch(MalformedURLException me){
            System.out.print("Can't Find!");
            System.exit(0);
        }

        FileOutputStream fos = null;

        try{
            URLConnection urlcon = url.openConnection();
            String contentType = urlcon.getContentType(); //웹 문서의 헤드에서 contenttype을 얻어옴

            long dl = urlcon.getDate(); //읽어온 시간을 ms으로 변환함.
            Date date = new Date(dl);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm::ss a");
            String result = sdf.format(date); //지정된 포멧으로 읽어온 시간을 변경함.

            System.out.println("Content type : " + contentType);
            System.out.print("Read time : " + result);

            InputStream inputStream = urlcon.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            fos = new FileOutputStream("urlConnectionExample.txt");
            String temp;
            System.out.println("Unboxing INFO...");
            while((temp = bufferedReader.readLine()) != null){
                System.out.println(temp);
                fos.write(temp.getBytes());
            }
            System.out.println("Unboxing finish!");
            fos.close();
        }catch(Exception e){

        }
    }
}
