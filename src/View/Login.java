package View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.image.Image;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public class Login  {





   // Image image = new Image("res/first.PNG");
    Button button;
    ComboBox<String> comboBox;
    GridPane grid;
    TextField nameText;

  public Login()
    {
        try {
            initLogin(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initLogin(Stage stage) throws Exception{


         GridPane pane = new GridPane();
         pane.setPadding(new Insets(10,10,10,10));
         pane.setVgap(10);
         pane.setHgap(10);

        BackgroundImage myB = new BackgroundImage(new Image("res/first.PNG",550,600,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myB));

        ObservableList<String> options = FXCollections.observableArrayList("One Player","two Players");
        comboBox = new ComboBox<>(options);
       comboBox.setPromptText("How many Players?");
       comboBox.setMaxSize(450,50);
       comboBox.setMinSize(450,50);
        comboBox.setStyle("-fx-background-color: black" );
        GridPane.setConstraints(comboBox,4,21);

        button =new Button("Start");
        button.setMaxSize(450,35 );
        button.setMinSize(450,35);
        //button.setTranslateX(90);
       // button.setTranslateY(80);
        button.setStyle("-fx-background-color: transparent");
  //      button.
       // button.setStyle("-fx-text-Fill: white");
        button.setOnAction(e-> new StartGame(stage));

       GridPane.setConstraints(button,4,30);


        TextField nameText = new TextField("Nickname");
        nameText.setMaxSize(450,50 );
        nameText.setMinSize(450,50);
        nameText.setStyle("-fx-background-color: black" );
        GridPane.setConstraints(nameText,4,24);




        pane.getChildren().addAll(comboBox,button,nameText);
        Scene scene = new Scene(pane, 550,600);
        stage.setScene(scene);

        stage.show();





    }
}
