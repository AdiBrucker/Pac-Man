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

    public GameView()
    {
        try {
            initGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PacmanController controller;
    /*  static Stage window;
      Scene scene;
      static GridPane gridPane;*/
    public static void  initGame(){
        ViewLogic instance = ViewLogic.getInstance();
        controller = PacmanController.CreateInstance(); // singleton (construction by method 'Create').
        SysData s=new SysData();//// just for checking the question json

        Game game = Game.getInstance();
        JFrame frame = new JFrame(game.TITLE);
        //  Container pane = bs.getContentPane();
        frame.add(BorderLayout.PAGE_END, game);
        frame.add(BorderLayout.WEST,instance.getlScoreForPacman());
        frame.add(BorderLayout.EAST,instance.getLifeScoreForPacman());
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);

        game.start();
    }



}