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
        //设置背景
        BufferedImage image = null;
        try {
            File file = new File("image/1.png");
            image = ImageIO.read(file);
            g.drawImage(image,0,0,width,height,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
