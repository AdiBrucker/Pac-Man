package Model;

import java.awt.*;

public class Candy extends Rectangle {
	
	public LifeBonus LifeBonus; 

    private String type;
    private Location location;

    public Candy(int x, int y, String type){
        setBounds(x + 10, y + 10, 10, 10);
        this.type = type;
        location = new Location(x+ 10,y+ 10);

    }

    public void render(Graphics g){

        if(type == "Yellow"){
            g.setColor(Color.yellow);
            g.fillRect(x, y, width, height);
        }
        else if (type == "Gold") {
        	   g.setColor(new Color(255,204,51));
               g.fillRect(x, y, width, height);
        }
        else{
            g.setColor(Color.lightGray);
            g.fillRect(x, y, width, height);
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
