package chatoy;

import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import utils.PathUtils;
import utils.ScreenUtils;
import component.allframe.BackgroundPanel;

public class App {
  jni.Utils jni = new jni.Utils();
  JFrame theFrame = new JFrame("Chatoy");

  final int WIDTH = 550;
  final int HEIGHT = 550 * 1584 / 1800; // 背景图片 1800 * 1584

  JTextField accountTextField;
  JPasswordField passwordTextField;
  Font font;
  Border etchedBorder =
    BorderFactory.createEtchedBorder(BevelBorder.RAISED, Color.white, Color.pink);
  Border lineBorder =
    BorderFactory.createLineBorder(Color.pink);

  // 组装视图
  public void init() throws IOException {
    // 全局微软雅黑字体
    font = new Font("微软雅黑", Font.PLAIN, 12);
    InitGlobalFont(font);

    // 设置窗口相关属性
    theFrame.setBounds(
      (ScreenUtils.getScreenWidth() - WIDTH) / 2,
      (ScreenUtils.getScreenHeight() - HEIGHT) / 2,
      WIDTH,
      HEIGHT
    );
    theFrame.setResizable(false);
    theFrame.setIconImage(
      new ImageIcon(
        this.getClass()
          .getResource(PathUtils.getRealPath("logo.png"))
      )
      .getImage()
    );

    // 设置窗口的内容
    BackgroundPanel backgroundPanel =
      new BackgroundPanel(
        new ImageIcon(
          this.getClass()
            .getResource(PathUtils.getRealPath("LoginBackground.png"))
        )
        .getImage()
      );

    // 组装登录相关的元素
    Box holeBox = Box.createVerticalBox();

    // 输入网址
    Box httpBox = Box.createHorizontalBox();
    JLabel httpLabel = new JLabel("Server address: ");
    httpLabel.setForeground(Color.pink); // 字体设为粉色
    JTextField httpTextField = new JTextField();
    httpTextField.setForeground(Color.gray); // 文本框字体设为浅灰色
    httpTextField.setOpaque(false); // 文本框设置为透明
    httpTextField.setBorder(lineBorder); // 粉色边框

    httpBox.add(httpLabel);
    httpBox.add(Box.createHorizontalStrut(6));
    httpBox.add(httpTextField);

    // 组装用户名
    Box accountBox = Box.createHorizontalBox();
    JLabel accountLabel = new JLabel("Username: ");
    accountLabel.setForeground(Color.pink); // 字体设为粉色
    accountTextField = new JTextField(15);
    accountTextField.setForeground(Color.gray); // 文本框字体设为浅灰色
    accountTextField.setOpaque(false); // 文本框设置为透明
    accountTextField.setBorder(lineBorder); // 粉色边框

    accountBox.add(accountLabel);
    accountBox.add(Box.createHorizontalStrut(6));
    accountBox.add(accountTextField);

    // 组装密码
    Box passwordBox = Box.createHorizontalBox();
    JLabel passwordLabel = new JLabel("Password: ");
    passwordLabel.setForeground(Color.pink); // 字体设为粉色
    passwordTextField = new JPasswordField(15);
    passwordTextField.setForeground(Color.white); // 文本框字体设为浅灰色
    passwordTextField.setOpaque(false); // 文本框设置为透明
    passwordTextField.setBorder(lineBorder); // 粉色边框

    passwordBox.add(passwordLabel);
    passwordBox.add(Box.createHorizontalStrut(6));
    passwordBox.add(passwordTextField);

    // 组装按钮
    Box buttonBox = Box.createHorizontalBox();
    JButton loginButton = new JButton("登录");
    loginButton.setBackground(Color.pink);
    JButton registerButton = new JButton("注册");
    registerButton.setBackground(Color.pink);

    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // 获取用户输入的数据
        String server = httpTextField.getText();
        String username = accountTextField.getText();
        String password = new String(passwordTextField.getPassword());

        var resp = jni.login(server, username, password);

        if (resp.getCode() == 0) {
          String token = resp.getMsg();
          try {
            new Chatoy(server, token).init();
            theFrame.dispose();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        } else {
          String msg = resp.getMsg();
          System.err.println(msg);
          // TODO: alert
        }
      }
    });

    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new Register().init();
          theFrame.dispose();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });

    buttonBox.add(loginButton);
    buttonBox.add(Box.createHorizontalStrut(50));
    buttonBox.add(registerButton);

    // Chatoy 大 title
    JLabel title = new JLabel("Chatoy", SwingConstants.CENTER);
    Font titleFont = new Font("Cabin Sketch", Font.ITALIC, 70);
    title.setForeground(Color.pink);
    title.setFont(titleFont);
    title.setLocation(50, 50);
    Box titleBox = Box.createHorizontalBox();
    titleBox.add(title);

    holeBox.add(Box.createVerticalStrut(20));
    holeBox.add(titleBox);
    holeBox.add(Box.createVerticalStrut(25));
    holeBox.add(httpBox);
    holeBox.add(Box.createVerticalStrut(15));
    holeBox.add(accountBox);
    holeBox.add(Box.createVerticalStrut(15));
    holeBox.add(passwordBox);
    holeBox.add(Box.createVerticalStrut(20));
    holeBox.add(buttonBox);

    // 创建一个纯色底面
    JPanel jPanel = new JPanel();
    jPanel.setBorder(etchedBorder);
    jPanel.setBounds(105, 65, 340, 310);
    jPanel.setBackground(new Color(5, 5, 5));
    jPanel.add(holeBox);
    backgroundPanel.setLayout(null);
    backgroundPanel.add(jPanel);

    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    theFrame.add(backgroundPanel);
    theFrame.setVisible(true);
  }

  public static void main(String[] args) throws IOException {
    App login = new App();
    login.init();
  }

  private static void InitGlobalFont(Font font) {
    FontUIResource fontRes = new FontUIResource(font);
    for (var keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof FontUIResource) {
        UIManager.put(key, fontRes);
      }
    }
  }
}
