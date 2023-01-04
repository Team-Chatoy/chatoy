package chatoy;

import component.allframe.BackgroundPanel;
import component.allframe.DemoScrollBarUI;
import democlass.database.Room;
import utils.PathUtils;
import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Chatoy {
    JFrame theFrame = new JFrame("chatoy");

    final int WIDTH = 820;
    final int HEIGHT = 560;
    int dividerLocation = 200;
    Font font;
    Font unchosenFont = new Font("Cabin Sketch",Font.ITALIC,70);
    JSplitPane jSplitPane = new JSplitPane();
    JSplitPane rightSplitPane = new JSplitPane();

    Room[] rooms;

    JTextArea roomDescription = new JTextArea();

    Box roomBox = Box.createVerticalBox();

    Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black,
             Color.black, Color.white, Color.pink);
    Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.white, Color.pink);
    public void init() throws IOException {

        // try {
        //     String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        //
        //     UIManager.setLookAndFeel(lookAndFeel);
        //     // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // 全局微软雅黑字体
        font = new Font("微软雅黑",Font.PLAIN,12);
        InitGlobalFont(font);

        // 给窗口设置属性
        theFrame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGHT)/2, WIDTH, HEIGHT);
        // theFrame.setResizable(false);
        theFrame.setIconImage(new ImageIcon(this.getClass().getResource(PathUtils.getRealPath("logo.png"))).getImage());

        // 设置菜单栏
        // JMenuBar jMenuBar = new JMenuBar();
        // jMenuBar.setBackground(new Color(5, 5, 5));
        //
        // JMenu jMenu = new JMenu("设置");
        // jMenu.setForeground(Color.white);
        // JMenuItem changeAccount = new JMenuItem("切换账号");
        // JMenuItem signOut = new JMenuItem("退出程序");
        // signOut.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         try {
        //             new Login().init();
        //             theFrame.dispose();
        //         } catch (IOException ex) {
        //             throw new RuntimeException(ex);
        //         }
        //     }
        // });
        //
        // signOut.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         System.exit(0);
        //     }
        // });
        // jMenu.add(changeAccount);
        // jMenu.add(signOut);
        // jMenuBar.add(jMenu);
        //
        // theFrame.setJMenuBar(jMenuBar);

        // 设置分割面板
        // 支持连续布局
        jSplitPane = new JSplitPane();
        jSplitPane.setContinuousLayout(true);
        jSplitPane.setDividerLocation(dividerLocation);
        jSplitPane.setDividerSize(1);
        jSplitPane.setBackground(Color.black);


        Border etchedBorder = BorderFactory.createEtchedBorder(BevelBorder.RAISED,
                Color.white, Color.pink);
        // 左侧滚动条风格
        // leftScrollPane.getVerticalScrollBar().setBackground(Color.blue);
        // leftScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
        //     @Override
        //     protected void configureScrollBarColors() {
        //         this.thumbColor = Color.lightGray;
        //     }
        // });

        // 设置左侧内容

        // 设置左侧整个box
        Box leftBox = Box.createVerticalBox();

        // 查找和添加键区域
        JPanel roomSearchAddPanel = new JPanel();
        roomSearchAddPanel.setLayout(new BorderLayout());
        roomSearchAddPanel.setBackground(Color.green);
        roomSearchAddPanel.setMaximumSize(new Dimension(WIDTH, 25));
        // 设置查找和添加键
        JTextField roomSearchTextField = new JTextField("搜索");
        roomSearchTextField.setForeground(Color.gray);
        JButton roomAddButton = new JButton("+");
        roomAddButton.setPreferredSize(new Dimension(40, 30));
        roomAddButton.setBackground(Color.pink);
        // 组装查找和添加键区域
        roomSearchAddPanel.add(roomSearchTextField, BorderLayout.CENTER);
        roomSearchAddPanel.add(roomAddButton, BorderLayout.EAST);

        // 设置rooms区域
        JPanel roomsPanel = new JPanel();
        roomsPanel.setBackground(new Color(19, 28, 33));
        Box roomsBox = Box.createVerticalBox();
        // 创建所有的图形化room
        rooms = loadRooms();
        for (int i=0; i < rooms.length; i++) {
            // roomPanel
            JPanel roomPanel = createRoomPanel(rooms[i]);
            roomsBox.add(roomPanel);
            roomsBox.add(Box.createVerticalStrut(10));
        }

        // 设置滚动条
        roomsPanel.add(roomsBox);
        JScrollPane roomsScrollPane = new JScrollPane(roomsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        roomsScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
        roomsScrollPane.setBorder(BorderFactory.createEmptyBorder());
        roomsScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });

        // 组装左侧区域
        leftBox.add(roomSearchAddPanel);
        leftBox.add(roomsScrollPane);
        jSplitPane.setLeftComponent(leftBox);

        // 组装后开始设置左侧区域的Actionlistener等
        roomAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomsBox.add(createRoomPanel(new Room(111, "ADD 1", "THIS IS A ADDING TEST", new Date())));
                roomsBox.add(Box.createVerticalStrut(10));
                roomsPanel.updateUI();
            }
        });


