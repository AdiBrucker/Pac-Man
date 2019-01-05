package Model;

import View.GhostView;

import java.awt.*;
import java.util.Random;

/**
 * Ghost class holds the model of the ghosts in the game
 */
public class Ghost extends Rectangle implements IMovable {
    private int random = 0;
    private int smart = 1;
    private int state = random;
    private int right = 0;
    private int left = 1;
    private int up = 2;
    private int down = 3;
    public Random randomGen = new Random();
    private int speed = 2;
    private int dir = randomGen.nextInt(4);

    /**
     * Constructor of Ghosts. Responsible to create and locate on the game's board
     * @param x
     * @param y
     */
    public Ghost(int x, int y) {
        new Location(x, y);
        setBounds(x, y, 32, 32);
    }

    /**
     * Method which run the movement of the ghosts in the game.
     */
    public void tick() {
        ghostsAI();

    }

    /**
     * The AI for the ghost movement. When a ghost intersects with a wall the directions of its movement is changed.
     */
    private void ghostsAI() {
        if (state == random) {
            if (dir == right) {
                if (Game.canMove(x + speed, y, width, height)) {
                    x += speed;
                } else {
                    dir = randomGen.nextInt(4);
                }
            } else if (dir == left) {
                if (Game.canMove(x - speed, y, width, height)) {
                    x -= speed;
                } else {
                    dir = randomGen.nextInt(4);
                }
            } else if (dir == up) {
                if (Game.canMove(x, y - speed, width, height)) {
                    y -= speed;
                } else {
                    dir = randomGen.nextInt(4);
                }
            } else if (dir == down) {
                if (Game.canMove(x, y + speed, width, height)) {
                    y += speed;
                } else {
                    dir = randomGen.nextInt(4);
                }
            }

        }
        else if (state == smart) {

        }
    }

    /**
     * Draws the ghosts image.
     * @param g
     */
    public void render(Graphics g) {
        SpriteSheet sheet = Game.spriteSheets.get(Game.getPlayerIndex());
        GhostView view = new GhostView(0, 16, x, y, 32, 32, sheet);
        view.render(g);
    }
}
