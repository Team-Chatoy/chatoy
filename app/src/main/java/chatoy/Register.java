package chatoy;

import java.awt.Container;

import javax.swing.*;

public class Register extends JFrame {
  private JLabel usernameLabel;
  private JLabel tipNameLabel;
  private JLabel passwdLabel;
  private JLabel tipPasswdLabel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton registerButton;
  private JCheckBox agreeService;

  public Register() {
    Container container = this.getContentPane();
    container.setLayout(null);

    // Labels
    usernameLabel = new JLabel("昵称：");
    usernameLabel.setBounds(50, 20, 300, 30);
    container.add(usernameLabel);

    tipNameLabel = new JLabel("请输入你的昵称");
    tipNameLabel.setBounds(50, 50, 300, 30);
    container.add(tipNameLabel);

    passwdLabel = new JLabel("密码：");
    passwdLabel.setBounds(50, 130, 300, 30);
    container.add(passwdLabel);

    tipPasswdLabel = new JLabel("请输入你的密码");
    tipPasswdLabel.setBounds(50, 160, 300, 30);
    container.add(tipPasswdLabel);

    // Fields
    usernameField = new JTextField();
    usernameField.setBounds(50, 90, 300, 30);
    container.add(usernameField);

    passwordField = new JPasswordField();
    passwordField.setBounds(50, 200, 300, 30);
    container.add(passwordField);

    // Checkbox and button
    agreeService = new JCheckBox("我同意所有关于服务的声明");
    agreeService.setBounds(65, 240, 270, 30);
    container.add(agreeService);

    registerButton = new JButton("注册");
    registerButton.setBounds(65, 290, 270, 60);
    container.add(registerButton);

    // 背景图片
    var bgPicUrl = this.getClass().getResource("/img/background.png");
    ImageIcon bgPic = new ImageIcon(bgPicUrl);
    var bgPanel = new PicturePanel(bgPic.getImage());
    bgPanel.setBounds(0, 0, 400, 450);
    container.add(bgPanel);

    this.setSize(400, 450);
    this.setTitle("Chatoy 注册界面");
    this.setVisible(true);
  }
}
