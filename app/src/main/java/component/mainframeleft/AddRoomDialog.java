package component.mainframeleft;

import utils.ScreenUtils;

import javax.swing.*;

public class AddRoomDialog extends JDialog {
    final int WIDTH = 300;
    final int HEIGHT = 300;

    public AddRoomDialog(JFrame theFrame, String title, boolean isModel) {
        super(theFrame, title, isModel);

        // 组装视图

        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2, (ScreenUtils.getScreenHeight()-HEIGHT)/2, WIDTH, HEIGHT);
        Box holeBox = Box.createVerticalBox();

        // 组装name
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("房间名称");
        JTextField nameTextField = new JTextField();

        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(15));
        nameBox.add(nameTextField);


    }
}
