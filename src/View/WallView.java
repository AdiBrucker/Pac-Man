package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Draw the wall in the game
 */
public class WallView extends Rectangle {


    int x;
    int y;
    int width;
    int height;
    BufferedImage blueBrick;

    /**
     * Constructor
     */
    public WallView(int x, int y, int width, int height, BufferedImage blueBrick) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.blueBrick = blueBrick;

    }

    public void render(Graphics g){
        g.drawImage(blueBrick, x, y, width, height, null, null);
    }
}
