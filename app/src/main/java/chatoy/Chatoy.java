package chatoy;

import component.allframe.BackgroundPanel;
import component.allframe.DemoScrollBarUI;
import component.mainframeleft.LeftBox;
import democlass.database.Room;
import utils.PathUtils;
import utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Chatoy {
    JFrame theFrame = new JFrame("chatoy");

    final int WIDTH = 820;
    final int HEIGHT = 560;
    int dividerLocation = 200;
    Font font;
    Font unchosenFont = new Font("Cabin Sketch",Font.ITALIC,70);

    Vector<Room> roomVector = new Vector<>();
    JTextArea roomDescription = new JTextArea();

    // 分割面板
    JSplitPane jSplitPane = new JSplitPane();
    // 左侧区域
    JPanel leftBackPanel;
    JScrollPane leftScrollPane;
    JPanel leftPanel;

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
        theFrame.setIconImage(new ImageIcon("img/logo.png").getImage());

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

        // 设置左侧内容
        Border etchedBorder = BorderFactory.createEtchedBorder(BevelBorder.RAISED,
                Color.white, Color.pink);
        // 左侧滚动条
        leftPanel = new JPanel();
        // leftPanel.setBackground(new Color(19, 28, 33));
        leftPanel.setBackground(Color.pink);
        leftScrollPane = new JScrollPane(leftPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        leftScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
        // leftScrollPane.getVerticalScrollBar().setBackground(Color.blue);
        // leftScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
        //     @Override
        //     protected void configureScrollBarColors() {
        //         this.thumbColor = Color.lightGray;
        //     }
        // });



        // 组装左侧区域
        leftPanel.add(new LeftBox());
        jSplitPane.setLeftComponent(new LeftBox());
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
        // 消息输入区
        Box bottomBox = Box.createHorizontalBox();
        JButton sendButton = new JButton("发送");
        // sendButton.setForeground(Color.white);
        sendButton.setBackground(Color.pink);
        JTextField textField = new JTextField("请输入");
        textField.setBorder(etchedBorder);
        // textField.setBackground(new Color(38,45,49));
        textField.setBackground(new Color(30, 30, 30));
        textField.setForeground(Color.gray);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (textField.getText().equals("")) {
                    textField.setText("请输入");
                    textField.setForeground(Color.gray);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (textField.getText().equals("请输入")) {
                    textField.setText("");
                    textField.setForeground(Color.white);
                }
            }
        });

        bottomBox.add(textField);
        bottomBox.add(sendButton);

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("img/LoginBackground.png").getImage());
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(bottomBox, BorderLayout.SOUTH);

        // 设置默认背景
        JPanel unchosenPanel = new JPanel();
        unchosenPanel.setLayout(new BorderLayout());
        unchosenPanel.setBackground(new Color(38, 45 ,49));
        // 大title
        JLabel unchosenLabel = new JLabel("Hi, Chatoy !", SwingConstants.CENTER);
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



        // chatoy.addRoom(new Room("ccc",333, "this 1"));
        // // 添加聊天室
        // chatoy.addRoom(new Room("test1111111111111111111",333, "this 1"));
        // chatoy.addRoom(new Room("tes333333333333333333333333333333333333333333333333333333333333333333333333t",12, "this 2"));
        // chatoy.addRoom(new Room("testromm1",333, "this 1"));
        // chatoy.addRoom(new Room("testromm1",333, "this 1"));
        // chatoy.addRoom(new Room("testromm1",333, "this 1"));
        // chatoy.addRoom(new Room("testromm1",333, "this 1"));
        // chatoy.addRoom(new Room("testromm1",333, "this 1"));
        // chatoy.addRoom(new Room("testromm1",333, "this 1"));
    }

    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
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



}
