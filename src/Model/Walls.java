package Model;

import View.WallView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Represents the walls on the game maze
 */
public class Walls extends Rectangle {


    private BufferedImage blueBrick;
    /**
     * Constructor- Creates the walls in the game
     * @param x
     * @param y
     */
    public Walls(int x, int y){
        setBounds(x, y, 32, 32);
        try {
            blueBrick = ImageIO.read(getClass().getResource("/res/sprites/blueBrick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the walls in the game
     * @param g
     */
    public void render(Graphics g){
        WallView view = new WallView(x, y, width, height, blueBrick);
        view.render(g);
    }
}
