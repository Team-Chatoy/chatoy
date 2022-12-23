package src.main.java.chatoy;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login extends JPanel implements ActionListener,ItemListener{

    private JLabel jlb1;
    private JLabel jlb2;
    private JLabel jlb3;
    private JLabel jlb4;
    private JLabel jlb5;
    private JTextField jtf1;
    private JTextField jtf2;
    private JPasswordField jpf;
    private JButton jbt;
    private JCheckBox jcb;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chatoy注册界面");
        frame.setContentPane(new Login());
        frame.setSize(500,700);
        frame.setVisible(true);
    }

    public Login() {
        setLayout(null);
        Font font = new Font("Serif",Font.PLAIN,15);
        jlb1 = new JLabel("FULL NAME");
        jlb2 = new JLabel("Please enter your full name");
        jlb2.setFont(font);
        jlb3 = new JLabel("E-MAIL");
        jlb4 = new JLabel("Your e-mail goes here");
        jlb4.setFont(font);
        jlb5 = new JLabel("PASSWORD");
        add(jlb1);
        add(jlb2);
        add(jlb3);
        add(jlb4);
        add(jlb5);
        jlb1.setBounds(50, 50, 250, 20);
        jlb2.setBounds(50, 80, 400, 20);
        jlb3.setBounds(50, 200, 250, 20);
        jlb4.setBounds(50, 230, 400, 20);
        jlb5.setBounds(50, 350, 250, 20);
        jtf1 = new JTextField();
        jtf2 = new JTextField();
        jpf = new JPasswordField();
        add(jtf1);
        add(jtf2);
        add(jpf);
        jtf1.setBounds(50, 120, 400, 40);
        jtf2.setBounds(50, 270, 400, 40);
        jpf.setBounds(50, 390, 400, 40);
        jcb = new JCheckBox("I agree all statements in Terms of service");
        jbt = new JButton("LOG IN");
        add(jcb);
        add(jbt);
        jcb.setBounds(65, 450, 370, 40);
        jbt.setBounds(65, 500, 370, 60);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub

    }
}
