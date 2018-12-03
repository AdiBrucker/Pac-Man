package Model;

import java.awt.*;
import java.util.Random;

public class Ghost extends Rectangle implements IMovable{
    private int random = 0;
    private int smart = 1;
    private int state = random;
    private int right = 0;
    private int left = 1;
    private int up = 2;
    private int down = 3;
    public Random  randomGen = new Random();
    private int time = 0;
    private int targetTime = 240;
    private int speed = 2;
    private int dir = randomGen.nextInt(4);
    private Location location;

    public Ghost(int x, int y){
        
        location = new Location(x,y);

        setBounds(x, y, 32, 32 );
    }
	public void setLocation(Location location) {
		this.location = location;
	}


    public void tick(){

        if (state == random){
            if (dir == right){
                if (canMove(x + speed, y)){
                    x += speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
            else if (dir == left){
                if (canMove(x - speed, y)){
                    x -= speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
            else if (dir == up){
                if (canMove(x, y - speed)){
                    y -= speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
            else if (dir == down){
                if (canMove(x, y + speed)){
                    y += speed;
                }
                else {
                    dir = randomGen.nextInt(4);
                }
            }
           
        }
        else if (state == smart){

        }

    }


    public boolean canMove(int nextx, int nexty){
        Rectangle bounds = new Rectangle(nextx, nexty, width, height);
        Maze maze = Game.maze;

        for (int xx = 0; xx < maze.walls.length; xx++){
            for (int yy = 0; yy < maze.walls[0].length; yy++){
                if (maze.walls[xx][yy] != null){
                    if (bounds.intersects(maze.walls[xx][yy])){

                        for (int x = 0; x < maze.walls.length; x++){
                            for (int y = 0; y < maze.walls[0].length; y++){
                                if (maze.walls[x][y] != null){
                                    if (bounds.intersects(maze.walls[x][y])){

                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void render(Graphics g){
        SpriteSheet sheet = Game.spriteSheet;
        g.drawImage(sheet.getSprite(0, 16), x, y, 32, 32, null);
    }
}
