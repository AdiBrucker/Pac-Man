package Model;

import java.awt.*;

public class PoisonCandy extends Candy{

    public PoisonCandy(int x, int y) {
        super(x, y, "PoisonCandy");
    }

    public void render(Graphics g){

        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);

    }

}
