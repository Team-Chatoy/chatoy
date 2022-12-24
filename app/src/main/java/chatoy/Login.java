package chatoy;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
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

  public Login() {
    this.setTitle("Chatoy 注册界面");
    this.setSize(500, 700);
    Container container = this.getContentPane();
    container.setLayout(null);
    Font font = new Font("Serif", Font.PLAIN, 15);
    jlb1 = new JLabel("FULL NAME");
    jlb2 = new JLabel("Please enter your full name");
    jlb2.setFont(font);
    jlb3 = new JLabel("E-MAIL");
    jlb4 = new JLabel("Your e-mail goes here");
    jlb4.setFont(font);
    jlb5 = new JLabel("PASSWORD");
    container.add(jlb1);
    container.add(jlb2);
    container.add(jlb3);
    container.add(jlb4);
    container.add(jlb5);
    jlb1.setBounds(50, 50, 250, 20);
    jlb2.setBounds(50, 80, 400, 20);
    jlb3.setBounds(50, 200, 250, 20);
    jlb4.setBounds(50, 230, 400, 20);
    jlb5.setBounds(50, 350, 250, 20);
    jtf1 = new JTextField();
    jtf2 = new JTextField();
    jpf = new JPasswordField();
    container.add(jtf1);
    container.add(jtf2);
    container.add(jpf);
    jtf1.setBounds(50, 120, 400, 40);
    jtf2.setBounds(50, 270, 400, 40);
    jpf.setBounds(50, 390, 400, 40);
    jcb = new JCheckBox("I agree all statements in Terms of service");
    jcb.setFont(font);
    jbt = new JButton("LOG IN");
    container.add(jcb);
    container.add(jbt);
    jcb.setBounds(65, 450, 370, 40);
    jbt.setBounds(65, 500, 370, 60);
    this.setVisible(true);
  }
}
