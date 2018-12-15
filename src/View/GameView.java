package View;

import Controller.PacmanController;
import Model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * The view of the game view
 */
public class GameView{
	private	static JFrame frame;
	private	static Game game;
	private	static	ViewLogic instance;
	private static PacmanController controller;

	public GameView()
    {
        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    }
 /***update all the instance to null 
  * in favor to open the game again
  * 
  */
	public static void closewindow() {
		
	 	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.dispose();
		game.isRunning=false;//false shahar
//		game.pacmans.remove(game.pacmans.get(game.getPlayerIndex()));
//		game.mazes.get(game.getPlayerIndex()).ghosts=null;
//		game.mazes.get(game.getPlayerIndex()).candy=null;
//		game.mazes.remove(game.mazes.get(game.getPlayerIndex()));
  		game.SetInstance();
 		instance.SetInstance();
//	 	controller.SetInstance();
	    

 	}
	
}