package chatoy;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Chatting extends JFrame{

    private JPanel emptyPanel;
    private JPanel enterPanel;
    private JSplitPane ChatSplitPanel1;
    private JSplitPane ChatSplitPanel2;
    private JSplitPane ChatSplitPanel3;
    private JSplitPane ListSplitPanel1;
    private JSplitPane ListSplitPanel2;
    private JSplitPane ListSplitPanel3;
    private JSplitPane UIPanel;
    private JButton chatterbt1;
    private JButton chatterbt2;
    private JButton enterbt;
    private JLabel listLabel;
    private JLabel chatterLabel;
    private JTextArea outputArea;
    private JTextField inputField;

    public static void main(String[] args) {
        Chatting chatting = new Chatting();
    }

    public Chatting() {
        JFrame frame = new JFrame();
        frame.setTitle("聊天界面");
        frame.setSize(600, 400);
        Container container = frame.getContentPane();

        //绘制好友列表SplitPanel
        listLabel = new JLabel("好友列表",SwingConstants.CENTER);
        chatterbt1 = new JButton("小李");
        chatterbt2 = new JButton("小明");
        emptyPanel = new JPanel();
        ListSplitPanel1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,listLabel,chatterbt1);
        ListSplitPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,ListSplitPanel1,chatterbt2);
        ListSplitPanel3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,ListSplitPanel2,emptyPanel);
        ListSplitPanel2.setDividerSize(2);
        ListSplitPanel3.setDividerSize(2);

        //绘制聊天SplitPanel
        chatterLabel = new JLabel("小李",SwingConstants.CENTER);
        outputArea = new JTextArea();
        inputField = new JTextField();
        enterPanel = new JPanel();
        ChatSplitPanel1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,chatterLabel,outputArea);
        ChatSplitPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,ChatSplitPanel1,enterPanel);
        ChatSplitPanel3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,ChatSplitPanel2,inputField);
        ChatSplitPanel2.setDividerLocation(250);
        ChatSplitPanel2.setDividerSize(2);
        ChatSplitPanel3.setDividerLocation(280);
        ChatSplitPanel3.setDividerSize(2);

        enterbt = new JButton("发送");
        enterbt.setBounds(500, 255, 80, 25);
        container.add(enterbt);

        UIPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,ListSplitPanel3,ChatSplitPanel3);
        UIPanel.setDividerLocation(180);
        container.add(UIPanel);

        class EnterListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == enterbt) {
                    String text = inputField.getText();
                    outputArea.setText(text);
                }
                else if(e.getSource() == chatterbt1){
                    chatterLabel.setText("小李");
                }
                else {
                    chatterLabel.setText("小明");
                }

            }
        }

        EnterListener listener = new EnterListener();
        enterbt.addActionListener(listener);
        chatterbt1.addActionListener(listener);
        chatterbt2.addActionListener(listener);

        var bgPicUrl = this.getClass().getResource("/img/background.png");
        ImageIcon bgPic = new ImageIcon(bgPicUrl);
        var bgPanel = new PicturePanel(bgPic.getImage());
        bgPanel.setBounds(0, 0, 600, 400);
        container.add(bgPanel);


        //Set the look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            System.out.println("Error setting the LAF!");
            e.printStackTrace();
        }
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

