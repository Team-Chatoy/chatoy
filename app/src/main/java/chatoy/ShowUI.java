package chatoy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ShowUI {
  public void UI() {
    // 创建窗体
    JFrame frame = new JFrame();
    frame.setLayout(null);

    // Set the look and feel
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (Exception e) {
      System.out.println("Error setting the LAF!");
      e.printStackTrace();
    }

    // 除了 JFrame 设置大小为 setSize(int x, int y)
    // 其它组件都用 setPreferredSize(Dimension d)

    // 输入框
    JLabel usernameLabel = new JLabel("账号：");
    usernameLabel.setBounds(15, 10, 65, 30);
    frame.add(usernameLabel);

    JTextField usernameField = new JTextField();
    usernameField.setBounds(60, 10, 310, 30);
    frame.add(usernameField);

    JLabel passwordLabel = new JLabel("密码：");
    passwordLabel.setBounds(15, 50, 65, 30);
    frame.add(passwordLabel);

    JPasswordField passwordField = new JPasswordField();
    passwordField.setBounds(60, 50, 310, 30);
    frame.add(passwordField);

    // 复选框
    JCheckBox rememberPasswd = new JCheckBox("记住密码");
    rememberPasswd.setBounds(290, 90, 100, 40);
    rememberPasswd.addActionListener(e -> {
      JOptionPane.showMessageDialog(frame, "您确定要记住密码吗？");
    });
    frame.add(rememberPasswd);

    JCheckBox autoLogin = new JCheckBox("自动登录");
    autoLogin.setBounds(210, 90, 100, 40);
    autoLogin.addActionListener(e -> {
      JOptionPane.showMessageDialog(frame, "下次登录将自动登录！");
    });
    frame.add(autoLogin);

    // 按钮
    JButton registButton = new JButton("注册");
    registButton.setBounds(70, 150, 90, 40);
    frame.add(registButton);

    JButton loginButton = new JButton("登录");
    loginButton.setBounds(220, 150, 90, 40);
    ButtonListener onLogin = new ButtonListener(usernameField, passwordField);
    loginButton.addActionListener(onLogin);
    frame.add(loginButton);

    // 背景图片
    var bgPicUrl = this.getClass().getResource("/img/background.png");
    ImageIcon bgPic = new ImageIcon(bgPicUrl);
    var bgPanel = new PicturePanel(bgPic.getImage());
    bgPanel.setBounds(0, 0, 400, 300);
    frame.getContentPane().add(bgPanel);

    frame.setSize(400, 300);              // 窗体大小
    frame.setTitle("Chatoy 登录界面");     // 窗体名字
    frame.setLocationRelativeTo(null);    // 相对屏幕居中
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // 窗体可见，写在 add 组件之后
    frame.setVisible(true);
  }

  // 判断账号密码是否正确——正确显示“登陆成功”，不正确显示“登录失败”
  public static class ButtonListener implements ActionListener {
    private JTextField usernameField;
    private JTextField passwordField;

    // 重写接口中的所有方法：actionPerformed 为 ActionListener 中的方法
    // 当该事件发生，这个方法会被调用
    public void actionPerformed(ActionEvent e) {
      System.out.println("按钮被点击！");
      validate(
        usernameField.getText(),
        passwordField.getText()
      );
      passwordField.setText("");
    }

    // 将界面中文本框的值传入事件监听器中
    public ButtonListener(
      JTextField initUsernameField,
      JTextField initPasswordField
    ) {
      this.usernameField = initUsernameField;
      this.passwordField = initPasswordField;
    }

    // 判断方法——判断登陆成功 or 失败，显示弹框信息后，初始化输入框文本为空 “”
    private void validate(String username, String password) {
      if (username.equals("root") && password.equals("root")) {
        System.out.println("登陆成功！");
        JOptionPane.showMessageDialog(null, "登录成功！");
      } else {
        System.out.println("登录失败！");
        JOptionPane.showMessageDialog(null, "登录失败！");
      }
    }
  }

  public static void main(String[] args) {
    ShowUI tf = new ShowUI();
    tf.UI();
  }
}
