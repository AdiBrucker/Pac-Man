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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.util.List;

public class ScoreHistory {

    Label l_Score;
    Label l_Name;
    Label l_rank;
    Label Score4_Text,Score5_Text,Rank1_Text,Score1_Text, Name1_Text,Rank2_Text,Score2_Text,Score3_Text,Name2_Text,Rank3_Text,Rank4_Text,Rank5_Text,Name3_Text,Name4_Text,Name5_Text;
    Button B_back;
    Image pic;


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
            l_rank.setFont(new Font("Lucida Console", 20));
           // l_rank.setStyle("-fx-background-color: yellow");
            l_rank.setTextFill(Color.WHITE);
            GridPane.setConstraints(l_rank,3,15);



            if(SysData.instance.getPacman().size()>0)
            {
                Rank1_Text = new Label("1" );
            }
            else {
                Rank1_Text = new Label("");
            }

            Rank1_Text.setMaxSize(110,35);
            Rank1_Text.setMinSize(110,35);
            Rank1_Text.setFont(new Font("Lucida Console", 20));
            Rank1_Text.setTextFill(Color.YELLOW);
            GridPane.setConstraints(Rank1_Text,3,16);

            Rank2_Text = new Label("2" );
            Rank2_Text.setMaxSize(110,35);
            Rank2_Text.setMinSize(110,35);
            Rank2_Text.setFont(new Font("Lucida Console", 20));
            Rank2_Text.setTextFill(Color.RED);
            GridPane.setConstraints(Rank2_Text,3,17);

            Rank3_Text = new Label("3" );
            Rank3_Text.setMaxSize(110,35);
            Rank3_Text.setMinSize(110,35);
            Rank3_Text.setFont(new Font("Lucida Console", 20));
            Rank3_Text.setTextFill(Color.LIGHTBLUE);
            GridPane.setConstraints(Rank3_Text,3,18);

            Rank4_Text = new Label("4" );
            Rank4_Text.setMaxSize(110,35);
            Rank4_Text.setMinSize(110,35);
            Rank4_Text.setFont(new Font("Lucida Console", 20));
            Rank4_Text.setTextFill(Color.PINK);
            GridPane.setConstraints(Rank4_Text,3,19);

            Rank5_Text = new Label("5" );
            Rank5_Text.setMaxSize(110,35);
            Rank5_Text.setMinSize(110,35);
            Rank5_Text.setFont(new Font("Lucida Console", 20));
            Rank5_Text.setTextFill(Color.GREENYELLOW);
            GridPane.setConstraints(Rank5_Text,3,20);




             l_Score = new Label("Score:");
            l_Score.setMaxSize(110,35);
            l_Score.setMinSize(110,35);
            l_Score.setFont(new Font("Lucida Console", 20));
            l_Score.setTextFill(Color.WHITE);
            GridPane.setConstraints(l_Score,9,15);


            if(SysData.instance.getPacman().size()>0)
            {
                Score1_Text = new Label(" " + Integer.valueOf(SysData.instance.getPacman().get(0).getScoreResult()));
                System.out.println(SysData.instance.getPacman().get(0).getScoreResult());
            }
            else {
                Score1_Text = new Label("");
            }

            Score1_Text.setMaxSize(110,35);
            Score1_Text.setMinSize(110,35);
            Score1_Text.setFont(new Font("Lucida Console", 20));
            Score1_Text.setTextFill(Color.YELLOW);
            GridPane.setConstraints(Score1_Text,9,16);


            if(SysData.instance.getPacman().size()>1)
            {
                Score2_Text = new Label("" + Integer.valueOf(SysData.instance.getPacman().get(1).getScoreResult()));
            }
            else {
                Score2_Text = new Label("");
            }


            Score2_Text.setMaxSize(110,35);
            Score2_Text.setMinSize(110,35);
            Score2_Text.setFont(new Font("Lucida Console", 20));
            Score2_Text.setTextFill(Color.RED);
            GridPane.setConstraints(Score2_Text,9,17);


            if(SysData.instance.getPacman().size()>2)
            {
                Score3_Text = new Label("" + Integer.valueOf(SysData.instance.getPacman().get(2).getScoreResult()));
            }
            else {
                Score3_Text = new Label("");
            }
            Score3_Text.setMaxSize(110,35);
            Score3_Text.setMinSize(110,35);
            Score3_Text.setFont(new Font("Lucida Console", 20));
            Score3_Text.setTextFill(Color.LIGHTBLUE);
            GridPane.setConstraints(Score3_Text,9,18);



