package sec01.exam01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;

public class CheckURL extends Frame {
    Panel panel1; // 상단 입력 및 버튼 패널
    Panel panel2; // 하단 결과 표시 패널
    TextField textfield;
    TextArea textarea;
    Button hostInfo;
    Label label1;
    Label label2;
    InetAddress ip;

    public CheckURL() {
        setTitle("URL 주소 확인");
        setSize(600, 400);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10)); // 프레임에 여백 추가

        // 패널1 설정 (상단 입력 및 버튼)
        panel1 = new Panel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel1.setBackground(Color.GRAY);

        label1 = new Label("호스트 이름 : ");
        textfield = new TextField(40); // TextField의 너비 설정
        hostInfo = new Button("호스트 정보");

        // 버튼 액션 리스너 (람다식 사용)
        hostInfo.addActionListener((e) -> {
            String name = textfield.getText();
            try {
                ip = InetAddress.getByName(name);
                textarea.setText(""); // 기존 텍스트 초기화
                String str = "호스트 이름: " + ip.getHostName() + "\n";
                str += "호스트 주소: " + ip.getHostAddress() + "\n";
                str += "로컬 호스트 주소: " + InetAddress.getLocalHost().getHostAddress() + "\n";
                textarea.append(str);
            } catch (UnknownHostException ue) {
                textarea.setText("해당 호스트가 존재하지 않습니다.\n");
            }
        });

        panel1.add(label1);
        panel1.add(textfield);
        panel1.add(hostInfo);

        // 패널2 설정 (하단 결과 표시)
        panel2 = new Panel(new BorderLayout());
        panel2.setBackground(Color.LIGHT_GRAY);

        label2 = new Label("URL 정보:", Label.LEFT);
        textarea = new TextArea();
        textarea.setEditable(false); // 출력용 TextArea는 수정 불가능하게 설정

        panel2.add(label2, BorderLayout.NORTH);
        panel2.add(textarea, BorderLayout.CENTER);

        // 메인 프레임에 패널 추가
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);

        // 윈도우 닫기 이벤트
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckURL();
    }
}
