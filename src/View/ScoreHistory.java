package View;

import Model.Pacman;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ScoreHistory {

      //  TableView<Pacman> table;
     //    public  int score1=0  ;
         public String PacmanNane;

        public ScoreHistory(Stage stage)

        {
            try {
                initScoreHistory( stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void   initScoreHistory(Stage stage) throws Exception {


            GridPane pane = new GridPane();
            pane.setPadding(new Insets(10, 10, 10, 10));
            pane.setVgap(10);
            pane.setHgap(10);

            BackgroundImage myB = new BackgroundImage(new Image("res/EmptyBackground.jpeg", 550, 600, false, true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            pane.setBackground(new Background(myB));




            Scene scene = new Scene(pane, 550,600);
            stage.setScene(scene);

            stage.show();


        }


}
