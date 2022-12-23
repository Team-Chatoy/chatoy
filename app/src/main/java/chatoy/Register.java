package src.main.java.chatoy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener{

    JLabel jbl1;
    JTabbedPane jtp;
    JPanel jp2, jp3, jp4;
    JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3;
    JTextField jp2_jtf;
    JPasswordField jp2_jpf;
    JCheckBox jp2_jcb1, jp2_jcb2;
    JPanel jp1;
    JButton jp1_jb1;
    JButton jp1_jb2;

    public static void main(String[] args) {
        Register frame = new Register("Chatoy登陆界面");
        frame.setSize(400,150);
        frame.setVisible(true);
    }

    public Register(String str){

        super(str);
        jbl1 = new JLabel(new ImageIcon());
        jp1 = new JPanel();
        jp1_jb1 = new JButton("登录");
        jp1_jb2 = new JButton("注册");
        jp1.add(jp1_jb1);
        jp1.add(jp1_jb2);

        jp2 = new JPanel(new GridLayout(3,3));
        jp2_jbl1 = new JLabel("账号",JLabel.CENTER);
        jp2_jbl2 = new JLabel("密码",JLabel.CENTER);
        jp2_jbl3 = new JLabel("忘记密码",JLabel.CENTER);
        jp2_jbl3.setForeground(Color.blue);
        jp2_jtf = new JTextField();
        jp2_jpf = new JPasswordField();
        jp2_jcb1 = new JCheckBox("隐身登录");
        jp2_jcb2 = new JCheckBox("记住密码");

        jp2.add(jp2_jbl1);
        jp2.add(jp2_jtf);
        jp2.add(jp2_jbl2);
        jp2.add(jp2_jpf);
        jp2.add(jp2_jbl3);
        jp2.add(jp2_jcb1);
        jp2.add(jp2_jcb2);

        jtp = new JTabbedPane();
        jtp.add("账号",jp2);
        jp3 = new JPanel();
        jtp.add("手机号码",jp3);
        jp4 = new JPanel();
        jtp.add("电子邮件",jp4);

        this.setSize(550, 340);
        this.setIconImage((new ImageIcon()).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(jbl1,"North");
        this.add(jp1,"South");
        this.add(jtp,"Center");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
