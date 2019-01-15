package Model;

import View.CandyView;
import View.FlickerCandyView;

import java.awt.*;

public class FlickerCandy extends Candy {
    /**
     * Candy constructor. Creates and locates the different candies on the board.
     *
     * @param x
     * @param y
     * @param type
     */
    public FlickerCandy(int x, int y, String type) {
        super(x, y, type);
    }

    /**
     * Drawing a candy according to its type
     *
     * @param g
     */
    public void render(Graphics g, int flickerIndex) {
        FlickerCandyView view = new FlickerCandyView(x, y, width, height);
        view.render(g, flickerIndex);
    }
}
