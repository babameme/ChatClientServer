import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client extends JFrame implements ActionListener{
    static JTextArea content;
    static JButton send;
    static JTextField inputJText, toName;
    static String inputString = "", temp = "", addrr = "";
    static Socket s, sB;
    static PrintWriter newSend;

    public static void main(String[] args) {
        try{
            String ip = JOptionPane.showInputDialog(null, "Input IP server");
            new client();
            sB = new Socket(ip, 222);
            BufferedReader nhan = new BufferedReader(new InputStreamReader(sB.getInputStream()));
            while ((inputString = nhan.readLine()) != null){
                temp += inputString + "\n";
                content.setText(temp);
                content.setVisible(false);
                content.setVisible(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public client(){
        setSize(600, 600);
        setTitle("Client");
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
                newSend.println(toName.getText() + ": " + inputJText.getText());
                temp += toName.getText() + ": " + inputJText.getText() + "\n";
                inputJText.setText("");
                inputJText.requestFocus();
                content.setText(temp);
                content.setVisible(false);
                content.setVisible(true);
            }catch (Exception r){
                r.printStackTrace();
            }
        }
    }
}
