package chatoy;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame{
    private JLabel usernameLabel;
    private JLabel tipNameLabel;
    private JLabel passwordLabel;
    private JLabel tipPasswdLabel;
    private JTextField usernamefield;
    private JPasswordField passwordfield;
    private JButton registerbutton;
    private JCheckBox agreeservice;

    public Register() {
        this.setTitle("Chatoy注册界面");
        this.setSize(400,450);
        Container container = this.getContentPane();
        container.setLayout(null);

        //背景图片
        var bgPicUrl = this.getClass().getResource("/img/background.png");
        ImageIcon bgPic = new ImageIcon(bgPicUrl);
        var bgPanel = new PicturePanel(bgPic.getImage());
        bgPanel.setBounds(0, 0, 400, 450);
        container.add(bgPanel);

        usernameLabel = new JLabel("昵称：");
        tipNameLabel = new JLabel("请输入你的昵称");
        passwordLabel = new JLabel("密码：");
        tipPasswdLabel = new JLabel("请输入你的密码");
        container.add(usernameLabel);
        container.add(tipNameLabel);
        container.add(passwordLabel);
        container.add(tipPasswdLabel);
        usernameLabel.setBounds(50, 20, 300, 30);
        tipNameLabel.setBounds(50, 50, 300, 30);
        passwordLabel.setBounds(50, 130, 300, 30);
        tipPasswdLabel.setBounds(50, 160, 300, 30);
        usernamefield = new JTextField();
        passwordfield = new JPasswordField();
        container.add(usernamefield);
        container.add(passwordfield);
        usernamefield.setBounds(50, 90, 300, 30);
        passwordfield.setBounds(50, 200, 300, 30);
        agreeservice = new JCheckBox("我同意所有关于服务的声明");
        registerbutton = new JButton("注册");
        container.add(agreeservice);
        container.add(registerbutton);
        agreeservice.setBounds(65, 240, 270, 30);
        registerbutton.setBounds(65, 290, 270, 60);
        this.setVisible(true);

    }

}
