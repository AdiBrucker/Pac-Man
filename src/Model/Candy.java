package Model;

import java.awt.*;

public class Candy extends Rectangle {

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
        else{
            g.setColor(Color.lightGray);
            g.fillRect(x, y, width, height);
        }

    }


    public String getType() {
        return type;
    }
}
