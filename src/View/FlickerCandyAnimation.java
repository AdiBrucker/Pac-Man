package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FlickerCandyAnimation {

    public static BufferedImage spritesheet;
    public static BufferedImage[] flickerCandy;

    public FlickerCandyAnimation(){
        try {
            spritesheet = ImageIO.read(getClass().getResource("/res/sprites/FlickerCandy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        flickerCandy = new BufferedImage[15];

        flickerCandy[0] = getSprites(0, 0);
        flickerCandy[1] = getSprites(16, 0);
        flickerCandy[2] = getSprites(32, 0);
        flickerCandy[3] = getSprites(48, 0);
        flickerCandy[4] = getSprites(64, 0);
        flickerCandy[5] = getSprites(80, 0);
        flickerCandy[6] = getSprites(96, 0);
        flickerCandy[7] = getSprites(112, 0);
        flickerCandy[8] = getSprites(128, 0);
        flickerCandy[9] = getSprites(144, 0);
        flickerCandy[10] = getSprites(0, 16);
        flickerCandy[11] = getSprites(16, 16);
        flickerCandy[12] = getSprites(32, 16);
        flickerCandy[13] = getSprites(48, 16);
        flickerCandy[14] = getSprites(64, 16);
    }

    public BufferedImage getSprites(int x, int y){
        return spritesheet.getSubimage(x, y, 16, 16);
    }
}
