package View;
import Controller.MainClass;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;


public class StartGame {

    public StartGame( ) {
        try {
            initStartGame(new Stage() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Button button;
    Button button1;
    Button button2;
    Image image = new Image("res/Startgame.PNG");

    public void initStartGame(Stage stage) {
        try {

            ImageView iv = new ImageView();

            StackPane pane = new StackPane();
            Scene scene = new Scene(pane, 550, 600);
            pane.getChildren().add(iv);
            stage.setScene(scene);
            stage.show();
            //StackPane pane = new  StackPane();
            //  pane.getChildren().add(button);
            iv.setImage(image);
            iv.setFitHeight(700);
            iv.setFitWidth(700);
            iv.setPreserveRatio(true);
            stage.setScene(scene);
           // stage.show();
          	Image icon =new Image("pacnanIm.png");////   src//pacnanIm.jpg
        	stage.getIcons().add(icon);
        	stage.setTitle("PACMAN");
            button = new Button(" ");
            pane.getChildren().add(button);
            button.setMaxSize(260, 30);
            button.setTranslateX(-110);
            button.setTranslateY(-110);
            button.setStyle("-fx-background-color:transparent ; -fx-text-fill:transparent ");
         //  button.setOnAction(e->Controller.MainClass.getInstance().begin() );
            button.setOnAction(e->new Login(stage)  );


            //  pane.getChildren().add(button1);
            button1 = new Button(" ");
            pane.getChildren().add(button1);
            button1.setMaxSize(300, 30);
            button1.setTranslateX(-50);
            button1.setTranslateY(23);
            button1.setStyle("-fx-background-color:transparent; -fx-text-fill:transparent ");
            button1.setOnAction(e-> new QuestionManager(stage));

            button2 = new Button(" ");
            pane.getChildren().add(button2);
            button2.setMaxSize(250, 30);
            button2.setTranslateX(-60);
            button2.setTranslateY(100);
            button2.setStyle("-fx-background-color:transparent; -fx-text-fill:transparent ");
            button2.setOnAction(e -> {
                stage.close();
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

