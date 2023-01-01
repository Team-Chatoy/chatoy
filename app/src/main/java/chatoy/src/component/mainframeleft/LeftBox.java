package component.mainframeleft;

import component.jpanel.RoomPanel;
import component.jscrollpane.DemoScrollBarUI;
import democlass.database.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class LeftBox extends Box{
    final int WIDTH = 200;
    final int HEIGHT = 560;
    int frameWidth = 820;


    private JPanel roomsPanel;
    Box roomsBox;
    private List<Room> roomList; // 测试用
    private Vector<Vector> roomsData;

    public LeftBox() {
        // 垂直布局
        super(BoxLayout.Y_AXIS);

        // 组装视图

        // 查找和添加键
        JTextField roomSearchTextField = new JTextField("搜索");
        roomSearchTextField.setForeground(Color.gray);
        JButton roomAddButton = new JButton("+");
        roomAddButton.setPreferredSize(new Dimension(40, 30));
        roomAddButton.setBackground(Color.pink);
        // 查找和添加键区域
        JPanel roomSearchAddPanel = new JPanel();
        roomSearchAddPanel.setLayout(new BorderLayout());
        roomSearchAddPanel.setBackground(Color.green);
        roomSearchAddPanel.setMaximumSize(new Dimension(frameWidth, 25));
        // roomSearchAddPanel.setLayout(new FlowLayout());
        // 组装查找和添加键区域
        roomSearchAddPanel.add(roomSearchTextField, BorderLayout.CENTER);
        roomSearchAddPanel.add(roomAddButton, BorderLayout.EAST);

        roomAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 弹出一个对话框，让用户输入图书的信息


                new RoomPanel(new Room(6, "new","new", new Date()), roomsBox);
                roomsPanel.updateUI();
            }
        });

        this.add(roomSearchAddPanel);

        // room区域
        roomsPanel = new JPanel();
        roomsPanel.setBackground(new Color(19, 28, 33));

        roomsBox = Box.createVerticalBox();
        // 载入所有的roomData
        roomsData = new Vector<>();
        // 组装room区域
        // 测试用的room (删除时同时删掉第21行)
        roomList = new ArrayList<>();
        roomList.add(new Room(1, "testroom1", "this is a testroom", new Date()));
        roomList.add(new Room(2, "testroom2", "this is a testroom", new Date()));
        roomList.add(new Room(3, "testroom3------------------------------------------------------", "this is a testroom",  new Date()));
        roomList.add(new Room(4, "testroom1", "this is a testroom", new Date()));
        roomList.add(new Room(5, "testroom1", "this is a testroom", new Date()));

        for (int i=0; i < roomList.size(); i++) {
            new RoomPanel(roomList.get(i), roomsBox);
        }
        // 设置滚动条
        roomsPanel.add(roomsBox);
        JScrollPane jScrollPane = new JScrollPane(roomsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI());
        this.add(jScrollPane);

        jScrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });

        // requestData();
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
