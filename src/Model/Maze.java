package Model;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze{

    public int width;
    public int height;
    public Walls[][] walls;
    public List<Candy> candy;
    public List<Ghost> ghosts;

    public Maze(String path){
        candy = new ArrayList();
        ghosts = new ArrayList();
        int getCandy = 0;
        boolean candyPoisonAppeared = false;
        int getSilverCandy = 0;
        Random rand = new Random();
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            this.width = map.getWidth();
            this.height = map.getHeight();
            int[] pixels = new int[width * height];
            walls = new Walls[width][height];
            map.getRGB(0, 0, width, height, pixels, 0, width);

            for (int xx = 0; xx < width; xx++){
                for (int yy = 0; yy < height; yy++){
                    int val = pixels[xx + (yy * width)];
                    int n = rand.nextInt(10) + 1;
                    if (val == 0xFF000000){
                        //Wall
                        walls[xx][yy] = new Walls(xx * 32, yy *32);
                    }
                    else if (val == 0xFF0000FF){
                        //pacman
                        Game.pacman.x = xx * 32;
                        Game.pacman.y = yy * 32;
                    }
                    else if (val == 0xFFFF0000){
                        //Ghost
                        ghosts.add(new Ghost(xx * 32, yy * 32));
                    }
                    else {
                        //Candy
                        if(getCandy < 10){
                            candy.add(new ScoreCandy(xx * 32, yy * 32, "Yellow"));
                            getCandy++;
                        }
                        else if (getSilverCandy <5 && n%5 == 0)
                        {
                            candy.add(new ScoreCandy(xx * 32, yy * 32, "Silver"));
                            getSilverCandy++;

                        }
                        else if(!candyPoisonAppeared){
                            candy.add(new PoisonCandy(xx * 32, yy * 32));
                            candyPoisonAppeared = true;
                        }
                        else{
                            candy.add(new QuestionCandy(xx * 32, yy * 32));
                            getCandy =0;

                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void tick(){
        for (int i = 0; i < ghosts.size(); i++){
            ghosts.get(i).tick();
        }
    }


    public void render(Graphics g){
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                if (walls[x][y] != null){
                    walls[x][y].render(g);
                }
            }
        }
        for (int i = 0; i < candy.size(); i++){
            candy.get(i).render(g);
        }
        for (int i = 0; i < ghosts.size(); i++){
            ghosts.get(i).render(g);
        }
    }


    public boolean canMove(int nextx, int nexty){
        return false;
    }

}
