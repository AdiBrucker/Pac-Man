package Model;

import View.QuestionCandyView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Holds and connect between question to the question candies.
 */
public class QuestionCandy extends Candy {
    private BufferedImage redCandy;

    /**
     * Constructor- Creates a question candy
     * @param x
     * @param y
     */
    public QuestionCandy(int x, int y) {
        super(x, y, "QuestionCandy");

        try {
            redCandy = ImageIO.read(getClass().getResource("/res/sprites/redCandy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws a question candy on the map
     * @param g
     */
    public void render(Graphics g) {
        QuestionCandyView view = new QuestionCandyView(x, y, "QuestionCandy", redCandy);
        view.render(g);
    }
}
