package Model;

import java.awt.*;

public interface IMovable {

    public void tick();
    public boolean canMove(int nextx, int nexty);
    public void render(Graphics g);

}
