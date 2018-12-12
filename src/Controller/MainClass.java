package Controller;

import Model.Game;
import Model.SysData;
import View.Login;
import View.PopUpLogic;
import View.StartGame;
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
import java.util.ArrayList;

import javax.swing.*;

public class MainClass extends Application{

	
  private static PacmanController controller;

  static MainClass instance;

    public static MainClass getInstance() {
        if (instance == null)
            instance = new MainClass();
        return  instance;
    }



    public void start(Stage stage) throws Exception {

<<<<<<< HEAD
	  /// ViewLogic instances = ViewLogic.getInstance();/// we didnt need that line here  because game view call to this class later 
	   controller = PacmanController.CreateInstance(); // singleton (construction by method 'Create').
       Login login = new Login();
=======
		//ViewLogic instances = ViewLogic.getInstance();
		controller = PacmanController.CreateInstance(); // singleton (construction by method 'Create').

        //Login login = new Login();
        StartGame s = new StartGame(stage);
>>>>>>> 3920b5eb30d0ef932361083505816924fdb0bf8f

    }
}
