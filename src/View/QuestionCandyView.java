package View;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QuestionCandyView extends CandyView {

    private BufferedImage redCandy;

    public QuestionCandyView(int x, int y, String type, BufferedImage redCandy) {
        super(x, y, type);
        this.redCandy = redCandy;
    }

    /**
     * Draws a question candy on the map
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(redCandy, x, y, width + 20, height + 20, null, null);
    }
}
