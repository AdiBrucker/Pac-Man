package View;

import Controller.PacmanController;
import Model.Game;
import Model.Maze;
import Model.Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * The view of the game view
 */
public class GameView extends Canvas{
	private	static JFrame frame;
	private	static Game game;
	private	static	ViewLogic instance;
	private static PacmanController controller;
	private int x;
	private int y;

	public GameView()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameView(int x, int y){
	    this.x = x;
	    this.y = y;
    }
 
    public static void  initGame(){

        game = Game.getInstance();
        instance = ViewLogic.getInstance();
         frame = new JFrame(game.TITLE);
        frame.add(BorderLayout.PAGE_END, game);
        frame.add(BorderLayout.BEFORE_FIRST_LINE, instance.getNickname());
        frame.add(BorderLayout.WEST,instance.getlScoreForPacman());
        frame.add(BorderLayout.EAST,instance.getLifeScoreForPacman());
        instance.getTimer();
        frame.add(BorderLayout.CENTER,instance.timer);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
       	ImageIcon img = new ImageIcon("res/pacnanIm.png");
 
       	frame.setIconImage(img.getImage());
         game.start();
        
      frame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    	public void windowClosing(java.awt.event.WindowEvent e) {
	       if( PopUpLogic.getInstance().ShowEXit(false)==0) {
		        closewindow();
	       }
	 	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
 
    	}
        });
       // StartGame.closeMain();
    }
 /***update all the instance to null 
  * in favor to open the game again
  * 
  */
	public static void closewindow() {
		
	 	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.dispose();
		game.isRunning=false;
  		game.SetInstance();
 		instance.SetInstance();
 	}

    /**
     * Responsible to render the game's board
     */
    public void render(ArrayList<Pacman> pacmans, ArrayList<Maze> mazes, int playerIndex, BufferStrategy bs) {
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        pacmans.get(playerIndex).render(g);
        mazes.get(playerIndex).render(g);
        g.dispose();
        bs.show();
    }
	
}