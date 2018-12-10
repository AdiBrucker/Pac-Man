package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Walls extends Rectangle {

    private BufferedImage blueBrick;

    public Walls(int x, int y){
        setBounds(x, y, 32, 32);
        try {
            blueBrick = ImageIO.read(getClass().getResource("/res/sprites/blueBrick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g){
        g.drawImage(blueBrick, x, y, width, height, null, null);
    }
}