            if(SysData.instance.getPacman().size()>3)
            {
                Score4_Text = new Label("" + Integer.valueOf(SysData.instance.getPacman().get(3).getScoreResult()));
            }
            else {
                Score4_Text = new Label("");
            }
            Score4_Text.setMaxSize(110,35);
            Score4_Text.setMinSize(110,35);
            Score4_Text.setFont(new Font("Lucida Console", 20));
            Score4_Text.setTextFill(Color.PINK);
            GridPane.setConstraints(Score4_Text,9,19);


            if(SysData.instance.getPacman().size()>4)
            {
                Score5_Text = new Label("" + Integer.valueOf(SysData.instance.getPacman().get(4).getScoreResult()));
            }
            else {
                Score5_Text = new Label("");
            }
            Score5_Text.setMaxSize(110,35);
            Score5_Text.setMinSize(110,35);
            Score5_Text.setFont(new Font("Lucida Console", 20));
            Score5_Text.setTextFill(Color.GREENYELLOW);
            GridPane.setConstraints(Score5_Text,9,20);



             l_Name = new Label("Name:");
            l_Name.setMaxSize(110,35);
            l_Name.setMinSize(110,35);
            l_Name.setFont(new Font("Lucida Console", 20));
            l_Name.setTextFill(Color.WHITE);
            GridPane.setConstraints(l_Name,18,15);


            if(SysData.instance.getPacman().size()>0)
            {
                Name1_Text = new Label("" + SysData.instance.getPacman().get(0).getPacmanName());
            }
            else {
                Name1_Text = new Label("");
            }

            Name1_Text.setMaxSize(110,35);
            Name1_Text.setMinSize(110,35);
            Name1_Text.setFont(new Font("Lucida Console", 20));
            Name1_Text.setTextFill(Color.YELLOW);
            GridPane.setConstraints( Name1_Text,18,16);


            if(SysData.instance.getPacman().size()>1)
            {
                Name2_Text = new Label("" + SysData.instance.getPacman().get(1).getPacmanName());
            }
            else {
                Name2_Text = new Label("");
            }

            Name2_Text.setMaxSize(110,35);
            Name2_Text.setMinSize(110,35);
            Name2_Text.setFont(new Font("Lucida Console", 20));
            Name2_Text.setTextFill(Color.RED);
            GridPane.setConstraints( Name2_Text,18,17);

            if(SysData.instance.getPacman().size()>2)
            {
                Name3_Text = new Label("" + SysData.instance.getPacman().get(2).getPacmanName());
            }
            else {
                Name3_Text = new Label("");
            }

            Name3_Text.setMaxSize(110,35);
            Name3_Text.setMinSize(110,35);
            Name3_Text.setFont(new Font("Lucida Console", 20));
            Name3_Text.setTextFill(Color.LIGHTBLUE);
            GridPane.setConstraints( Name3_Text,18,18);

            if(SysData.instance.getPacman().size()>3)
            {
                Name4_Text = new Label("" + SysData.instance.getPacman().get(3).getPacmanName());
            }
            else {
                Name4_Text = new Label("");
            }

            Name4_Text.setMaxSize(110,35);
            Name4_Text.setMinSize(110,35);
            Name4_Text.setFont(new Font("Lucida Console", 20));
            Name4_Text.setTextFill(Color.PINK);
            GridPane.setConstraints( Name4_Text,18,19);

            if(SysData.instance.getPacman().size()>4)
            {
                Name5_Text = new Label("" + SysData.instance.getPacman().get(4).getPacmanName());
            }
            else {
                Name5_Text = new Label("");
            }

            Name5_Text.setMaxSize(110,35);
            Name5_Text.setMinSize(110,35);
            Name5_Text.setFont(new Font("Lucida Console", 20));
            Name5_Text.setTextFill(Color.GREENYELLOW);
            GridPane.setConstraints( Name5_Text,18,20);

             Button B_back = new Button("Back");
              B_back.setMaxSize(90,90);
              B_back.setOnAction(e->new StartGame(stage) );
            B_back.setStyle("-fx-background-color: white");
            GridPane.setConstraints( B_back,3,30);












            pane.getChildren().addAll( B_back, Score4_Text,Score5_Text,Name3_Text,Name4_Text,Name5_Text,l_Score,l_rank,l_Name,Rank1_Text,Score1_Text,Rank3_Text,Rank4_Text,Rank5_Text, Name1_Text,Rank2_Text,Score2_Text,Score3_Text,Name2_Text );







                    Scene scene = new Scene(pane, 550,600);
                    stage.setScene(scene);

            stage.show();


        }


}
