package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.*;

public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(String path){
        try {
            sheet = read(getClass().getResource(path));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public BufferedImage getSprite(int xx, int yy){
        return sheet.getSubimage(xx, yy, 16, 16);
    }
}
