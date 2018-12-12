package Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import View.PacmanAnimation;
import View.PopUpLogic;

public class Game extends Canvas implements Runnable, KeyListener {

	public boolean isRunning = false;
    public static final int WIDTH = 1120, HEIGHT = 800;
    public static final String TITLE = "PACMAN";
    public static ArrayList<Pacman> pacmans;
    public static ArrayList<Maze> mazes;
    public static ArrayList<SpriteSheet> spriteSheets;
    private  static ArrayList<Game> instances;
    public static int playerCount = 0;
    private static Thread thread;
    static PopUpLogic popInctance = PopUpLogic.getInstance();
    private static int playerIndex = 0; // set the game turn with the relevant player

    public Game(){

            if (playerCount ==1) {
                pacmans = new ArrayList<>();
                mazes = new ArrayList<>();
                spriteSheets = new ArrayList<>();
            }

        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));


        addKeyListener(this);
        new PacmanAnimation();
        if(playerCount ==1) {
            pacmans.add(new Pacman(Game.WIDTH / 2, Game.HEIGHT / 2, popInctance.getPlayer1()));
        }

        else if(playerCount ==2) {
            pacmans.add(new Pacman(Game.WIDTH / 2, Game.HEIGHT / 2, popInctance.getPlayer2()));
            playerIndex=1;
        }

        mazes.add(new Maze("/res/map/map.png"));
        spriteSheets.add(new SpriteSheet("/res/sprites/spritesheet.png"));

    }

    public synchronized void start(){
        if (isRunning){
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!isRunning){
            return;
        }
        isRunning = false;
        try{
            thread.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void tick(){
        pacmans.get(playerIndex).tick();
        mazes.get(playerIndex).tick();
    }


    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        pacmans.get(playerIndex).render(g);
        mazes.get(playerIndex).render(g);
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {

        requestFocus();
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta = 0;
        double ns = 1000000000 / targetTick;

        while (isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                render();
                fps++;
                delta=0;
            }
            if (System.currentTimeMillis() - timer >= 1000){
         //       System.out.println(fps);
                fps = 0;
                timer += 1000;
            }
        }
        stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) pacmans.get(playerIndex).right = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) pacmans.get(playerIndex).left = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacmans.get(playerIndex).up = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacmans.get(playerIndex).down = true;
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE)// when pressing space 
        	PopUpLogic.getInstance().pauseGame();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) pacmans.get(playerIndex).right = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) pacmans.get(playerIndex).left = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacmans.get(playerIndex).up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacmans.get(playerIndex).down = false;
    }

    public static Game getInstance() {
        if (instances == null){
            instances = new ArrayList<>();
        }

        while (playerCount < popInctance.getNumOfPlayers()) {
            playerCount++;
            instances.add(new Game());
        }

        return  instances.get(playerIndex);
    }
    /// need to check this one    
    public void SetInstance() {
    	instances.remove(instances.get(playerIndex));
    }

    public static int getPlayerIndex() {
        return playerIndex;
    }

    public static void setPlayerIndex(int playerIndex) {
        Game.playerIndex = playerIndex;
    }
}
