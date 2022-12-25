package chatoy;

import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
  private JLabel jlabel1;
  private JLabel jlabel2;
  private JLabel jlabel3;
  private JLabel jlabel4;
  private JTextField jtextfield;
  private JPasswordField jpasswordfield;
  private JButton jbutton;
  private JCheckBox jcheckbox;

  public Login() {
    this.setTitle("Chatoy注册界面");
    this.setSize(400,450);
    Container container = this.getContentPane();
    container.setLayout(null);

    // 背景图片
    var bgPicUrl = this.getClass().getResource("/img/background.png");
    ImageIcon bgPic = new ImageIcon(bgPicUrl);
    var bgPanel = new PicturePanel(bgPic.getImage());
    bgPanel.setBounds(0, 0, 400, 450);
    container.add(bgPanel);

    jlabel1 = new JLabel("昵称：");
    jlabel2 = new JLabel("请输入你的昵称");
    jlabel3 = new JLabel("密码：");
    jlabel4 = new JLabel("请输入你的密码");
    container.add(jlabel1);
    container.add(jlabel2);
    container.add(jlabel3);
    container.add(jlabel4);
    jlabel1.setBounds(50, 20, 300, 30);
    jlabel2.setBounds(50, 50, 300, 30);
    jlabel3.setBounds(50, 130, 300, 30);
    jlabel4.setBounds(50, 160, 300, 30);
    jtextfield = new JTextField();
    jpasswordfield = new JPasswordField();
    container.add(jtextfield);
    container.add(jpasswordfield);
    jtextfield.setBounds(50, 90, 300, 30);
    jpasswordfield.setBounds(50, 200, 300, 30);
    jcheckbox = new JCheckBox("我同意所有关于服务的声明");
    jbutton = new JButton("注册");
    container.add(jcheckbox);
    container.add(jbutton);
    jcheckbox.setBounds(65, 240, 270, 30);
    jbutton.setBounds(65, 290, 270, 60);
    this.setVisible(true);

  }

}

