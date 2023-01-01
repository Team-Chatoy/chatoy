package component.mainframeleft;

import democlass.database.Room;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class RoomPanel extends JPanel{

    public RoomPanel(Room room, Box roomsBox) {
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
        // roomBox添加到roomPanel, 并添加到leftBox
        roomPanel.add(roomBox);
        // roomPanel.setPreferredSize(new Dimension(160, 30+nameTextAreaHeight+(int)buttonPanel.getPreferredSize().getHeight()));
        roomsBox.add(roomPanel);
        roomsBox.add(Box.createVerticalStrut(10));


        // enterButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         try {
        //             BackgroundPanel backgroundPanel = new BackgroundPanel(ImageIO.read(new File(PathUtils.getRealPath("LoginBackground.png"))));
        //
        //             backgroundPanel.setLayout(new BorderLayout());
        //             JTextArea jTextArea = new JTextArea();
        //             jTextArea.setRows(5);
        //             jTextArea.setForeground(Color.white);
        //             jTextArea.setBackground(new Color(25, 25, 25));
        //             jTextArea.setText(room.getDescription());
        //             Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.white, Color.pink);
        //             jTextArea.setBorder(etchedBorder);
        //             backgroundPanel.add(jTextArea, BorderLayout.SOUTH);
        //
        //
        //             jSplitPane.setRightComponent(backgroundPanel);
        //         } catch (IOException ex) {
        //             throw new RuntimeException(ex);
        //         }
        //     }
        // });
    }
}
