package Model;

import java.awt.*;

/**
 * Interface which represents the movement of the characters in the games and its drawings.
 */
public interface IMovable {

    void tick();
    void render(Graphics g);

}
