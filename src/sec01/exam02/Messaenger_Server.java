package sec01.exam02;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Messaenger_Server {
    public static void main(String[] args){
        int port = 6000;
        Scanner scanner = new Scanner(System.in);
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("클라이언트의 연결을 기다리는중...");

            Socket serverClientSocket = serverSocket.accept();
            System.out.println("클라이언트와 연결되었습니다!");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(serverClientSocket.getInputStream()));
            PrintWriter printWriter= new PrintWriter(serverClientSocket.getOutputStream(), true);

            new Thread(() -> {
                String message;
                try{
                    while((message = bufferedReader.readLine()) != null){
                        if(message.equals("exit")){
                            System.out.println("\n클라이언트가 연결을 종료했습니다.");
                            break;
                        }
                        System.out.println("\n클라이언트 : "+ message);
                        System.out.print("보낼 메세지 : ");
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }).start();


            String message;
            while(true){
                System.out.print("보낼 메세지 : ");
                message = scanner.nextLine();
                printWriter.println(message);
                if(message.equals("exit")){
                    System.out.println("서버가 연결을 종료했습니다.");
                    break;
                }
            }
            serverClientSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
