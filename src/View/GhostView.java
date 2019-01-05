package View;

import Model.Game;
import Model.SpriteSheet;

import java.awt.*;

public class GhostView {

    int xx;
    int yy;
    int x;
    int y;
    int width;
    int height;
    SpriteSheet sheet;

    public GhostView(int xx, int yy, int x, int y, int width, int height, SpriteSheet sheet) {
        this.xx = xx;
        this.yy = yy;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }

    /**
     * Draws the ghosts image.
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(sheet.getSprite(xx, yy), x, y, width, height, null);
    }
}
