package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PoisonCandy extends Candy{

    private BufferedImage poisonCandy;

    public PoisonCandy(int x, int y) {
        super(x, y, "PoisonCandy");
        LifeBonus=new isntLife();

        try {
            poisonCandy = ImageIO.read(getClass().getResource("/res/sprites/poisonCandy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics g){
        g.drawImage(poisonCandy, x, y, width+10, height+10, null, null);

    }

}
