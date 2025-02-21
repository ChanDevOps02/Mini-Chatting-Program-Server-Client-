package sec01.exam01;

import java.net.*;

public class URLBasic {
    public static void main(String[] args) {
        try{
            URL url = new URL("http://www.naver.com");
            System.out.println("Protocol : " + url.getProtocol());
            System.out.println("Port : " + url.getPort());
            System.out.println("Host : " + url.getHost());
            System.out.println("File : " + url.getFile());
            System.out.println("Ext : " + url.toExternalForm());
        }catch(MalformedURLException me){
            System.out.println();
        }
    }
}
