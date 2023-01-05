package chatoy;

import java.io.IOException;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.FontUIResource;

import jni.Room;
import utils.PathUtils;
import utils.ScreenUtils;
import component.allframe.BackgroundPanel;
import component.allframe.DemoScrollBarUI;
import component.mainframeleft.CreateRoomPanel;
import component.mainframeleft.JoinRoomPanel;

public class Chatoy {
  static jni.Utils jni = new jni.Utils();
  JFrame theFrame = new JFrame("Chatoy");

  static final int WIDTH = 820;
  final int HEIGHT = 560; // review: ???

  static int dividerLocation = 200;
  Font font;
  Font unchosenFont = new Font("Cabin Sketch", Font.ITALIC, 70);
  static JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
  static JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

  public List<Room> rooms;

  JTextArea roomDescription = new JTextArea();

  Box roomBox = Box.createVerticalBox();
  static Box roomsBox;
  static JPanel roomsPanel;
  Border bevelBorder =
    BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.black, Color.white, Color.pink);
  Border etchedBorder =
    BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.white, Color.pink);

  private String server;
  private String token;
  public Chatoy(String server, String token) {
    this.server = server;
    this.token = token;
    this.rooms = jni.getMyRooms(server, token);
  }

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
    theFrame.setResizable(false);
    theFrame.setIconImage(
      new ImageIcon(
        this.getClass()
          .getResource(PathUtils.getRealPath("logo.png"))
      )
      .getImage()
    );

    // 设置分割面板
    // 支持连续布局
    jSplitPane = new JSplitPane();
    jSplitPane.setContinuousLayout(true);
    jSplitPane.setDividerLocation(dividerLocation);
    jSplitPane.setDividerSize(1);
    jSplitPane.setBackground(Color.green);

    // 设置左侧内容

    // 设置左侧整个 box
    Box leftBox = Box.createVerticalBox();

    // 查找和添加键区域
    JPanel roomSearchAddPanel = new JPanel();
    roomSearchAddPanel.setLayout(new BorderLayout());
    roomSearchAddPanel.setBackground(Color.green);
    roomSearchAddPanel.setMaximumSize(new Dimension(WIDTH, 25));
    // 设置查找和添加键
    JTextField roomSearchTextField = new JTextField("搜索");
    roomSearchTextField.setForeground(Color.gray);
    JMenu roomFunctionMenu = new JMenu("   +"); // review: that's bad
    roomFunctionMenu.setForeground(Color.pink);
    roomFunctionMenu.setBackground(new Color(38, 45, 49));
    roomFunctionMenu.setHorizontalAlignment(SwingConstants.CENTER);
    roomFunctionMenu.setPreferredSize(new Dimension(40, 30));
    JMenuItem joinRoomItem, createRoomItem;
    joinRoomItem = new JMenuItem("加入房间");
    createRoomItem = new JMenuItem("创建房间");
    joinRoomItem.setForeground(Color.pink);
    createRoomItem.setForeground(Color.pink);
    joinRoomItem.setBackground(new Color(38, 45, 49));
    createRoomItem.setBackground(new Color(38, 45, 49));
    roomFunctionMenu.add(joinRoomItem);
    roomFunctionMenu.add(createRoomItem);
    JMenuBar functionMenuBar = new JMenuBar();
    functionMenuBar.add(roomFunctionMenu);
    functionMenuBar.setBackground(new Color(38, 45, 49));
    // 组装查找和添加键区域
    roomSearchAddPanel.add(roomSearchTextField, BorderLayout.CENTER);
    roomSearchAddPanel.add(functionMenuBar, BorderLayout.EAST);

    // 设置 rooms 区域
    roomsPanel = new JPanel();
    roomsPanel.setBackground(new Color(19, 28, 33));
    roomsBox = Box.createVerticalBox();
    // 创建所有的图形化 room
    for (int i = 0; i < rooms.size(); ++ i) {
      // roomPanel
      JPanel roomPanel = createRoomPanel(rooms.get(i));
      roomsBox.add(roomPanel);
      roomsBox.add(Box.createVerticalStrut(10));
    }

    // 设置滚动条
    roomsPanel.add(roomsBox);
    JScrollPane roomsScrollPane = new JScrollPane(
      roomsPanel,
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    roomsScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
    roomsScrollPane.setBorder(BorderFactory.createEmptyBorder());

    roomsScrollPane.getVerticalScrollBar()
      .addAdjustmentListener(new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
          e.getAdjustable()
            .setValue(e.getAdjustable().getMaximum());
        }
      });

    // 组装左侧区域
    leftBox.add(roomSearchAddPanel);
    leftBox.add(roomsScrollPane);
    jSplitPane.setLeftComponent(leftBox);

    var that = this;
    // 组装后开始设置左侧区域的 Actionlistener 等
    joinRoomItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new JoinRoomPanel(server, token, that).init();
      }
    });

    // 创建房间响应
    createRoomItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // 弹出一个对话框，让用户输入房间的信息
        new CreateRoomPanel(server, token, that).init();
      }
    });

    // 设置右侧区域

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

    theFrame.setContentPane(jSplitPane);
    // setVisible
    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    theFrame.setVisible(true);
  }

  public static Box loadRoomsBox() {
    return roomsBox;
  }

  public JPanel loadRoomsPanel() {
    return roomsPanel;
  }

  public static void InitGlobalFont(Font fnt) {
    FontUIResource fontRes = new FontUIResource(fnt);
    for (var keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof FontUIResource) {
        UIManager.put(key, fontRes);
      }
    }
  }

  public static JPanel createRoomPanel(Room room) {
    JPanel roomPanel = new JPanel();
    roomPanel.setBackground(new Color(38, 45, 49));
    roomPanel.setBorder(
      BorderFactory.createEtchedBorder(
        EtchedBorder.RAISED,
        Color.gray,
        Color.white
      )
    );
    Box roomBox = Box.createVerticalBox();
    // 创建 name 和 number 区域
    JLabel nameLabel = new JLabel(room.getName());
    // 自适应 name 大小来设置 JTextArea 高度
    int nameTextAreaHeight = 0;
    int nameTextAreaWidth = 110;
    double nameDivide = nameLabel.getPreferredSize().getWidth() / nameTextAreaWidth;
    for (int i = 0; i < nameDivide; ++ i) {
      nameTextAreaHeight += (int)nameLabel.getPreferredSize().getHeight();
    }
    JTextArea nameTextArea = new JTextArea();
    nameTextArea.setForeground(Color.pink);
    nameTextArea.setPreferredSize(new Dimension(nameTextAreaWidth, nameTextAreaHeight));
    nameTextArea.setLineWrap(true);
    nameTextArea.setEditable(false);
    nameTextArea.setOpaque(false);
    nameTextArea.setText(room.getName());
    JLabel numberLabel = new JLabel("#" + room.getId());
    // 组装 name 和 number
    JPanel numberPanel = new JPanel();
    // numberLabel.setFont(roomFont);
    numberLabel.setOpaque(true);
    numberLabel.setBackground(Color.LIGHT_GRAY);
    numberPanel.setLayout(new GridBagLayout());
    numberPanel.setOpaque(false);
    numberPanel.add(numberLabel);
    JPanel nameNumberPanel = new JPanel();
    nameNumberPanel.setLayout(new BorderLayout());
    nameNumberPanel.setOpaque(false);
    nameNumberPanel.add(nameTextArea, BorderLayout.WEST);
    nameNumberPanel.add(numberPanel, BorderLayout.EAST);
    nameNumberPanel.setPreferredSize(new Dimension(130, nameTextAreaHeight));
    // 创建 button 区域
    JButton enterButton = new JButton("Enter");
    enterButton.setForeground(Color.pink);
    enterButton.setBorder(BorderFactory.createLineBorder(Color.gray));
    enterButton.setBackground(new Color(38, 45, 49));
    enterButton.setPreferredSize(new Dimension(150, 20));
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(enterButton);
    buttonPanel.setOpaque(false);
    // 组装 roomBox
    roomBox.add(Box.createVerticalStrut(4));
    roomBox.add(nameNumberPanel);
    roomBox.add(Box.createVerticalStrut(5));
    roomBox.add(buttonPanel);
    roomPanel.add(roomBox);

    enterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jSplitPane.setRightComponent(showRight(room));
        jSplitPane.setDividerLocation(dividerLocation);
      }
    });

    return roomPanel;
  }

  public static BackgroundPanel showRight(Room room) {
    rightSplitPane.setOpaque(false);
    rightSplitPane.setDividerLocation(490);
    rightSplitPane.setDividerSize(1);

    // 标题区
    Font titelFront = new Font("Cabin Sketch", Font.PLAIN, 40);
    JLabel titelLabel = new JLabel();
    titelLabel.setFont(titelFront);
    titelLabel.setForeground(Color.white);
    titelLabel.setText(room.getName());
    Box titelBox = Box.createHorizontalBox();
    titelBox.add(Box.createHorizontalStrut(10));
    titelBox.add(titelLabel);
    JPanel titelPanel = new JPanel();
    titelPanel.setLayout(new BorderLayout());
    titelPanel.setBackground(new Color(38, 45, 49));
    titelPanel.add(titelBox, BorderLayout.WEST);

    // 输入框区域
    // textArea
    JTextArea textArea = new JTextArea();
    textArea.setOpaque(false);
    textArea.setRows(1);
    textArea.setForeground(Color.white);
    textArea.setLineWrap(true);
    textArea.setText(room.getDescription());
    JScrollPane textScrollPane = new JScrollPane(
      textArea,
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    textScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
    textScrollPane.setBorder(BorderFactory.createEmptyBorder());
    textScrollPane.getViewport().setOpaque(false);
    textScrollPane.setOpaque(false);
    // button
    JButton sendButton = new JButton("发送");
    JPanel textButtonPanel = new JPanel();
    textButtonPanel.setLayout(new BorderLayout());
    textButtonPanel.setBackground(new Color(25, 25, 25));
    JPanel littelPanel = new JPanel();
    littelPanel.setOpaque(false);
    littelPanel.setPreferredSize(new Dimension(4, 10));
    textButtonPanel.add(littelPanel, BorderLayout.WEST);
    textButtonPanel.add(textScrollPane, BorderLayout.CENTER);
    textButtonPanel.add(sendButton, BorderLayout.EAST);

    // 发送消息区域
    Box messagesBox = Box.createVerticalBox();
    JPanel messagesPanel = new JPanel();
    messagesPanel.setOpaque(false);
    messagesPanel.add(messagesBox);
    JScrollPane messagesScrollPane = new JScrollPane(
      messagesPanel,
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    messagesScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
    messagesScrollPane.setBackground(Color.black);
    messagesScrollPane.setOpaque(false);
    messagesScrollPane.getViewport().setOpaque(false);

    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // 设置 message 区域组件
        JPanel messageLongPanel = new JPanel();
        JTextArea messageTextArea = new JTextArea();

        // 设置文本展示区
        String messageContent = textArea.getText();
        JLabel messageLabel = new JLabel(messageContent); // 一行的时候用
        messageLabel.setOpaque(true);
        messageLabel.setBackground(Color.pink);
        double messageTextAreaWidth = 300;
        double messageDivide = messageLabel.getPreferredSize().getWidth() / messageTextAreaWidth;
        messageTextArea.setText(messageContent);
        messageTextArea.setBackground(Color.pink);
        messageTextArea.setLineWrap(true);
        messageTextArea.setEditable(false);
        messageLongPanel.setOpaque(false);
        messageLongPanel.setLayout(new BorderLayout());
        messageLongPanel.setPreferredSize(
          new Dimension(
            WIDTH - dividerLocation,
            (int)(((int)messageDivide + 1) * messageLabel.getPreferredSize().getHeight()) // review: don't do this :(
          )
        );

        // 组装一条 messagePanel
        if (messageDivide <= 1) {
          messageLongPanel.add(messageLabel, BorderLayout.WEST);
        } else {
          messageLongPanel.add(messageTextArea, BorderLayout.WEST);
        }

        // 组装 messagesPanel
        messagesBox.add(messageLongPanel);
        messagesBox.add(Box.createVerticalStrut(10));
        messagesPanel.updateUI();
      }
    });

    // 组装

    // 设置分割面板上侧
    JPanel upperPanel = new JPanel();
    upperPanel.setOpaque(false);
    upperPanel.setLayout(new BorderLayout());
    upperPanel.add(titelPanel, BorderLayout.NORTH);
    upperPanel.add(messagesScrollPane, BorderLayout.CENTER);

    // 组装整体
    rightSplitPane.setTopComponent(upperPanel);
    rightSplitPane.setBottomComponent(textButtonPanel);

    // 设置背景
    var fucking_java = new App(); // you should never do this but we have no time :)
    BackgroundPanel backgroundPanel =
      new BackgroundPanel(
        new ImageIcon(
          fucking_java.getClass()
            .getResource(PathUtils.getRealPath("LoginBackground.png"))
        )
        .getImage()
      );

    backgroundPanel.setLayout(new BorderLayout());
    backgroundPanel.add(rightSplitPane, BorderLayout.CENTER);

    return backgroundPanel;
  }
}
