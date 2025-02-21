package sec01.exam02;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
       int port = 6000;
       try{
           ServerSocket serverSocket = new ServerSocket(port);
           System.out.println("서버가 클라이언트를 기다리는중...");
           while(true){
               Socket serverClientsocket = serverSocket.accept();
               System.out.println("클라이언트와 연결 성공");

               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serverClientsocket.getInputStream()));
               PrintWriter writer = new PrintWriter(serverClientsocket.getOutputStream(), true);

               String message = bufferedReader.readLine();
               System.out.println("클라이언트로부터 받은 메세지 : " + message);

               writer.println("서버의 응답 메세지 : nice");

               serverClientsocket.close();
               System.out.print("클라이언트 연결 종료");
           }
        }catch(IOException e){
           e.printStackTrace();
       }
    }
}
