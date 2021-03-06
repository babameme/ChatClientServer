import javax.swing.*;
import java.awt.*;
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
    static JTextField inputJText, toName;
    static String inputString = "";
    static String temp = "";
    static ServerSocket serA;
    static Socket s, sA;
    static PrintWriter newSend;

    public static void main(String[] args) {
        new server();
        try{
            serA = new ServerSocket(2222);
            s = serA.accept();
            while (true){
                BufferedReader nhan = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while ((inputString = nhan.readLine()) != null){
                    temp += inputString + "\n";
                    content.setText(temp);
                    content.setVisible(false);
                    content.setVisible(true);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public server(){
        setSize(600, 600);
        setTitle("Server Chat B16D47");
        Font font = new Font("Arial", Font.BOLD, 20);

        content = new JTextArea();
        content.setFont(font);
        content.setBackground(Color.yellow);
        content.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(content);

        send = new JButton("Send");
        inputJText = new JTextField(30);
        inputJText.setFont(font);
        toName = new JTextField("Enter your name");
        toName.setFont(font);
        toName.setBackground(Color.RED);

        add(toName, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        add(inputJText, BorderLayout.PAGE_END);
        inputJText.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(inputJText)){
            try{
                newSend = new PrintWriter(s.getOutputStream(), true);
                temp += toName.getText() + ": " + inputJText.getText() + "\n";
                newSend.println(toName.getText() + ": " + inputJText.getText());
                content.setText(temp);
                inputJText.setText("");
                inputJText.requestFocus();
                content.setVisible(false);
                content.setVisible(true);
            }catch (Exception r){
                r.printStackTrace();
            }
        }
    }
}