/*
        DefaultMutableTreeNode root0 = new DefaultMutableTreeNode("0");
        DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("1");
        DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("2");
        DefaultMutableTreeNode root3 = new DefaultMutableTreeNode("3");
        DefaultMutableTreeNode root4 = new DefaultMutableTreeNode("4");

        root0.add(root1);
        root0.add(root2);
        root0.add(root3);
        root0.add(root4);

        Color color = new Color(5,5,5);
        JTree tree = new JTree(root0);
        // MyRenderer myRenderer = new MyRenderer();
        // myRenderer.setBackgroundNonSelectionColor(color);
        // myRenderer.setBackgroundSelectionColor(new Color(140,140,140));
        // tree.setCellRenderer(myRenderer);

        tree.setBackground(color);
        // 设置当前tree默认选中root2
        tree.setSelectionRow(2);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            // 当条目选中变化后，这个方法会执行
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // 得到当前选中的节点对象
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();

                if (root0.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new JLabel("这里进行聊天0..."));
                    jSplitPane.setDividerLocation(220);
                } else if (root1.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new JLabel("这里进行聊天1..."));
                    jSplitPane.setDividerLocation(220);
                } else if (root2.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new JLabel("这里进行聊天2..."));
                    jSplitPane.setDividerLocation(220);
                } else if (root3.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new JLabel("这里进行聊天3..."));
                    jSplitPane.setDividerLocation(220);
                } else if (root4.equals(lastPathComponent)) {
                    jSplitPane.setRightComponent(new JLabel("这里进行聊天4..."));
                    jSplitPane.setDividerLocation(220);
                }

            }
        });
*/
        // 设置右边内容




        // 设置默认背景
        JPanel unchosenPanel = new JPanel();
        unchosenPanel.setLayout(new BorderLayout());
        unchosenPanel.setBackground(new Color(38, 45 ,49));
        // 大title
        JLabel unchosenLabel = new JLabel("Hi, Chatoy !", SwingConstants.CENTER);
        unchosenLabel.setForeground(Color.pink);
        unchosenLabel.setFont(unchosenFont);

        unchosenPanel.add(unchosenLabel, BorderLayout.CENTER);

        //
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon(this.getClass().getResource(PathUtils.getRealPath("LoginBackground.png"))).getImage());
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(rightSplitPane, BorderLayout.CENTER);
        rightSplitPane.setOpaque(false);

        theFrame.setContentPane(jSplitPane);
        // setVisible
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        // 测试用的room (删除时同时删掉第21行)
        Chatoy chatoy = new Chatoy();

        chatoy.init();
    }

    // 向服务器请求room
    public Room[] loadRooms() {
        Room[] rooms = {new Room(1,"ROOM 1", "THIS IS A TEST", new Date())
                , new Room(2,"ROOM 2", "THIS IS A TEST", new Date())
                , new Room(3,"ROOM 3", "THIS IS A TEST", new Date())
                , new Room(100,"ROOM 100 ------------------------------------------------", "THIS IS A TEST", new Date())};
        return rooms;
    }

    public static void InitGlobalFont(Font fnt)

    {
        FontUIResource fontRes = new FontUIResource(fnt);
        for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)

        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
                UIManager.put(key, fontRes);
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
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            ImageIcon image = null;
            switch (row) {
                case 0:
                    image = root0Icon;
                case 1:
                    image = root1Icon;
                case 2:
                    image = root2Icon;
                case 3:
                    image = root3Icon;
                case 4:
                    image = root4Icon;
            }

            this.setIcon(image);
            return this;
        }
    }

    public JPanel createRoomPanel(Room room) {
        JPanel roomPanel = new JPanel();
        roomPanel.setBackground(Color.pink);
        roomPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.white));
        Box roomBox = Box.createVerticalBox();

        // 创建name和number区域
        JLabel nameLabel = new JLabel(room.getName());
        // 自适应name大小来设置JTextArea高度
        int nameTextAreaHeight = 0;
        int nameTextAreaWidth = 110;
        double nameDivide = nameLabel.getPreferredSize().getWidth() / nameTextAreaWidth;
        for (int i = 0; i < nameDivide; i++) {
            nameTextAreaHeight += (int) nameLabel.getPreferredSize().getHeight();
        }
        JTextArea nameTextArea = new JTextArea();
        // nameTextArea.setFont(roomFont);
        nameTextArea.setPreferredSize(new Dimension(nameTextAreaWidth, nameTextAreaHeight));
        nameTextArea.setLineWrap(true);
        nameTextArea.setEditable(false);

        nameTextArea.setOpaque(false);
        nameTextArea.setText(room.getName());
        JLabel numberLabel = new JLabel("#" + room.getId());
        // 组装name和number
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

        // 创建button区域
        JButton enterButton = new JButton("Enter");
        // enterButton.setFont(roomFont);
        enterButton.setBorder(BorderFactory.createLineBorder(Color.gray));
        enterButton.setBackground(Color.pink);
        enterButton.setPreferredSize(new Dimension(150, 20));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(enterButton);
        buttonPanel.setOpaque(false);

        // 组装roomBox
        roomBox.add(Box.createVerticalStrut(4));
        roomBox.add(nameNumberPanel);
        roomBox.add(Box.createVerticalStrut(5));
        roomBox.add(buttonPanel);

        roomPanel.add(roomBox);

        // // roomBox添加到roomPanel, 并添加到leftBox
        // roomPanel.add(roomBox);
        // // roomPanel.setPreferredSize(new Dimension(160, 30+nameTextAreaHeight+(int)buttonPanel.getPreferredSize().getHeight()));
        // roomsBox.add(roomPanel);
        // roomsBox.add(Box.createVerticalStrut(10));


        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jSplitPane.setRightComponent(showRight(room));
                jSplitPane.setDividerLocation(dividerLocation);
            }
        });

        return roomPanel;
    }

    public JSplitPane showRight(Room room) {
        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setDividerLocation(400);
        rightSplitPane.setDividerSize(1);

        // 标题区
        Font titelFront = new Font("Cabin Sketch",Font.PLAIN,40);
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
        JTextArea textArea = new JTextArea();
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setRows(5);
        textArea.setForeground(Color.white);
        textArea.setLineWrap(true);
        textArea.setText(room.getDescription());
        // Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.white, Color.pink);
        // textArea.setBorder(etchedBorder);
        JScrollPane textScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
        textScrollPane.setBorder(BorderFactory.createEmptyBorder());
        JButton sendButton = new JButton("发送");
        JPanel sendButtonPanel = new JPanel();
        sendButtonPanel.setBackground(new Color(25, 25, 25));
        sendButtonPanel.setLayout(new BorderLayout());
        sendButtonPanel.add(sendButton, BorderLayout.EAST);
        JPanel textButtonPanel = new JPanel();
        textButtonPanel.setLayout(new BorderLayout());
        textButtonPanel.add(textScrollPane, BorderLayout.CENTER);
        textButtonPanel.add(sendButtonPanel, BorderLayout.SOUTH);


        // 发送消息区域
        JPanel messagesPanel = new JPanel();
        messagesPanel.setOpaque(false);
        messagesPanel.setLayout(new BorderLayout());
        JScrollPane messagesScrollPane = new JScrollPane(messagesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messagesScrollPane.setOpaque(false);
        messagesScrollPane.getViewport().setOpaque(false);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 设置message区域组件
                JPanel messagePanel = new JPanel();
                Box messageContentBox = Box.createHorizontalBox();
                JTextArea messageTextArea = new JTextArea();
                // 获取输入的text内容
                String messageContent = textArea.getText();
                messageTextArea.setText(messageContent);
                // 组装一条messagePanel
                messageContentBox.add(messageTextArea);
                messageContentBox.add(Box.createHorizontalStrut(10));
                messagePanel.setLayout(new BorderLayout());
                messagePanel.add(messageContentBox, BorderLayout.EAST);
                // 组装messagesPanel
                messagesPanel.add(messagePanel, BorderLayout.PAGE_END);
                messagesScrollPane.updateUI();


            }
        });

        // 组装

        // 组装分割线上侧
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon(this.getClass().getResource(PathUtils.getRealPath("LoginBackground.png"))).getImage());
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(titelPanel, BorderLayout.NORTH);
        backgroundPanel.add(messagesScrollPane, BorderLayout.CENTER);

        rightSplitPane.setTopComponent(backgroundPanel);
        rightSplitPane.setBottomComponent(textButtonPanel);

        return rightSplitPane;
    }



// 请求数据
    // public void requestData() {
    //     GetUtils.get("http://...", new SuccessListener()) {
    //         @Override
    //         public void success(String result) {
    //             ResultInfo info = JsonUtils.parseResult(result);
    //             Vector<Vector> vectors = ResultInfoData2Vector.convertResultInfoData2Vector(info);
    //
    //             // 清空roomsData的数据
    //             roomsData.clear();
    //             // 把请求道的数据加到roomsData中
    //             for (Vector vector : vectors) {
    //                 roomsData.add(vector);
    //             }
    //
    //             // 刷新rooms窗口
    //             roomsPanel.
    //         }
    //     }, new FailListener() {
    //         @Override
    //         public void fail(String result) {
    //
    //         }
    //
    //     }
    // }



}
