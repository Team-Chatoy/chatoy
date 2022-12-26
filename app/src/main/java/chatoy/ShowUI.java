package chatoy;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.*;
import javax.swing.*;

public class ShowUI {
  public void UI() {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    } catch (InstantiationException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
    }

    // 创建窗体
    JFrame frame = new JFrame();
    frame.setSize(400, 300);              // 窗体大小
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);    // 相对屏幕居中
    frame.setTitle("Chatoy 登录界面");     // 窗体名字

    int windowWidth = frame.getWidth();
    int windowHeight = frame.getHeight();
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);

    frame.setLayout(null);
    // 图像——创建 JLabel 对象，使用 ImageIcon 作为输入初始化 JLabel
    // 文本输入——文字 JLabel、账号 JTextField、密码 JPasswordField


    ((JPanel)frame.getContentPane()).setOpaque(false);
    ImageIcon img = new ImageIcon("app/src/main/resources/img/background.png"); //添加图片
    JLabel background = new  JLabel(img);
    background.setPreferredSize(new Dimension(400,300));
    background.setBounds(0,0,400,300);
    frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
    background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

    // 除了 JFrame 设置大小为 setSize(int x, int y)，其他组件都用 setPreferredSize(Dimension d)
    JLabel jl1 = new JLabel("账号：");
    jl1.setBounds(15, 10, 65, 30);

    JTextField jt1 = new JTextField();
    jt1.setPreferredSize(new Dimension(310, 30));
    jt1.setBounds(60, 10, 310, 30);

    JLabel jl2 = new JLabel("密码：");
    jl2.setBounds(15, 50, 65, 30);

    JPasswordField jt2 = new JPasswordField();
    jt2.setPreferredSize(new Dimension(300, 30));
    jt2.setBounds(60, 50, 310, 30);

    frame.add(jl1);
    frame.add(jt1);
    frame.add(jl2);
    frame.add(jt2);

    // JTextField jt3 = new JTextField(4); // 设置输入框大小另一种方式——4 个输入字符
    // 复选框——JCheckBox
    JCheckBox jcb = new JCheckBox("记住密码");
    frame.add(jcb);
    jcb.setBounds(290, 90, 100, 40);

    JCheckBox jcb1 = new JCheckBox("自动登录");
    frame.add(jcb1);
    jcb1.setBounds(210, 90, 100, 40);

    jcb.addActionListener(e -> {
      JOptionPane.showMessageDialog(frame,"您确定要记住密码吗？");
    });

    jcb1.addActionListener(e -> JOptionPane.showMessageDialog(frame,"下次登录将自动登录！"));
    // 按钮——JButton
    JButton zhuceButton = new JButton("注册");
    frame.add(zhuceButton);
    zhuceButton.setBounds(70, 150, 90, 40);

    JButton dengluButton = new JButton("登录");
    frame.add(dengluButton);
    dengluButton.setBounds(220, 150, 90, 40);

    dengluButton.addActionListener(e -> {
      String zhanghao = jt1.getText();
      if(zhanghao == null || zhanghao.trim().equals("")){
        JOptionPane.showMessageDialog(null,"账号不能为空！");
      }
    });

    // 窗体可见，写在 add 组件之后
    frame.setVisible(true);
    // (a) 构造方法初始化文本框的对象
    ButtonListener bl = new ButtonListener(jt1, jt2);

    // (b) set 方法设置监听器类中的文本框类的对象
    // ButtonListener bl = new ButtonListener();
    // bl.setText(jt1, jt2);

    // 4. 绑定
    dengluButton.addActionListener(bl);

/*    ChatInterface chatInterface = new ChatInterface();
    String tokenString = "abc";
    chatInterface.run(tokenString);*/
  }

  // 判断账号密码是否正确——正确显示“登陆成功”，不正确显示“登录失败”
  public static class ButtonListener implements ActionListener {
    private JTextField jtf1;
    private JTextField jtf2;

    // 重写接口中的所有方法：actionPerformed 为 ActionListener 中的方法
    // 当该事件发生，这个方法被自动调用
    public void actionPerformed(ActionEvent e) {
      System.out.println("按钮被点击！");
      validate(jtf1, jtf2);
    }

    // 将界面中文本框的值传入事件监听器中
    // (a) 构造方法，参数为文本框内容
    public ButtonListener(JTextField jtf1, JTextField jtf2) {
      this.jtf1 = jtf1;
      this.jtf2 = jtf2;
    }

    // (b) set 方法
    public void setText(JTextField jtf1, JTextField jtf2) {
      this.jtf1 = jtf1;
      this.jtf2 = jtf2;
    }

    // 判断方法——判断登陆成功 or 失败，显示弹框信息后，初始化输入框文本为空 “”
    public void validate(JTextField jtf1, JTextField jtf2) {
      if (jtf1.getText().equals("root") && jtf2.getText().equals("root")) {
        System.out.println("登陆成功！");
        JOptionPane.showMessageDialog(null, "登录成功！");
      } else {
        System.out.println("登录失败！");
        JOptionPane.showMessageDialog(null, "登录失败！");
      }
      jtf1.setText("");
      jtf2.setText("");
    }




  }


  public static void main(String[] args) {
    ShowUI tf = new ShowUI();
    tf.UI();
  }
}
