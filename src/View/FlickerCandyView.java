package View;

import Model.CONSTS;
import Model.Game;
import Model.SysData;

import java.awt.*;

public class FlickerCandyView {
    int x;
    int y;
    int width;
    int height;

    public FlickerCandyView(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the pacman and its animation.
     * @param g
     */
    public void render(Graphics g, int flickerIndex) {
        if (flickerIndex < 15){
            g.drawImage(FlickerCandyAnimation.flickerCandy[flickerIndex], x, y, width + 15, height + 15, null);
        }
    }

}
