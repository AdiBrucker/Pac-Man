package Model;

import java.awt.*;

/**
 * Holds the temporary ghosts which appear when a candy question is eaten
 */
public class TmpGhost extends Ghost{

    /**
     * Constructor- Creates a temporary ghost
     * @param x
     * @param y
     */
    public TmpGhost(int x, int y) {
        super(x, y);
    }

    /**
     * Draws the temporary ghost
     * @param g
     */
    public void render(Graphics g){
        SpriteSheet sheet = Game.spriteSheets.get(Game.getPlayerIndex());
        g.drawImage(sheet.getSprite(0, 16), x, y, 32, 32, null);
        g.setColor(Color.BLUE);
    }
}
