package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Poison candy responsible to hold data.
 */
public class PoisonCandy extends Candy {

    private BufferedImage poisonCandy;

    /**
     * Constructor- Creates a poison candy
     * @param x
     * @param y
     */
    public PoisonCandy(int x, int y) {
        super(x, y, "PoisonCandy");

        try {
            poisonCandy = ImageIO.read(getClass().getResource("/res/sprites/poisonCandy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Draws the poison candy image
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(poisonCandy, x, y, width + 10, height + 10, null, null);

    }

}
