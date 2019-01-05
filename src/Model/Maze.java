package Model;

import javax.imageio.ImageIO;

import View.MazeView;
import com.google.gson.Gson;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class that holds the model of the maze. Contains the walls, candies, ghosts, etc.
 */
public class Maze {

    //width and height represents the dimensions of the maze
    public int width;
    public int height;
    //array the contains the walls in the maze
    public Walls[][] walls;
    //list the contains the candies in the maze
    public List<Candy> candy;
    //list the contains the ghosts in the maze
    public List<Ghost> ghosts;
    public static boolean level2 = false;
    public static int goodx = 0; // it will determine the location of the tmp ghosts
    public static int goody = 0;

    /**
     * Constructor
     * Responsible to build the maze by reading the pixels from the source map which is imported in the constructor
     * by reading the map the walls, candies and ghosts are sorted into the relevant array/list and later are drawn on games map.
     *
     * @param path
     */
    public Maze(String path) {
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

            for (int xx = 0; xx < width; xx++) {
                for (int yy = 0; yy < height; yy++) {
                    int val = pixels[xx + (yy * width)];
                    int n = rand.nextInt(10) + 1;
                    if (val == 0xFF000000) {
                        //Wall
                        walls[xx][yy] = new Walls(xx * 32, yy * 32);
                    } else if (val == 0xFF0000FF) {
                        //pacman
                        Game.pacmans.get(Game.getPlayerIndex()).x = xx * 32;
                        Game.pacmans.get(Game.getPlayerIndex()).y = yy * 32;
                    } else if (val == 0xFFFF0000) {
                        //Ghost
                        ghosts.add(new Ghost(xx * 32, yy * 32));
                        goodx = xx * 32;
                        goody = yy * 32;
                    } else {
                        //Candy
                        // if its a gold candy
                        Candy c = null;

                        if (level2) {// if the player pass the first level
                            level2 = false;
                            //candy.add(new ScoreCandy(xx * 32, yy * 32, "Gold"));
                            c = CandyFactory.makeCandy("Gold", xx * 32, yy * 32);
                            //c.setLocation();
                            candy.add(c);
                        }
                        if (getCandy < 30) {
                            //candy.add(new ScoreCandy(xx * 32, yy * 32, "Yellow"));
                            c = CandyFactory.makeCandy("Yellow", xx * 32, yy * 32);
                            //c.setLocation(xx * 32,  yy * 32);
                            candy.add(c);
                            getCandy++;
                        } else if (getSilverCandy < 5 && n % 5 == 0) {
                            c = CandyFactory.makeCandy("Silver", xx * 32, yy * 32);
                            //c.setLocation(xx * 32,  yy * 32);
                            candy.add(c);
                            //candy.add(new ScoreCandy(xx * 32, yy * 32, "Silver"));
                            getSilverCandy++;
                        } else if (!candyPoisonAppeared) {
                            c = CandyFactory.makeCandy("PoisonCandy", xx * 32, yy * 32);
                            //c.setLocation(xx * 32,  yy * 32);
                            candy.add(c);
                            //candy.add(new PoisonCandy(xx * 32, yy * 32));
                            candyPoisonAppeared = true;
                        } else {

                            c = CandyFactory.makeCandy("QuestionCandy", xx * 32, yy * 32);
                            //c.setLocation(xx * 32,  yy * 32);
                            candy.add(c);
                            //candy.add(new QuestionCandy(xx * 32, yy * 32));
                            getCandy = 0;

                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Manages the movements of the ghosts on the map
     */
    public void tick() {
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).tick();
        }
    }

    /**
     * draws the walls, candies and ghosts on the game's map
     *
     * @param g
     */
    public void render(Graphics g) {
        MazeView view = new MazeView(walls, candy, ghosts, width, height);
        view.render(g);
    }

    public static int getGhostWidth() {
        return goodx;
    }

    public static int getGhostHeigh() {
        return goody;
    }
}
