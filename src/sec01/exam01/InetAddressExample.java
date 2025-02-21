package sec01.exam01;

import java.net.*;

public class InetAddressExample {
    public static void main(String[] args) {
       InetAddress ip;
       try{
           ip = InetAddress.getByName("www.korea.go.kr");
           System.out.println("Host Name : " + ip.getHostName());
           System.out.println("Host Address : " + ip.getHostAddress());
           System.out.println("Local Host Address : " + InetAddress.getLocalHost().getHostAddress());
       }catch(UnknownHostException ue){
           System.out.println(ue);
       }
       System.out.println();
    }
}