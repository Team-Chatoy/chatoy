package component.allframe;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    // 声明图片
    private Image backIcon;
    public BackgroundPanel(Image backIcon) {
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制背景
        g.drawImage(backIcon, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
