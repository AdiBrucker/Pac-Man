package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class QuestionCandy extends Candy{


    public QuestionCandy(int x, int y) {
        super(x, y, "QuestionCandy");
    }

    public void render(Graphics g){

            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);

    }

    public boolean isQuestionCandy(){
        return true;
    }

}
