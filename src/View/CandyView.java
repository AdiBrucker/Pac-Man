package View;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CandyView {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String type;
    protected BufferedImage yellowCandy;
    protected BufferedImage goldCandy;
    protected BufferedImage silverCandy;

    public CandyView( String type, BufferedImage yellowCandy, BufferedImage goldCandy, BufferedImage silverCandy, int x, int y, int width, int height) {
        this.type = type;
        this.yellowCandy = yellowCandy;
        this.goldCandy = goldCandy;
        this.silverCandy = silverCandy;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public CandyView(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Drawing a candy according to its type
     *
     * @param g
     */
    public void render(Graphics g) {
        if (type == "Yellow") {
            g.drawImage(yellowCandy, x, y, width + 10, height + 10, null, null);

        } else if (type == "Gold") {
            g.drawImage(goldCandy, x, y, width + 10, height + 10, null, null);
        } else if (type == "Silver") {
            g.drawImage(silverCandy, x, y, width + 10, height + 10, null, null);
        }
    }
}
