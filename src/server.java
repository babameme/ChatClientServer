import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends JFrame implements ActionListener{
    static JTextArea content;
    static JButton send;
    static JTextField nhap, toName;
    static String chuoi = "";
    static String temp = "";
    static ServerSocket serA;
    static Socket s, sA;
    static PrintWriter gui;

    public static void main(String[] args) {
        new server();
        try{
            serA = new ServerSocket(2222);
            s = serA.accept();
            while (true){
                BufferedReader nhan = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while ((chuoi = nhan.readLine()) != null){
                    temp += chuoi + "\n";
                    content.setText(temp);
                    content.setVisible(false);
                    content.setVisible(true);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
