package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Candy extends Rectangle {
	
	public LifeBonus LifeBonus; 

    private String type;
    private Location location;
    private BufferedImage yellowCandy;
    private BufferedImage goldCandy;
    private BufferedImage silverCandy;


    public Candy(int x, int y, String type){
        setBounds(x + 10, y + 10, 10, 10);
        this.type = type;
        location = new Location(x+ 10,y+ 10);
        try {
            yellowCandy = ImageIO.read(getClass().getResource("/res/sprites/yellowCandy.png"));
            goldCandy = ImageIO.read(getClass().getResource("/res/sprites/goldCandy.png"));
            silverCandy = ImageIO.read(getClass().getResource("/res/sprites/silverCandy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics g){

        if(type == "Yellow"){
            g.drawImage(yellowCandy, x, y, width+10, height+10, null, null);

        }
        else if (type == "Gold") {
            g.drawImage(goldCandy, x, y, width+10, height+10, null, null);
        }
        else{
            g.drawImage(silverCandy, x, y, width+10, height+10, null, null);
        }

    }


    public String getType() {
        return type;
    }
    
    
    public boolean getLifeBonus(){
    	 
    	        return LifeBonus.life();
    	 
    	    }
    	 
    	    // If you want to be able to change the LifeBonus  dynamically
    	 
    	    // add the following method
    	 
    	    public void setnewLifeBonus(LifeBonus newLifeBonus){
    	 
    	    	LifeBonus = newLifeBonus;
    	  }

}
