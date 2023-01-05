package component.allframe;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

// 自定义滚动条 UI
public class DemoScrollBarUI extends BasicScrollBarUI {
  @Override
  protected void configureScrollBarColors() {
    // 滑道
    trackColor = Color.black;
    setThumbBounds(0, 0, 3, 10);
  }

  /**
   * 设置滚动条的宽度
   */
  @Override
  public Dimension getPreferredSize(JComponent c) {
    c.setPreferredSize(new Dimension(6, 0));
    return super.getPreferredSize(c);
  }

  // 重绘滑块的滑动区域背景
  public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
    Graphics2D g2 = (Graphics2D)g;
    GradientPaint gp = null;
    // 判断滚动条是垂直的还是水平的
    if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
      // 设置画笔
      gp = new GradientPaint(
        0,
        0,
        new Color(80, 80, 80),
        trackBounds.width,
        0,
        new Color(80, 80, 80)
      );
    }
    if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
      gp = new GradientPaint(
        0,
        0,
        new Color(80, 80, 80),
        trackBounds.height,
        0,
        new Color(80, 80, 80)
      );
    }
    g2.setPaint(gp);
    // 填充 Track
    g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    // 绘制 Track 的边框
    if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT) {
      this.paintDecreaseHighlight(g);
    }
    if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT) {
      this.paintIncreaseHighlight(g);
    }
  }

  @Override
  protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
    // 把绘制区的 x, y 点坐标定义为坐标系的原点
    g.translate(thumbBounds.x, thumbBounds.y);
    // 设置把手颜色
    g.setColor(new Color(230, 230, 250));
    // 消除锯齿
    Graphics2D g2 = (Graphics2D) g;
    RenderingHints rh = new RenderingHints(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    g2.addRenderingHints(rh);
    // 半透明
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    // 填充圆角矩形
    g2.fillRoundRect(0, 0, 40, thumbBounds.height - 1, 5, 5);
  }

  /**
   * 创建滚动条上方的按钮
   */
  @Override
  protected JButton createIncreaseButton(int orientation) {
    JButton button = new JButton();
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    button.setBorder(null);
    return button;
  }

  /**
   * 创建滚动条下方的按钮
   */
  @Override
  protected JButton createDecreaseButton(int orientation) {
    JButton button = new JButton();
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    button.setFocusable(false);
    button.setBorder(null);
    return button;
  }
}
