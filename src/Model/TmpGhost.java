package Model;

import java.awt.*;

public class TmpGhost extends Ghost{


    public TmpGhost(int x, int y) {
        super(x, y);
    }

    public void render(Graphics g){
        SpriteSheet sheet = Game.spriteSheets.get(Game.getPlayerIndex());
        g.drawImage(sheet.getSprite(0, 16), x, y, 32, 32, null);
        g.setColor(Color.BLUE);
    }
}
