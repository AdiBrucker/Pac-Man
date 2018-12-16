package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Holds the temporary ghosts which appear when a candy question is eaten
 */
public class TmpGhost extends Ghost{

    private BufferedImage icon;
    private static int tmpIndex = 1; // index for know who is the answer that tmpghost holds

    public TmpGhost(int x, int y) {
        super(x, y);
    }

    /**
     * Draws the temporary ghost
     * @param g
     */
    public void render(Graphics g){

        try {
            if (tmpIndex ==1) {
                icon = ImageIO.read(getClass().getResource("/res/sprites/tmpGhost1.png"));
                g.drawImage(icon, x+tmpIndex, y-tmpIndex, width, height, null, null);
            }
            else if (tmpIndex == 2){
                icon = ImageIO.read(getClass().getResource("/res/sprites/tmpGhost2.png"));
                g.drawImage(icon, x-tmpIndex, y+tmpIndex, width, height, null, null);
            }
            else if (tmpIndex == 3){
                icon = ImageIO.read(getClass().getResource("/res/sprites/tmpGhost3.png"));
                g.drawImage(icon, x+tmpIndex, y-tmpIndex, width, height, null, null);
            }
            else if (tmpIndex == 4){
                icon = ImageIO.read(getClass().getResource("/res/sprites/tmpGhost4.png"));
                g.drawImage(icon, x-tmpIndex, y+tmpIndex, width, height, null, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // g.drawImage(icon, x, y, width, height, null, null);
        tmpIndex++;
        if(tmpIndex == 5){
            tmpIndex = 1;
        }
    }

    public static int getTmpIndex() {
        return tmpIndex;
    }
}
