package View;

import Model.Pacman;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.util.List;

public class ScoreHistory {

    TableView table;
    Group Group;

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

///////////////////////////////////////

            final Label label = new Label("Address Book");
//
//
//
//            TableColumn firstNameCol = new TableColumn("First Name");
//            TableColumn lastNameCol = new TableColumn("Last Name");
//            TableColumn emailCol = new TableColumn("Email");
//
//            table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
//
//            final VBox vbox = new VBox();
//            vbox.setSpacing(5);
//            vbox.setPadding(new Insets(10, 0, 0, 10));
//
//            GridPane.setConstraints(table,4,23);
//
//            Scene scene = new Scene(pane, 550,600);
//

    //        pane.getChildren().addAll( vbox,label, table);
//////////////////////////////////////////
            Scene scene = new Scene(pane, 550,600);
                    stage.setScene(scene);

            stage.show();


        }


}
