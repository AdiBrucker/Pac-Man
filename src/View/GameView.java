package View;

import Controller.PacmanController;
import Model.Game;
import Model.SysData;
import Model.*;
import View.ViewLogic;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.awt.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
import java.awt.*;


import javax.swing.*;

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
 
    	instance = ViewLogic.getInstance();
        controller = PacmanController.CreateInstance(); // singleton (construction by method 'Create').
        game = Game.getInstance();
        frame = new JFrame(game.TITLE);
        frame.add(BorderLayout.PAGE_END, game);
        frame.add(BorderLayout.WEST,instance.getlScoreForPacman());
        frame.add(BorderLayout.EAST,instance.getLifeScoreForPacman());
        instance.getTimer();
        frame.add(BorderLayout.CENTER,instance.timer);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
       	ImageIcon img = new ImageIcon("pacnanIm.png");
 
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
 	 	game.isRunning=false;	
		game.SetInstance();
 		instance.SetInstance();
		controller.SetInstance();
	    

 	}
	
}