package View;

import Model.Pacman;
import Model.SysData;
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

    Label l_Score;
    Label l_Name;
    Label l_rank;
    TextField Score4_Text,Score5_Text,Rank1_Text,Score1_Text, Name1_Text,Rank2_Text,Score2_Text,Score3_Text,Name2_Text,Rank3_Text,Rank4_Text,Rank5_Text,Name3_Text,Name4_Text,Name5_Text;



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


            l_rank = new Label("Rank:");
            l_rank.setMaxSize(110,35);
            l_rank.setMinSize(110,35);
            l_rank.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_rank,3,15);



            if(SysData.instance.getPacman().size()>0)
            {
                Rank1_Text = new TextField("1" );
            }
            else {
                Rank1_Text = new TextField("");
            }

            Rank1_Text.setMaxSize(110,35);
            Rank1_Text.setMinSize(110,35);
            Rank1_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Rank1_Text,3,16);

            Rank2_Text = new TextField("2" );
            Rank2_Text.setMaxSize(110,35);
            Rank2_Text.setMinSize(110,35);
            Rank2_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Rank2_Text,3,17);

            Rank3_Text = new TextField("3" );
            Rank3_Text.setMaxSize(110,35);
            Rank3_Text.setMinSize(110,35);
            Rank3_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Rank3_Text,3,18);

            Rank4_Text = new TextField("4" );
            Rank4_Text.setMaxSize(110,35);
            Rank4_Text.setMinSize(110,35);
            Rank4_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Rank4_Text,3,19);

            Rank5_Text = new TextField("5" );
            Rank5_Text.setMaxSize(110,35);
            Rank5_Text.setMinSize(110,35);
            Rank5_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Rank5_Text,3,20);




             l_Score = new Label("Score:");
            l_Score.setMaxSize(110,35);
            l_Score.setMinSize(110,35);
            l_Score.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Score,9,15);


            if(SysData.instance.getPacman().size()>0)
            {
                Score1_Text = new TextField(" " + Integer.valueOf(SysData.instance.getPacman().get(0).getScoreResult()));
                System.out.println(SysData.instance.getPacman().get(0).getScoreResult());
            }
            else {
                Score1_Text = new TextField("");
            }

            Score1_Text.setMaxSize(110,35);
            Score1_Text.setMinSize(110,35);
            Score1_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Score1_Text,9,16);


            if(SysData.instance.getPacman().size()>1)
            {
                Score2_Text = new TextField("" + Integer.valueOf(SysData.instance.getPacman().get(1).getScoreResult()));
            }
            else {
                Score2_Text = new TextField("");
            }


            Score2_Text.setMaxSize(110,35);
            Score2_Text.setMinSize(110,35);
            Score2_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Score2_Text,9,17);


            if(SysData.instance.getPacman().size()>2)
            {
                Score3_Text = new TextField("" + Integer.valueOf(SysData.instance.getPacman().get(2).getScoreResult()));
            }
            else {
                Score3_Text = new TextField("");
            }
            Score3_Text.setMaxSize(110,35);
            Score3_Text.setMinSize(110,35);
            Score3_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Score3_Text,9,18);



            if(SysData.instance.getPacman().size()>3)
            {
                Score4_Text = new TextField("" + Integer.valueOf(SysData.instance.getPacman().get(3).getScoreResult()));
            }
            else {
                Score4_Text = new TextField("");
            }
            Score4_Text.setMaxSize(110,35);
            Score4_Text.setMinSize(110,35);
            Score4_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Score4_Text,9,19);


            if(SysData.instance.getPacman().size()>4)
            {
                Score5_Text = new TextField("" + Integer.valueOf(SysData.instance.getPacman().get(4).getScoreResult()));
            }
            else {
                Score5_Text = new TextField("");
            }
            Score5_Text.setMaxSize(110,35);
            Score5_Text.setMinSize(110,35);
            Score5_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Score5_Text,9,20);



             l_Name = new Label("Name:");
            l_Name.setMaxSize(110,35);
            l_Name.setMinSize(110,35);
            l_Name.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Name,18,15);


            if(SysData.instance.getPacman().size()>0)
            {
                Name1_Text = new TextField("" + SysData.instance.getPacman().get(0).getPacmanName());
            }
            else {
                Name1_Text = new TextField("");
            }

            Name1_Text.setMaxSize(110,35);
            Name1_Text.setMinSize(110,35);
            Name1_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints( Name1_Text,18,16);


            if(SysData.instance.getPacman().size()>1)
            {
                Name2_Text = new TextField("" + SysData.instance.getPacman().get(1).getPacmanName());
            }
            else {
                Name2_Text = new TextField("");
            }

            Name2_Text.setMaxSize(110,35);
            Name2_Text.setMinSize(110,35);
            Name2_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints( Name2_Text,18,17);

            if(SysData.instance.getPacman().size()>2)
            {
                Name3_Text = new TextField("" + SysData.instance.getPacman().get(2).getPacmanName());
            }
            else {
                Name3_Text = new TextField("");
            }

            Name3_Text.setMaxSize(110,35);
            Name3_Text.setMinSize(110,35);
            Name3_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints( Name3_Text,18,18);

            if(SysData.instance.getPacman().size()>3)
            {
                Name4_Text = new TextField("" + SysData.instance.getPacman().get(3).getPacmanName());
            }
            else {
                Name4_Text = new TextField("");
            }

            Name4_Text.setMaxSize(110,35);
            Name4_Text.setMinSize(110,35);
            Name4_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints( Name4_Text,18,19);

            if(SysData.instance.getPacman().size()>4)
            {
                Name5_Text = new TextField("" + SysData.instance.getPacman().get(4).getPacmanName());
            }
            else {
                Name5_Text = new TextField("");
            }

            Name5_Text.setMaxSize(110,35);
            Name5_Text.setMinSize(110,35);
            Name5_Text.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints( Name5_Text,18,20);

//            for(int i=0;i<5;i++)
//            {
//                if(SysData.instance.getPacman().size()>=i)
//                {
//
//                }
//            }





            pane.getChildren().addAll(Score4_Text,Score5_Text,Name3_Text,Name4_Text,Name5_Text,l_Score,l_rank,l_Name,Rank1_Text,Score1_Text,Rank3_Text,Rank4_Text,Rank5_Text, Name1_Text,Rank2_Text,Score2_Text,Score3_Text,Name2_Text );







                    Scene scene = new Scene(pane, 550,600);
                    stage.setScene(scene);

            stage.show();


        }


}
