package View;
 
import javax.swing.JFrame;

import Model.ModelLogic;
import Controller.MainClass;
import  com.jfoenix.controls.*;
 import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;



public class StartGame {

    public StartGame(Stage stage) {
        try {
            initStartGame(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Button button;
    Button button1;
    Button button2;
    Button button5;
    Image image = new Image("res/Startgame.PNG");
    StackPane pane;


    public void initStartGame(Stage stage) {
        try {

            ImageView iv = new ImageView();
            PopUpLogic popUpLogic= PopUpLogic.getInstance();

             pane = new StackPane();
            Scene scene = new Scene(pane, 550, 600);
            pane.getChildren().add(iv);
            stage.setScene(scene);
            stage.show();
            iv.setImage(image);
            iv.setFitHeight(700);
            iv.setFitWidth(700);
            iv.setPreserveRatio(true);
            stage.setScene(scene);
            button = new Button("Start");
        	Image icon =new Image("pacnanIm.png");////   src//pacnanIm.jpg
        	stage.getIcons().add(icon);
        	stage.setTitle("PACMAN");
            pane.getChildren().add(button);
            button.setMaxSize(260, 30);
            button.setTranslateX(-110);
            button.setTranslateY(-110);
            button.setStyle("-fx-background-color:transparent ; -fx-text-fill:transparent ");
             button.setOnAction(e->new GameView() );
            stage.setOnCloseRequest(e-> {
            if( PopUpLogic.getInstance().ShowEXit(true)==0) {
	       		 ModelLogic.getsData().writeQuestionsToJsonFile();
	       		  ModelLogic.getsData().Serialize(ModelLogic.getsData());
         		 System.out.println(ModelLogic.getsData());

	       		 stage.close();
            	}
            e.consume();
            });
 
          button.setOnAction(e -> popUpLogic.showGameType(pane));
  

            button1 = new Button("Question");
            pane.getChildren().add(button1);
            button1.setMaxSize(300, 30);
            button1.setTranslateX(-50);
            button1.setTranslateY(23);
            button1.setStyle("-fx-background-color:transparent; -fx-text-fill:transparent ");
            button1.setOnAction(e-> new QuestionManager(stage));


            button5 = new Button("Score");
            pane.getChildren().add(button5);
            button5.setMaxSize(300, 30);
            button5.setTranslateX(-60);
            button5.setTranslateY(-38);
            button5.setStyle("-fx-background-color:transparent; -fx-text-fill:transparent ");
            button5.setOnAction(e-> new ScoreHistory(stage));





            button2 = new Button("LogOut");
            pane.getChildren().add(button2);
            button2.setMaxSize(250, 30);
            button2.setTranslateX(-60);
            button2.setTranslateY(100);
            button2.setStyle("-fx-background-color:transparent; -fx-text-fill:transparent ");
            button2.setOnAction(e -> {
            	 if( PopUpLogic.getInstance().ShowEXit(true)==0) {
            		 ModelLogic.getsData().writeQuestionsToJsonFile();
            		 ModelLogic.getsData().Serialize(ModelLogic.getsData());
                      stage.close();
                 }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void handle(ActionEvent event) {
        if (event.getSource() == button) {
            //       System.out.println("wowwwww");
        }
    }



}

