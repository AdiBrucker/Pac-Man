package Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import View.GameView;
import View.PacmanAnimation;
import View.PopUpLogic;
import View.StartGame;

/**
 * Class which is responsible to hold the game objects such as pacman, maze, game etc.
 */
public class Game extends Canvas implements Runnable, KeyListener {


    public boolean isRunning = false;
    //defines the dimensions of the game
    public static final int WIDTH = 1120, HEIGHT = 800;
    public static final String TITLE = "PACMAN";
    //An array that holds pacman instances for multiple players.
    public static ArrayList<Pacman> pacmans;
    //An array that holds maze instances for multiple players.
    public static ArrayList<Maze> mazes;
    //An array that holds spriteshets instances for multiple players.
    public static ArrayList<SpriteSheet> spriteSheets;
    //An array that holds game instances for multiple players.
    private static ArrayList<Game> instances;
    //uses to indicate when to stop the watch and when the game is running
    public static  boolean flag = false;
    //Counts how many players there are in the game
    public static int playerCount = 0;
    public static Thread thread;
    //Responsible for the pop up messages
    static PopUpLogic popInctance = PopUpLogic.getInstance();
    // set the game turn with the relevant player
    private static int playerIndex = 0;

    /**
     * Class constructor. initiates the arrays, build the game boards with its contents.
     */
    public Game() {

        if (playerCount == 1) {
            pacmans = new ArrayList<>();
            mazes = new ArrayList<>();
            spriteSheets = new ArrayList<>();
        }

        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));


        addKeyListener(this);
        new PacmanAnimation();
        if (playerCount == 1) {
            pacmans.add(new Pacman(Game.WIDTH / 2, Game.HEIGHT / 2, popInctance.getPlayer1()));
        } else if (playerCount == 2) {
            pacmans.add(new Pacman(Game.WIDTH / 2, Game.HEIGHT / 2, popInctance.getPlayer2()));
            playerIndex = 1;
        }

        mazes.add(new Maze("/res/map/map.png"));
        spriteSheets.add(new SpriteSheet("/res/sprites/spritesheet.png"));
    }

    /**
     * Starting the game
     */
    public synchronized void start() {

        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * A method which is responsible to run the game and define the speed of the characters.
     * Manages their movements and their speed.
     */
    @Override
    public void run() {
        try {
            requestFocus();
            double timer = System.currentTimeMillis();
            long lastTime = System.nanoTime();
            double targetTick = 60.0;
            double delta = 0;
            double ns = 1000000000 / targetTick;

            while (isRunning) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    if (flag) {
                        synchronized (getInstanceList().get(playerIndex)) {
                            try {
                                getInstanceList().get(playerIndex).wait();
                            } catch (Exception e) {
                                getInstanceList().get(playerIndex).notify();
                            }
                            flag = false;
                        }
                    }
                    tick();
                    render();
                    delta = 0;
                }
                if (System.currentTimeMillis() - timer >= 1000) {
                    timer += 1000;
                }
            }

            stop(isRunning);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsible for the movement of the characters
     */
    public void tick() {
        Game.pacmans.get(playerIndex).tick();
        Game.mazes.get(playerIndex).tick();
    }

    /**
     * Responsible to render the game's board
     */
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        GameView view = new GameView(0, 0);
        view.render(Game.pacmans, Game.mazes, playerIndex, bs);
    }

    /**
     * Stops the game
     */
    public synchronized void stop(boolean isRunning) {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        try {
            Game.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * listens to the keyboard types. controls pacman movements and decides whether is should go left/right/up/down
     * according to the key typed
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) pacmans.get(playerIndex).right = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) pacmans.get(playerIndex).left = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacmans.get(playerIndex).up = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacmans.get(playerIndex).down = true;

        if (e.getKeyCode() == KeyEvent.VK_SPACE)// when pressing space
            PopUpLogic.getInstance().pauseGame();
    }

    /**
     * When a key is release the method is responsible to stop pacman's movement.
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) pacmans.get(playerIndex).right = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) pacmans.get(playerIndex).left = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacmans.get(playerIndex).up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacmans.get(playerIndex).down = false;
    }

    /**
     * Getter for the game instance
     * @return
     */
    public static Game getInstance() {
        if (instances == null) {
            instances = new ArrayList<>();
        }
        while (playerCount < popInctance.getNumOfPlayers()) {
            playerCount++;
            instances.add(new Game());
        }

        return instances.get(playerIndex);
    }

    /**
     * Setter for instance
     */
    public void SetInstance() {
        instances.remove(instances.get(playerIndex));
        instances = null;
        playerIndex = 0;
        playerCount = 0;
    }
    
    public static ArrayList<Game> getInstanceList() {
       return instances;
    }

    /**
     * Gets for the player index.
     * @return
     */
    public static int getPlayerIndex() {
        return playerIndex;
    }

    
    public static void setFlag(boolean a) {
    	flag=a;
    }
    /**
     * Sets the player index.
     * @param playerIndex
     */
    public static void setPlayerIndex(int playerIndex) {
        Game.playerIndex = playerIndex;
    }

    /**
     * A method that checks if in the next step of pacman or the ghosts exists a wall. If a wall exists the method returns false and doesn't
     * allow pacman or the ghost to continue it the same direction.
     * @param nextx
     * @param nexty
     * @param width
     * @param height
     * @return
     */
    public static boolean canMove(int nextx, int nexty, int width, int height){
        Rectangle bounds = new Rectangle(nextx, nexty, width,  height);
        Maze maze = Game.mazes.get(Game.getPlayerIndex());

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

}
