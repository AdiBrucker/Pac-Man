package View;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Draw the poison candy in the game
 */


public class PosionCandyView extends CandyView {

    BufferedImage poisonCandy;

    public PosionCandyView(int x, int y, String type, BufferedImage poisonCandy) {
        super(x, y, type);
        this.poisonCandy = poisonCandy;
    }

    /**
     * Draws the poison candy image
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(poisonCandy, x, y, width + 20, height + 20, null, null);
    }
}
