package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class PacmanAnimation {

    public static BufferedImage spritesheet;
    public static BufferedImage[] pacman;

    public PacmanAnimation(){
        try {
            spritesheet = ImageIO.read(getClass().getResource("/res/sprites/spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pacman = new BufferedImage[6];

        pacman[0] = getSprites(0, 0);
        pacman[1] = getSprites(16, 0);
        pacman[2] = getSprites(32, 0);
        pacman[3] = getSprites(48, 0);
        pacman[4] = getSprites(64, 0);
        pacman[5] = getSprites(80, 0);
    }

    public BufferedImage getSprites(int x, int y){
        return spritesheet.getSubimage(x, y, 16, 16);
    }

}
