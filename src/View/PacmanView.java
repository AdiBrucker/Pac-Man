package View;

import Model.CONSTS;
import Model.Game;
import Model.SysData;

import java.awt.*;

public class PacmanView {
    int x;
    int y;
    int width;
    int height;

    public PacmanView(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the pacman and its animation.
     * @param g
     */
    public void render(Graphics g, int lastDir, int animationIndexImage) {

        if (lastDir == CONSTS.RIGHT) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2], x, y, width, height, null);
        }
        if (lastDir == CONSTS.LEFT) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2], x + 32, y, -width, height, null);
        }
        if (lastDir == CONSTS.UP) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2 + 2], x, y, width, height, null);
        }
        if (lastDir == CONSTS.DOWN) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2 + 4], x, y, width, height, null);
        }
    }

    public void ShowGameOver(int score) {
        PopUpLogic.getInstance().ShowGameOver(score);

        if(Game.pacmans.size() == 1) {
            SysData.instance.AddPacman(Game.pacmans.get(0).getScore(), Game.pacmans.get(0).getPacmanName());
        }
        else {
            SysData.instance.AddPacman(Game.pacmans.get(0).getScore(), Game.pacmans.get(0).getPacmanName());
            SysData.instance.AddPacman(Game.pacmans.get(1).getScore(), Game.pacmans.get(1).getPacmanName());

        }

        GameView.closewindow();
    }
}
