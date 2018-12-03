package Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener {

    private boolean isRunning = false;
    public static final int WIDTH = 1120, HEIGHT = 800;
    public static final String TITLE = "PACMAN";
    public static Pacman pacman;
    public static Maze maze;
    public static SpriteSheet spriteSheet;
    private static Game instance;

    private Thread thread;

    public Game(){
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));


        addKeyListener(this);
        pacman = new Pacman(Game.WIDTH/2, Game.HEIGHT/2);
        maze = new Maze("/res/map/map.png");
        spriteSheet = new SpriteSheet("/res/sprites/spritesheet.png");

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
        pacman.tick();
        maze.tick();
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
        pacman.render(g);
        maze.render(g);
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
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) pacman.right = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) pacman.left = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacman.up = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacman.down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) pacman.right = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) pacman.left = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) pacman.up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) pacman.down = false;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return  instance;
    }

}
