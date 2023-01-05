package component.mainframeleft;

import chatoy.Chatoy;
import democlass.database.Room;
import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class JoinRoomPanel {

    final int WIDTH = 390;
    final int HEIGHT = 100;

    Font font1 = new Font("微软雅黑",Font.PLAIN,12);
    Font font2 = new Font("微软雅黑",Font.PLAIN,20);
    public static Room createdRoom;
    Border etchedBorder =
            BorderFactory.createEtchedBorder(BevelBorder.RAISED, Color.white, Color.pink);

    JFrame theFrame = new JFrame();

    public void init() {

        theFrame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-WIDTH)/2, WIDTH, HEIGHT);
        //theFrame.setResizable(false);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0,0,WIDTH,HEIGHT);

        // 布局
        Box joinRoombox = Box.createVerticalBox();
        Box SearchRoomBox = Box.createHorizontalBox();

        JLabel roomSearchLabel = new JLabel("找房间：",SwingConstants.CENTER);
        roomSearchLabel.setFont(font1);
        roomSearchLabel.setOpaque(false);
        roomSearchLabel.setForeground(Color.pink);

        JTextField roomSearchTextField = new JTextField("请输入要查找的房间号",20);
        roomSearchTextField.setFont(font1);
        roomSearchTextField.setOpaque(false);
        roomSearchTextField.setForeground(Color.gray);
        roomSearchTextField.setBorder(BorderFactory.createLineBorder(Color.pink));
        roomSearchTextField.setPreferredSize(new Dimension(150, 20));
        roomSearchTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if(roomSearchTextField.getText().equals("")){
                    roomSearchTextField.setText("请输入要查找的房间号");
                    roomSearchTextField.setForeground(Color.gray);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(roomSearchTextField.getText().equals("请输入要查找的房间号")){
                    roomSearchTextField.setText("");
                    roomSearchTextField.setForeground(Color.pink);
                }
            }
        });

        JButton roomSearchButton = new JButton("查找");
        roomSearchButton.setFont(font1);
        roomSearchButton.setBackground(Color.pink);
        roomSearchButton.setForeground(new Color(38, 45 ,49));
        roomSearchButton.setBorder(BorderFactory.createLineBorder(Color.pink));
        roomSearchButton.setPreferredSize(new Dimension(40, 20));

        // 放置查找到房间的界面
        JPanel searchedRoomPanel = new JPanel();
        searchedRoomPanel.setLayout(new BorderLayout());
        searchedRoomPanel.setBackground(new Color(19,28,33));
        searchedRoomPanel.setPreferredSize(new Dimension(390,75));

        // 查找失败提示
        JLabel failSearchLabel = new JLabel("       查找失败，该房间不存在 (●'^'●)");
        failSearchLabel.setFont(font2);
        failSearchLabel.setOpaque(false);
        failSearchLabel.setForeground(Color.pink);

        // 设置水平间距
        SearchRoomBox.add(Box.createHorizontalStrut(10));
        SearchRoomBox.add(roomSearchLabel);
        SearchRoomBox.add(roomSearchTextField);
        SearchRoomBox.add(Box.createHorizontalStrut(7));
        SearchRoomBox.add(roomSearchButton);
        SearchRoomBox.add(Box.createHorizontalStrut(10));
        // 设置垂直间距
        joinRoombox.add(Box.createVerticalStrut(18));
        joinRoombox.add(SearchRoomBox);
        joinRoombox.add(Box.createVerticalStrut(18));

        // 创建底板
        JSplitPane searchRoomPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                false,joinRoombox,searchedRoomPanel);
        searchRoomPanel.setDividerLocation(65);
        searchRoomPanel.setDividerSize(0);
        searchedRoomPanel.setVisible(false);
        searchRoomPanel.setBounds(0,0, 380, 65);
        searchRoomPanel.setBackground(new Color(38, 45 ,49));


        backgroundPanel.setBounds(0,0, WIDTH, HEIGHT);
        backgroundPanel.setLayout(null);
        backgroundPanel.add(searchRoomPanel);

        // 查找房间响应
        roomSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 根据房间号查找已创建的房间

                String roomID = roomSearchTextField.getText();
                String roomNumber;
                // 查找成功显示找的的房间
                for(int i = 0; i < Chatoy.rooms.size(); i++){
                    roomNumber = Integer.toString(Chatoy.rooms.get(i).getId());
                    if(roomNumber.equals(roomID)) {
                        searchRoomPanel.setSize(380,170);
                        backgroundPanel.setSize(WIDTH,200);
                        theFrame.setSize(WIDTH,207);
                        searchedRoomPanel.setVisible(true);
                        //searchedRoomPanel.add(new RoomPanel(LeftBox.getRoomList().get(i),LeftBox.getRoomsBox()));

                        JPanel roomPanel = new JPanel();
                        roomPanel.setBackground(new Color(38, 45 ,49));
                        roomPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.gray, Color.white));
                        Box roomBox = Box.createVerticalBox();
                        roomPanel.setSize(370,160);

                        // 创建name和number区域
                        JLabel nameLabel = new JLabel(Chatoy.rooms.get(i).getName());
                        // 自适应name大小来设置JTextArea高度
                        int nameTextAreaHeight = 0;
                        int nameTextAreaWidth = 110;
                        double nameDivide = nameLabel.getPreferredSize().getWidth() / nameTextAreaWidth;
                        for (int j = 0; j < nameDivide; j++) {
                            nameTextAreaHeight += (int) nameLabel.getPreferredSize().getHeight();
                        }
                        JTextArea nameTextArea = new JTextArea();
                        // nameTextArea.setFont(roomFont);
                        nameTextArea.setPreferredSize(new Dimension(nameTextAreaWidth, nameTextAreaHeight));
                        nameTextArea.setLineWrap(true);
                        nameTextArea.setEditable(false);
                        nameTextArea.setOpaque(false);
                        nameTextArea.setText(Chatoy.rooms.get(i).getName());
                        nameTextArea.setForeground(Color.pink);
                        JLabel numberLabel = new JLabel("#" + Chatoy.rooms.get(i).getId());
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
                        enterButton.setForeground(Color.pink);
                        enterButton.setBorder(BorderFactory.createLineBorder(Color.gray));
                        enterButton.setBackground(new Color(38, 45 ,49));
                        enterButton.setPreferredSize(new Dimension(150, 20));
                        JPanel buttonPanel = new JPanel();
                        buttonPanel.add(enterButton);
                        buttonPanel.setOpaque(false);

                        // 组装roomBox
                        roomBox.add(Box.createVerticalStrut(4));
                        roomBox.add(nameNumberPanel);
                        roomBox.add(Box.createVerticalStrut(5));
                        roomBox.add(buttonPanel);
                        roomBox.add(Box.createHorizontalStrut(10));

                        roomPanel.add(roomBox);

                        createdRoom = new Room(Chatoy.rooms.get(i).getId(),Chatoy.rooms.get(i).getName(),null,new Date());
                        JPanel createdRoomPanel = Chatoy.createRoomPanel(createdRoom);
                        searchedRoomPanel.add(createdRoomPanel);
                        Border lineBorder = BorderFactory.createLineBorder(Color.gray);
                    }
                }
                // 查找失败显示搜索失败提示
                if(Integer.parseInt(roomID) > Chatoy.rooms.size()){
                    searchedRoomPanel.add(failSearchLabel,BorderLayout.CENTER);
                    searchRoomPanel.setSize(380,170);
                    backgroundPanel.setSize(WIDTH,200);
                    theFrame.setSize(WIDTH,200);
                    searchedRoomPanel.setVisible(true);
                }


            }
        });

        theFrame.add(backgroundPanel);
        theFrame.setVisible(true);
    }
}


