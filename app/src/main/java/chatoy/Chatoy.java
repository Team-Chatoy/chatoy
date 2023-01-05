package chatoy;

import java.io.IOException;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultTreeCellRenderer;

import utils.PathUtils;
import utils.ScreenUtils;
import component.allframe.BackgroundPanel;
import component.allframe.DemoScrollBarUI;
import component.mainframeleft.LeftBox;
import democlass.database.Room;

public class Chatoy {
  JFrame theFrame = new JFrame("chatoy");

  final int WIDTH = 820;
  final int HEIGHT = 560;

  int dividerLocation = 200;
  Font font;
  Font unchosenFont = new Font("Cabin Sketch", Font.ITALIC, 70);

  Vector<Room> roomVector = new Vector<>();
  JTextArea roomDescription = new JTextArea();

  // 分割面板
  JSplitPane jSplitPane = new JSplitPane();
  // 左侧区域
  JPanel leftBackPanel;
  JScrollPane leftScrollPane;
  JPanel leftPanel;

  Border bevelBorder =
    BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black, Color.white, Color.pink);
  Border etchedBorder =
    BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.white, Color.pink);

  public void init() throws IOException {
    // 全局微软雅黑字体
    font = new Font("微软雅黑", Font.PLAIN, 12);
    InitGlobalFont(font);

    // 给窗口设置属性
    theFrame.setBounds(
      (ScreenUtils.getScreenWidth() - WIDTH) / 2,
      (ScreenUtils.getScreenHeight() - HEIGHT) / 2,
      WIDTH,
      HEIGHT
    );

    ImageIcon logoIcon = new ImageIcon(this.getClass().getResource("/img/logo.png"));
    theFrame.setIconImage(logoIcon.getImage());

    // 设置分割面板
    // 支持连续布局
    jSplitPane = new JSplitPane();
    jSplitPane.setContinuousLayout(true);
    jSplitPane.setDividerLocation(dividerLocation);
    jSplitPane.setDividerSize(1);
    jSplitPane.setBackground(Color.black);

    // 设置左侧内容
    Border etchedBorder = BorderFactory.createEtchedBorder(BevelBorder.RAISED, Color.white, Color.pink);

    // 左侧滚动条
    leftPanel = new JPanel();
    leftPanel.setBackground(Color.pink);
    leftScrollPane = new JScrollPane(
      leftPanel,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
    );
    leftScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());

    // 组装左侧区域
    leftPanel.add(new LeftBox());
    jSplitPane.setLeftComponent(new LeftBox());

    // 设置右边内容
    // 消息输入区
    Box bottomBox = Box.createHorizontalBox();
    JButton sendButton = new JButton("发送");

    sendButton.setBackground(Color.pink);
    JTextField textField = new JTextField();
    textField.setBorder(etchedBorder);
    textField.setBackground(new Color(30, 30, 30));
    textField.setForeground(Color.gray);

    bottomBox.add(textField);
    bottomBox.add(sendButton);
    ImageIcon LoginBackgroundIcon = new ImageIcon(this.getClass().getResource("/img/LoginBackground.png"));
    BackgroundPanel backgroundPanel = new BackgroundPanel(LoginBackgroundIcon.getImage());
    backgroundPanel.setLayout(new BorderLayout());
    backgroundPanel.add(bottomBox, BorderLayout.SOUTH);

    // 设置默认背景
    JPanel unchosenPanel = new JPanel();
    unchosenPanel.setLayout(new BorderLayout());
    unchosenPanel.setBackground(new Color(38, 45, 49));

    // 大 title
    JLabel unchosenLabel = new JLabel("Hi, Chatoy!", SwingConstants.CENTER);
    unchosenLabel.setForeground(Color.pink);
    unchosenLabel.setFont(unchosenFont);

    unchosenPanel.add(unchosenLabel, BorderLayout.CENTER);

    jSplitPane.setRightComponent(unchosenPanel);

    theFrame.add(jSplitPane);

    // setVisible
    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    theFrame.setVisible(true);
  }

  public static void main(String[] args) throws IOException {
    Chatoy chatoy = new Chatoy();
    chatoy.init();
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

  // 自定义节点绘制器
  private class MyRenderer extends DefaultTreeCellRenderer {
    private ImageIcon root0Icon = null;
    private ImageIcon root1Icon = null;
    private ImageIcon root2Icon = null;
    private ImageIcon root3Icon = null;
    private ImageIcon root4Icon = null;

    public MyRenderer() {
      root0Icon = new ImageIcon(PathUtils.getRealPath("logo.png"));
      root1Icon = new ImageIcon(PathUtils.getRealPath("logo.png"));
      root2Icon = new ImageIcon(PathUtils.getRealPath("logo.png"));
      root3Icon = new ImageIcon(PathUtils.getRealPath("logo.png"));
      root4Icon = new ImageIcon(PathUtils.getRealPath("logo.png"));
    }

    // 当绘制树的每个节点时，都会调用这个方法
    @Override
    public Component getTreeCellRendererComponent(
      JTree tree,
      Object value,
      boolean sel,
      boolean expanded,
      boolean leaf,
      int row,
      boolean hasFocus
    ) {
      super.getTreeCellRendererComponent(
        tree,
        value,
        sel,
        expanded,
        leaf,
        row,
        hasFocus
      );

      ImageIcon image = null;
      switch (row) {
        case 0: {
          image = root0Icon;
          break;
        }
        case 1: {
          image = root1Icon;
          break;
        }
        case 2: {
          image = root2Icon;
          break;
        }
        case 3: {
          image = root3Icon;
          break;
        }
        case 4: {
          image = root4Icon;
          break;
        }
      }

      this.setIcon(image);
      return this;
    }
  }
}
