package component.mainframeleft;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import utils.ScreenUtils;
import chatoy.Chatoy;

public class JoinRoomPanel {
  final int WIDTH = 390;
  final int HEIGHT = 100;

  Font font1 = new Font("微软雅黑", Font.PLAIN, 12);
  Font font2 = new Font("微软雅黑", Font.PLAIN, 20);

  Border etchedBorder =
    BorderFactory.createEtchedBorder(BevelBorder.RAISED, Color.white, Color.pink);

  JFrame theFrame = new JFrame();

  private Chatoy parent;
  public JoinRoomPanel(String server, String token, Chatoy parent) {
    this.parent = parent;
  }

  public void init() {
    theFrame.setBounds(
      (ScreenUtils.getScreenWidth() - WIDTH) / 2,
      (ScreenUtils.getScreenHeight() - WIDTH) / 2,
      WIDTH,
      HEIGHT
    );

    JPanel backgroundPanel = new JPanel();
    backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);

    // 布局
    Box joinRoombox = Box.createVerticalBox();
    Box SearchRoomBox = Box.createHorizontalBox();

    JLabel roomSearchLabel = new JLabel("找房间：", SwingConstants.CENTER);
    roomSearchLabel.setFont(font1);
    roomSearchLabel.setOpaque(false);
    roomSearchLabel.setForeground(Color.pink);

    JTextField roomSearchTextField = new JTextField("请输入要查找的房间号", 20);
    roomSearchTextField.setFont(font1);
    roomSearchTextField.setOpaque(false);
    roomSearchTextField.setForeground(Color.pink);
    roomSearchTextField.setBorder(BorderFactory.createLineBorder(Color.pink));
    roomSearchTextField.setPreferredSize(new Dimension(150, 20));

    JButton roomSearchButton = new JButton("查找");
    roomSearchButton.setFont(font1);
    roomSearchButton.setBackground(Color.pink);
    roomSearchButton.setForeground(new Color(38, 45, 49));
    roomSearchButton.setBorder(BorderFactory.createLineBorder(Color.pink));
    roomSearchButton.setPreferredSize(new Dimension(40, 20));

    // 放置查找到房间的界面
    JPanel searchedRoomPanel = new JPanel();
    searchedRoomPanel.setLayout(new BorderLayout());
    searchedRoomPanel.setBackground(new Color(19, 28, 33));
    searchedRoomPanel.setPreferredSize(new Dimension(390, 75));

    // 查找失败提示
    JLabel failSearchLabel = new JLabel("查找失败，该房间不存在 (●'^'●)");
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
    JSplitPane searchRoomPanel = new JSplitPane(
      JSplitPane.VERTICAL_SPLIT,
      false,
      joinRoombox,
      searchedRoomPanel
    );
    searchRoomPanel.setDividerLocation(65);
    searchRoomPanel.setDividerSize(0);
    searchedRoomPanel.setVisible(false);
    searchRoomPanel.setBounds(0, 0, 380, 65);
    searchRoomPanel.setBackground(new Color(38, 45, 49));


    backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);
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
        for (int i = 0; i < parent.rooms.size(); ++ i) {
          roomNumber = Integer.toString(parent.rooms.get(i).getId());
          if (roomNumber.equals(roomID)) {
            searchRoomPanel.setSize(380, 170);
            backgroundPanel.setSize(WIDTH, 200);
            theFrame.setSize(WIDTH, 200);
            searchedRoomPanel.setVisible(true);
          }
        }
        // 查找失败显示搜索失败提示
        if (Integer.parseInt(roomID) > parent.rooms.size()) {
          searchedRoomPanel.add(failSearchLabel, BorderLayout.CENTER);
          searchRoomPanel.setSize(380, 170);
          backgroundPanel.setSize(WIDTH, 200);
          theFrame.setSize(WIDTH, 200);
          searchedRoomPanel.setVisible(true);
        }
      }
    });

    theFrame.add(backgroundPanel);
    theFrame.setVisible(true);
  }
}
