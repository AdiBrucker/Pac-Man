package Model;

import java.awt.*;

public class Walls extends Rectangle {

    public Walls(int x, int y){
        setBounds(x, y, 32, 32);
    }

    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
