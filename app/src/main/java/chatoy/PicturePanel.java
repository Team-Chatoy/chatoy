package chatoy;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JPanel;

//stop
public class PicturePanel extends JPanel {
  Image img;

  public PicturePanel(Image initImg) {
    this.img = initImg;
    this.setOpaque(true);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
  }
}
