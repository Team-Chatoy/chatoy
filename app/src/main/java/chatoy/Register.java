package chatoy;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Register extends JFrame {
  private JPanel jpl1;
  private JPanel jpl2;
  private JPanel jpl3;
  private JPanel jpl4;
  private JLabel jlb1;
  private JLabel jlb2;
  private JLabel jlb3;
  private JLabel jlb4;
  private JTextField jtf;
  private JPasswordField jpf;
  private JCheckBox jcb;
  private JTabbedPane jtp;
  private JButton jbt1;
  private JButton jbt2;

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    } catch (Exception ee) {
      ee.printStackTrace();
    }

    new Register();
  }

  public Register() {
    Font font = new Font("Serif", Font.PLAIN, 15);
    ListenerOne listener = new ListenerOne();
    jpl1 = new JPanel();
    jpl1.setLayout(null);
    jbt1 = new JButton("登录");
    jbt2 = new JButton("注册");
    jpl1.add(jbt1);
    jpl1.add(jbt2);
    jbt1.setBounds(50, 380, 190, 50);
    jbt2.setBounds(260, 380, 190, 50);
    jbt2.addActionListener(listener);

    jpl2 = new JPanel();
    jpl2.setLayout(null);
    jlb1 = new JLabel("账号");
    jlb2 = new JLabel("密码");
    jtf = new JTextField();
    jpf = new JPasswordField();
    jcb = new JCheckBox("记住密码");
    jcb.setFont(font);
    jpl2.add(jlb1);
    jpl2.add(jlb2);
    jpl2.add(jtf);
    jpl2.add(jpf);
    jpl2.add(jcb);
    jlb1.setBounds(50, 20, 300, 20);
    jlb2.setBounds(50, 120, 300, 20);
    jtf.setBounds(50, 60, 300, 30);
    jpf.setBounds(50, 160, 300, 30);
    jcb.setBounds(50, 230, 300, 30);
    jtp = new JTabbedPane();
    jtp.addTab("账号", jpl2);

    jpl3 = new JPanel();
    jpl3.setLayout(null);
    jlb3 = new JLabel("电话号码");
    jlb2 = new JLabel("密码");
    jtf = new JTextField();
    jpf = new JPasswordField();
    jcb = new JCheckBox("记住密码");
    jcb.setFont(font);
    jpl3.add(jlb3);
    jpl3.add(jlb2);
    jpl3.add(jtf);
    jpl3.add(jpf);
    jpl3.add(jcb);
    jlb3.setBounds(50, 20, 300, 20);
    jlb2.setBounds(50, 120, 300, 20);
    jtf.setBounds(50, 60, 300, 30);
    jpf.setBounds(50, 160, 300, 30);
    jcb.setBounds(50, 230, 300, 30);
    jtp.addTab("电话号码", jpl3);

    jpl4 = new JPanel();
    jpl4.setLayout(null);
    jlb4 = new JLabel("电子邮件");
    jlb2 = new JLabel("密码");
    jtf = new JTextField();
    jpf = new JPasswordField();
    jcb = new JCheckBox("记住密码");
    jcb.setFont(font);
    jpl4.add(jlb4);
    jpl4.add(jlb2);
    jpl4.add(jtf);
    jpl4.add(jpf);
    jpl4.add(jcb);
    jlb4.setBounds(50, 20, 300, 20);
    jlb2.setBounds(50, 120, 300, 20);
    jtf.setBounds(50, 60, 300, 30);
    jpf.setBounds(50, 160, 300, 30);
    jcb.setBounds(50, 230, 300, 30);
    jtp.addTab("电子邮件", jpl4);

    jpl1.add(jtp);
    jtp.setBounds(50, 50, 400, 300);
    this.add(jpl1);
    this.setTitle("Chatoy 登陆界面");
    this.setSize(500, 500);
    this.setVisible(true);
  }

  class ListenerOne implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == jbt2) {
        new chatoy.Login();
      }
    }
  }
}
