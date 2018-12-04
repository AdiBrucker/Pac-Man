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
import javafx.scene.image.Image;
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

     /*  static Stage window;
      Scene scene;
      static GridPane gridPane;*/
    public static void  initGame(){
         instance = ViewLogic.getInstance();
        controller = PacmanController.CreateInstance(); // singleton (construction by method 'Create').
        //SysData s=new SysData();//// just for checking the question json

          game = Game.getInstance();
          frame = new JFrame(game.TITLE);
        //  Container pane = bs.getContentPane();
        frame.add(BorderLayout.PAGE_END, game);
        frame.add(BorderLayout.WEST,instance.getlScoreForPacman());
        frame.add(BorderLayout.EAST,instance.getLifeScoreForPacman());
        frame.add(BorderLayout.CENTER,instance.getTimer());
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);

        game.start();
    }


	public static void closewindow() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.dispose();
 		game.isRunning=true;
		game.pacman=null;
		game.maze.ghosts=null;
		game.maze.candy=null;
		game.maze=null;
 		game.SetInstance();
	   instance.SetInstance();
	   controller.SetInstance();
	    

 	}
	
}