package sec01.exam03;

import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RectangularShape;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class ChanKaoTalk_Server extends Frame implements KeyListener {
    Color BigoneColor = Color.WHITE;
    Button changeBackground;
    Button send;
    Button matching;
    Panel panel;
    TextArea Bigone;
    TextArea Smallone;
    Label label;
    String labelString = "Waiting for Client...";
    List<String>chats = new ArrayList<>();
    ServerSocket serverSocket;
    Socket serverClientSocket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;


    public ChanKaoTalk_Server(){
        setTitle("ChanKaoTalk (For Server)");
        setLayout(new BorderLayout());
        setSize(800, 800);
        setLocationRelativeTo(null);

        panel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(Color.GRAY);

        label = new Label(labelString);

        changeBackground = new Button("Change Color");
        changeBackground.addActionListener((ae) -> {
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            BigoneColor = new Color(red, green, blue);
            repaint();
        });
        send = new Button("Send");
        send.addActionListener((ae) -> {
            sendMessage();
        });
        matching = new Button("Matching");
        matching.addActionListener((ae) -> {
            int port = 6000;
            try {
                serverSocket = new ServerSocket(port);
                serverClientSocket = serverSocket.accept();
                if (serverClientSocket.isConnected()) {
                    setLabel("Client is now connected with Server");

                    // 👉 초기화 위치 수정
                    bufferedReader = new BufferedReader(new InputStreamReader(serverClientSocket.getInputStream()));
                    printWriter = new PrintWriter(serverClientSocket.getOutputStream(), true); // autoFlush 활성화

                    startMessageReceiver();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        panel.add(label);
        panel.add(matching);
        panel.add(changeBackground);
        panel.add(send);

        Bigone = new TextArea();
        Bigone.setEditable(false);

        Smallone = new TextArea();
        Smallone.addKeyListener(this);

        add(panel, BorderLayout.NORTH);
        add(Bigone, BorderLayout.CENTER);
        add(Smallone, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
    public void sendMessage(){
        String message = Smallone.getText().trim();
        if(!message.isEmpty()){
            chats.add("Server : " + message + "\n");
            Bigone.append("Server : " + message + "\n");
            Smallone.setText("");
            sendToOther(message);
        }
    }
    private void startMessageReceiver() {
        new Thread(() -> {
            String otherMessage;
            try {
                while ((otherMessage = bufferedReader.readLine()) != null) {
                    System.out.println("Received: " + otherMessage); // 디버깅용 로그
                    Bigone.append(otherMessage + "\n"); // UI 업데이트
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void sendToOther(String message){
        new Thread(() -> {
            printWriter.println("Server : " + message + "\n");
            printWriter.flush();
        }).start();
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            sendMessage();
        }
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyReleased(KeyEvent e){

    }
    public void paint(Graphics g){
        super.paint(g);
        Bigone.setBackground(BigoneColor);
    }
    public void setLabel(String message){
        label.setText(message);
    }

    public static void main(String[] args){
        ChanKaoTalk_Server frame = new ChanKaoTalk_Server();
    }
}


