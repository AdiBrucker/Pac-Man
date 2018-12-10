package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class QuestionCandy extends Candy{
    private BufferedImage redCandy;


    public QuestionCandy(int x, int y) {
        super(x, y, "QuestionCandy");

        try {
            redCandy = ImageIO.read(getClass().getResource("/res/sprites/redCandy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g){
        g.drawImage(redCandy, x, y, width+10, height+10, null, null);

    }

    public boolean isQuestionCandy(){
        return true;
    }

}
