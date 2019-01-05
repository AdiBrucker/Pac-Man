package View;

import Model.Candy;
import Model.Ghost;
import Model.Walls;

import java.awt.*;
import java.util.List;

public class MazeView {

    public Walls[][] walls;
    //list the contains the candies in the maze
    public List<Candy> candy;
    //list the contains the ghosts in the maze
    public List<Ghost> ghosts;
    //width and height represents the dimensions of the maze
    public int width;
    public int height;

    /**
     * Constructor
     * @param walls
     * @param candy
     * @param ghosts
     */
    public MazeView( Walls[][] walls, List<Candy>candy, List<Ghost> ghosts, int width, int height) {
        this.walls = walls;
        this.candy = candy;
        this.ghosts = ghosts;
        this.width = width;
        this.height = height;
    }

    /**
     * draws the walls, candies and ghosts on the game's map
     *
     * @param g
     */
    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (walls[x][y] != null) {
                    walls[x][y].render(g);
                }
            }
        }
        for (int i = 0; i < candy.size(); i++) {
            candy.get(i).render(g);
        }
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).render(g);
        }
    }
}
