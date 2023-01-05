package chatoy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import utils.PathUtils;
import utils.ScreenUtils;
import component.allframe.BackgroundPanel;

public class Register {
  JFrame theFrame = new JFrame("注册");

  final int WIDTH = 550;
  final int HEIGHT = 550 * 1584 / 1800; // 背景图片 1800 * 1584
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

    // 设置窗口的属性
    theFrame.setBounds(
            (ScreenUtils.getScreenWidth() - WIDTH) / 2,
            (ScreenUtils.getScreenHeight() - HEIGHT) / 2,
            WIDTH,
            HEIGHT
    );
    theFrame.setResizable(false);

    ImageIcon logoIcon = new ImageIcon(
            this.getClass()
                    .getResource(PathUtils.getRealPath("logo.png"))
    );
    theFrame.setIconImage(logoIcon.getImage());
    ImageIcon loginBackgroundIcon = new ImageIcon(
            this.getClass()
                    .getResource(PathUtils.getRealPath("LoginBackground.png"))
    );
    BackgroundPanel backgroundPanel = new BackgroundPanel(loginBackgroundIcon.getImage());
    backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);

    Box holeBox = Box.createVerticalBox();

    // 组装用户名
    Box userNameBox = Box.createHorizontalBox();
    JLabel userNameLabel = new JLabel("用  户  名："); // review: it's a bad way to set text
    userNameLabel.setForeground(Color.pink);
    JTextField userTextField = new JTextField("请输入用户名",15);
    userTextField.setOpaque(false);
    userTextField.setForeground(Color.gray);
    userTextField.setBorder(lineBorder); // 粉色边框

    userNameBox.add(userNameLabel);
    userNameBox.add(Box.createHorizontalStrut(8));
    userNameBox.add(userTextField);

    userTextField.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        if(userTextField.getText().equals("")){
          userTextField.setText("请输入用户名");
          userTextField.setForeground(Color.gray);
        }
      }
      @Override
      public void mouseEntered(MouseEvent e) {
        if(userTextField.getText().equals("请输入用户名")){
          userTextField.setText("");
          userTextField.setForeground(Color.pink);
        }
      }
    });

    // 组装密码
    Box passwordBox = Box.createHorizontalBox();
    JLabel passwordLabel = new JLabel("密       码："); // review: the same as above
    passwordLabel.setForeground(Color.pink);
    JPasswordField passwordTextField = new JPasswordField(15);
    passwordTextField.setOpaque(false);
    passwordTextField.setForeground(Color.white);
    passwordTextField.setBorder(lineBorder); // 粉色边框

    passwordBox.add(passwordLabel);
    passwordBox.add(Box.createHorizontalStrut(9));
    passwordBox.add(passwordTextField);

    // 组装确认密码
    Box password2Box = Box.createHorizontalBox();
    JLabel password2Label = new JLabel("确认密码：");
    password2Label.setForeground(Color.pink);
    JPasswordField password2TextField = new JPasswordField(15);
    password2TextField.setOpaque(false);
    password2TextField.setForeground(Color.white);
    password2TextField.setBorder(lineBorder); // 粉色边框

    password2Box.add(password2Label);
    password2Box.add(Box.createHorizontalStrut(10));
    password2Box.add(password2TextField);

    // 组装按钮
    Box buttonBox = Box.createHorizontalBox();
    JButton regisButton = new JButton("注册");
    regisButton.setBackground(Color.pink);
    JButton backButton = new JButton("返回登录界面");
    backButton.setBackground(Color.pink);

    regisButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // 获取用户录入的信息
        String userName = userTextField.getText().trim();
        String password = passwordTextField.getPassword().toString();
        String password2 = password2TextField.getPassword().toString();

        if (!password.equals(password2)) {
          // alert
          return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("userName", userName);
        params.put("password", password);

        // 访问后台接口
      }
    });

    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new App().init();
          theFrame.dispose();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });

    buttonBox.add(regisButton);
    buttonBox.add(Box.createHorizontalStrut(40));
    buttonBox.add(backButton);

    // Chatoy 大 title
    JLabel title = new JLabel("Register", SwingConstants.CENTER);
    Font titleFont=new Font("Cabin Sketch", Font.ITALIC, 50);
    title.setForeground(Color.white);
    title.setFont(titleFont);
    title.setLocation(50, 50);
    Box titleBox = Box.createHorizontalBox();
    titleBox.add(title);

    holeBox.add(Box.createVerticalStrut(18));
    holeBox.add(titleBox);
    holeBox.add(Box.createVerticalStrut(24));
    holeBox.add(userNameBox);
    holeBox.add(Box.createVerticalStrut(18));
    holeBox.add(passwordBox);
    holeBox.add(Box.createVerticalStrut(18));
    holeBox.add(password2Box);
    holeBox.add(Box.createVerticalStrut(25));
    holeBox.add(buttonBox);

    // 创建一个纯色底面
    JPanel jPanel = new JPanel();
    jPanel.setBorder(etchedBorder);
    jPanel.setBounds(105, 70, 340, 300);
    jPanel.setBackground(new Color(5, 5, 5));
    jPanel.add(holeBox);
    backgroundPanel.setLayout(null);
    backgroundPanel.add(jPanel);

    theFrame.add(backgroundPanel);
    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    theFrame.setVisible(true);
  }

  public static void main(String[] args) throws IOException {
    new Register().init();
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
