package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.*;

/**
 * Hold the image which contains the characters of the game
 */
public class SpriteSheet {
    private BufferedImage sheet;

    /**
     * Constructor- Imports the spritesheet which contains pacman and the ghosts to the game.
     * @param path
     */
    public SpriteSheet(String path){
        try {
            sheet = read(getClass().getResource(path));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Gets the relevant character according to its location on the spritesheet image
     * @param xx
     * @param yy
     * @return
     */
    public BufferedImage getSprite(int xx, int yy){
        return sheet.getSubimage(xx, yy, 16, 16);
    }
}
