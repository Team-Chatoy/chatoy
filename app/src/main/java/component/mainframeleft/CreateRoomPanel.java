package component.mainframeleft;

import chatoy.Chatoy;
import democlass.database.Room;
import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CreateRoomPanel {
    final int WIDTH =450;
    final int HEIGHT = 170;
    static int roomID = 4;

    Font font1 = new Font("微软雅黑",Font.PLAIN,12);
    Font font2 = new Font("微软雅黑",Font.PLAIN,20);
    // 将文本框显示为下划线
    MatteBorder underlineBorder = new MatteBorder(0,0,1,0,Color.pink);
    Border etchedBorder = BorderFactory.createEtchedBorder(BevelBorder.RAISED,
            Color.white, Color.pink);

    JFrame theFrame = new JFrame();
    public static Room createdRoom;
    public void init() {

        theFrame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-WIDTH)/2, WIDTH, HEIGHT);
        //theFrame.setResizable(false);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0,0,WIDTH,HEIGHT);

        // 垂直布局
        Box createRoomBox = Box.createVerticalBox();
        Box successfullyCraeteBox = Box.createVerticalBox();
        // 水平布局
        Box userTokenInfoBox = Box.createHorizontalBox();
        Box roomNameInfoBox = Box.createHorizontalBox();

        // userTokenLabel
        JLabel userTokenLabel = new JLabel("Token：",SwingConstants.CENTER);
        userTokenLabel.setFont(font1);
        userTokenLabel.setOpaque(false);
        userTokenLabel.setForeground(Color.pink);
        // roomNameLabel
        JLabel roomNameLabel = new JLabel("房间名称:",SwingConstants.CENTER);
        roomNameLabel.setFont(font1);
        roomNameLabel.setOpaque(false);
        roomNameLabel.setForeground(Color.pink);

        // successfullyCreateLabel
        JLabel successfullyCreateLabel = new JLabel("创建房间成功!(●'v'●)",SwingConstants.CENTER);
        successfullyCreateLabel.setFont(font2);
        successfullyCreateLabel.setOpaque(false);
        successfullyCreateLabel.setForeground(Color.pink);
        JPanel successfulLabelPanel = new JPanel();
        successfulLabelPanel.setLayout(new BorderLayout());
        successfulLabelPanel.add(successfullyCreateLabel,BorderLayout.CENTER);

        // roomIDLabel
        JLabel roomIDLabel = new JLabel("群号：" + Integer.toString(roomID),SwingConstants.CENTER);
        roomIDLabel.setFont(font2);
        roomIDLabel.setOpaque(false);
        roomIDLabel.setForeground(Color.pink);
        JPanel roomIDLabelPanel = new JPanel();
        roomIDLabelPanel.setLayout(new BorderLayout());
        roomIDLabelPanel.add(roomIDLabel,BorderLayout.CENTER);

        // userTokenTextField
        JTextField userTokenTextField = new JTextField("请输入您的Token",10);
        userTokenTextField.setFont(font1);
        userTokenTextField.setBorder(underlineBorder);
        userTokenTextField.setBackground(new Color(38,45,49));
        userTokenTextField.setForeground(Color.pink);
        userTokenTextField.setPreferredSize(new Dimension(100, 20));
        // roomNameTextField
        JTextField roomNameTextField = new JTextField("请输入创建房间的名称",10);
        roomNameTextField.setFont(font1);
        roomNameTextField.setBorder(underlineBorder);
        roomNameTextField.setBackground(new Color(38,45,49));
        roomNameTextField.setForeground(Color.pink);
        roomNameTextField.setPreferredSize(new Dimension(100, 20));

        // createRoomButton
        JButton createRoomButton = new JButton(" 创建房间 ");
        createRoomButton.setFont(font1);
        createRoomButton.setBackground(Color.pink);
        createRoomButton.setForeground(new Color(38, 45 ,49));
        createRoomButton.setBorder(BorderFactory.createLineBorder(Color.pink));
        createRoomButton.setPreferredSize(new Dimension(300, 20));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(createRoomButton,BorderLayout.CENTER);


        // 设置水平间距
        userTokenInfoBox.add(Box.createHorizontalStrut(5));
        userTokenInfoBox.add(userTokenLabel);
        userTokenInfoBox.add(Box.createHorizontalStrut(5));
        userTokenInfoBox.add(userTokenTextField);
        userTokenInfoBox.add(Box.createHorizontalStrut(10));
        roomNameInfoBox.add(Box.createHorizontalStrut(5));
        roomNameInfoBox.add(roomNameLabel);
        roomNameInfoBox.add(Box.createHorizontalStrut(5));
        roomNameInfoBox.add(roomNameTextField);
        roomNameInfoBox.add(Box.createHorizontalStrut(10));

        // 设置垂直间距
        createRoomBox.add(Box.createVerticalStrut(18));
        createRoomBox.add(userTokenInfoBox);
        createRoomBox.add(Box.createVerticalStrut(10));
        createRoomBox.add(roomNameInfoBox);
        createRoomBox.add(Box.createVerticalStrut(20));
        createRoomBox.add(buttonPanel);
        createRoomBox.add(Box.createVerticalStrut(20));

        successfullyCraeteBox.add(Box.createVerticalStrut(20));
        successfullyCraeteBox.add(successfullyCreateLabel);
        successfullyCraeteBox.add(Box.createVerticalStrut(20));
        successfullyCraeteBox.add(roomIDLabel);
        successfullyCraeteBox.add(Box.createVerticalStrut(20));

        // 绘制创建房间底板
        JPanel createRoomPanel = new JPanel();
        createRoomPanel.setBounds(0,0, WIDTH, HEIGHT);
        createRoomPanel.setBackground(new Color(38, 45 ,49));
        createRoomPanel.add(createRoomBox);

        //绘制成功创建房间底板
        JPanel successfullyCreatePanel = new JPanel();
        successfullyCreatePanel.setBounds(0,0, WIDTH, HEIGHT);
        successfullyCreatePanel.setBackground(new Color(38, 45 ,49));
        successfullyCreatePanel.add(successfullyCraeteBox);

        backgroundPanel.setBounds(0,0, WIDTH, HEIGHT);
        backgroundPanel.setLayout(null);
        backgroundPanel.add(createRoomPanel);

        // 查找房间响应
        createRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRoomPanel.setVisible(false);
                backgroundPanel.add(successfullyCreatePanel);
                theFrame.add(backgroundPanel);

                String roomName = roomNameTextField.getText();
                createdRoom = new Room(roomID,roomName,null,new Date());
                JPanel createdRoomPanel = Chatoy.createRoomPanel(createdRoom);;
                Chatoy.loadRoomsBox().add(createdRoomPanel);
                Chatoy.loadRoomsBox().add(Box.createVerticalStrut(10));
                Chatoy.rooms.add(createdRoom);
                // 刷新roomsPanel界面
                Chatoy.loadRoomsPanel().updateUI();

                roomID++;
            }
        });

        theFrame.add(backgroundPanel);
        theFrame.setVisible(true);
    }
}
