package Model;

import View.CandyView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Candy class holds the model of the candies in the game
 */
public class Candy extends Rectangle {

     //holdes the type of the candy to be created
    private String type;
    private BufferedImage yellowCandy;
    private BufferedImage goldCandy;
    private BufferedImage silverCandy;

    /**
     * Candy constructor. Creates and locates the different candies on the board.
     *
     * @param x
     * @param y
     * @param type
     */
    public Candy(int x, int y, String type) {
        setBounds(x + 10, y + 10, 10, 10);
        this.type = type;
        new Location(x + 10, y + 10);
        try {
            yellowCandy = ImageIO.read(getClass().getResource("/res/sprites/yellowCandy.png"));
            goldCandy = ImageIO.read(getClass().getResource("/res/sprites/goldCandy.png"));
            silverCandy = ImageIO.read(getClass().getResource("/res/sprites/silverCandy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Drawing a candy according to its type
     *
     * @param g
     */
    public void render(Graphics g) {
        CandyView view = new CandyView(type, yellowCandy, goldCandy, silverCandy, x, y, width, height);
        view.render(g);
    }

    /**
     * Returns the candy type
     *
     * @return
     */
    public String getType() {
        return type;
    }

}
