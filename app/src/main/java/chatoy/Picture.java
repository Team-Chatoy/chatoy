package chatoy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture extends JPanel {
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        //设置背景
        try {
            File file = new File("app/image/1.png");
            BufferedImage image = ImageIO.read(file);
            g.drawImage(image,0,0,width,height,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
