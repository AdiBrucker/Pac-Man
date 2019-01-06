package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import View.PopUpLogic;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Holds the temporary ghosts which appear when a candy question is eaten
 */
public class TmpGhost extends Ghost{
	public int index =0;
    private BufferedImage icon;
    private static int tmpIndex = 1; // index for know who is the answer that tmpghost holds
    public static int cheat_id = -1;

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
                if(cheat_id == 1)
                {
                        Color myColour = new Color(255, 255, 255, 150);
                    icon.createGraphics();
                    Graphics2D g2d = (Graphics2D)icon.getGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.setStroke(new BasicStroke(10));
                    g2d.setColor(myColour);
                    g2d.fillRect( 0, 0, 120, 120);
                }
                g.drawImage(icon, x+tmpIndex, y-tmpIndex, width, height, null, null);
            }
            else if (tmpIndex == 2){
                icon = ImageIO.read(getClass().getResource("/res/sprites/tmpGhost2.png"));
                if(cheat_id == 2)
                {
                    Color myColour = new Color(255, 255, 255, 150);
                    icon.createGraphics();
                    Graphics2D g2d = (Graphics2D)icon.getGraphics();
                    g2d.setColor(myColour);
                    g2d.setStroke(new BasicStroke(10));
                    g2d.fillRect(0, 0, 120, 120);
                }

                g.drawImage(icon, x-tmpIndex, y+tmpIndex, width, height, null, null);
            }
            else if (tmpIndex == 3){

                icon = ImageIO.read(getClass().getResource("/res/sprites/tmpGhost3.png"));

                if(cheat_id == 3)
                {
                    Color myColour = new Color(255, 255, 255, 150);
                    icon.createGraphics();
                    Graphics2D g2d = (Graphics2D)icon.getGraphics();
                    g2d.setColor(myColour);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.fillRect(0, 0, 120, 120);
                }
                g.drawImage(icon, x+tmpIndex, y-tmpIndex, width, height, null, null);
            }
            else if (tmpIndex == 4){
                icon = ImageIO.read(getClass().getResource("/res/sprites/tmpGhost4.png"));
                if(cheat_id == 4)
                {
                    Color myColour = new Color(255, 255, 255, 150);
                    icon.createGraphics();
                    Graphics2D g2d = (Graphics2D)icon.getGraphics();
                    g2d.setColor(myColour);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.fillRect(0, 0, 120, 120);
                }
                g.drawImage(icon, x-tmpIndex, y+tmpIndex, width, height, null, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     	index=tmpIndex;

        tmpIndex++;
    	if (Game.getPlayerIndex()==1) {
    		 if(tmpIndex  > PopUpLogic.getInstance().getPlayer1SizeArra ()){
    	            tmpIndex = 1;
    	        }    	}
    	else {
    		 if(tmpIndex  > PopUpLogic.getInstance().getPlayer2SizeArra ()){
    	            tmpIndex = 1;
    	        }
    	}
    }

    public static void cheetWithCorrectTmpGhost(){
        int indexwanted = Integer.parseInt(SysData.instance.getQuestions().
                get(PopUpLogic.getInstance().getindexOfQuestion()).getCorrect_ans());

        if(cheat_id == -1)
        {
            cheat_id = indexwanted;
        } else {
            cheat_id = -1;
        }
    }


    public     int getIndex() {
        return  index;
    }
    public static int getTmpIndex() {
        return tmpIndex;
    }
}
