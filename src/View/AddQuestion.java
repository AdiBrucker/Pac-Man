package View;

import Model.SysData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * The view for adding a question to the game
 */
public class AddQuestion {


        Button button;
        Label Difficulty;
        Label l_Quest_Num;
        Label l_Team;
        Label l_Question;
        Label l_Answer1;
        Label l_Answer2;
        Label l_Answer3;
        Label l_Answer4;
        Label l_curect_Ans;
        GridPane grid;
        TextField QuestText;
        TextField Text_Answer1;
        TextField Text_Answer2;
        TextField Text_Answer3;
        TextField Text_Answer4;
        TextField Text_cureectAns;
        HBox bar;


        ComboBox<String> comboBox;
        ComboBox<String> comboBox_num;
        ComboBox<String> comboBox_team;

        public AddQuestion(Stage stage)
        {
            try {
                initQuestionManager( stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void initQuestionManager(Stage stage) throws Exception{


            GridPane pane = new GridPane();
            pane.setPadding(new Insets(10,10,10,10));
            pane.setVgap(10);
            pane.setHgap(10);

            BackgroundImage myB = new BackgroundImage(new Image("res/EmptyBackground.jpeg",550,600,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            pane.setBackground(new Background(myB));

            Label l_Difficulty = new Label("Difficulty:");
            l_Difficulty.setMaxSize(60,35);
            l_Difficulty.setMinSize(60,35);
            l_Difficulty.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Difficulty,3,13);


            ObservableList<String> options = FXCollections.observableArrayList("Easy","Hard");
            comboBox = new ComboBox<>(options);
            comboBox.setPromptText("Choose Difficulty");
            comboBox.setMaxSize(350,35);
            comboBox.setMinSize(350,35);
            comboBox.setStyle("-fx-background-color: yellow; -fx-text-fill: white ; -fx-font-weight: bold; -fx-font-size: 18 " );
            GridPane.setConstraints(comboBox,4,13);


            Label l_Quest_Num = new Label("Question Num:");
            l_Quest_Num.setMaxSize(60,35);
            l_Quest_Num.setMinSize(60,35);
            l_Quest_Num.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Quest_Num,3,14);


            ObservableList<String> options1 = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14");



            ////////////////////
            comboBox_num = new ComboBox<>(options1);
            comboBox_num.setPromptText("Question number");
            comboBox_num.setMaxSize(350,35);
            comboBox_num.setMinSize(350,35);
            comboBox_num.setStyle("-fx-background-color: yellow; -fx-text-fill: white ; -fx-font-weight: bold; -fx-font-size: 18 " );
            GridPane.setConstraints(comboBox_num,4,14);
            //  comboBox_num.setOnAction(e->System.out.println(comboBox_num.getValue()));











            Label l_Team = new Label("Team:");
            l_Team.setMaxSize(60,35);
            l_Team.setMinSize(60,35);
            l_Team.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Team,3,15);


            ObservableList<String> options2 = FXCollections.observableArrayList("Zebra","Hedgehog","Giraff");
            comboBox_team = new ComboBox<>(options2);
            comboBox_team.setPromptText("Team Name");
            comboBox_team.setMaxSize(350,35);
            comboBox_team.setMinSize(350,35);
            comboBox_team.setStyle("-fx-background-color: yellow; -fx-text-fill: white ; -fx-font-weight: bold; -fx-font-size: 18 " );
            GridPane.setConstraints(comboBox_team,4,15);


            Label l_Question = new Label("Question:");
            l_Question.setMaxSize(60,35);
            l_Question.setMinSize(60,35);
            l_Question.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Question,3,16);

            TextField QuestText = new TextField("");
            QuestText.setMaxSize(350,35);
            QuestText.setMinSize(350,35);
            QuestText.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(QuestText,4,16);








            Label l_Answer1 = new Label("Answer 1:");
            l_Answer1.setMaxSize(60,35);
            l_Answer1.setMinSize(60,35);
            l_Answer1.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer1,3,17);

            TextField Text_Answer1 = new TextField("");
            Text_Answer1.setMaxSize(350,35);
            Text_Answer1.setMinSize(350,35);
            Text_Answer1.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer1,4,17);

            Label l_Answer2 = new Label("Answer 2:");
            l_Answer2.setMaxSize(60,35);
            l_Answer2.setMinSize(60,35);
            l_Answer2.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer2,3,18);

            TextField Text_Answer2 = new TextField("");
            Text_Answer2.setMaxSize(350,35);
            Text_Answer2.setMinSize(350,35);
            Text_Answer2.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer2,4,18);

            Label l_Answer3 = new Label("Answer 3:");
            l_Answer3.setMaxSize(60,35);
            l_Answer3.setMinSize(60,35);
            l_Answer3.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer3,3,19);

            TextField Text_Answer3 = new TextField("");
            Text_Answer3.setMaxSize(350,35);
            Text_Answer3.setMinSize(350,35);
            Text_Answer3.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer3,4,19);

            Label l_Answer4 = new Label("Answer 4:");
            l_Answer4.setMaxSize(60,35);
            l_Answer4.setMinSize(60,35);
            l_Answer4.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer4,3,20);

            TextField Text_Answer4 = new TextField("");
            Text_Answer4.setMaxSize(350,35);
            Text_Answer4.setMinSize(350,35);
            Text_Answer4.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer4,4,20);

            Label l_curect_Ans = new Label("curect Ans");
            l_curect_Ans.setMaxSize(60,35);
            l_curect_Ans.setMinSize(60,35);
            l_curect_Ans.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_curect_Ans,3,21);

            TextField Text_cureectAns = new TextField("");
            Text_cureectAns.setMaxSize(100,35);
            Text_cureectAns.setMinSize(100,35);
            Text_cureectAns.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_cureectAns,4,21);

            HBox bar = new HBox();


            Button knapp5 = new Button("Back");
            // knapp5.setStyle("-fx-background-color: white");
            knapp5.setMaxSize(65,35 );
            knapp5.setMinSize(65,35);
            knapp5.setOnAction(e->new StartGame(stage) );
            bar.getChildren().addAll(knapp5);


            GridPane.setConstraints(bar,4,23);














            pane.getChildren().addAll(l_Difficulty,comboBox,l_Quest_Num,comboBox_num, l_Team,comboBox_team,l_Question,QuestText,l_Answer1,Text_Answer1,l_Answer2,Text_Answer2,l_Answer3,Text_Answer3,l_Answer4,Text_Answer4,l_curect_Ans,Text_cureectAns,bar);



            // GridPane.setConstraints(comboBox,4,21);

            //   pane.getChildren().addAll(comboBox,button,nameText);
            Scene scene = new Scene(pane, 550,600);
            stage.setScene(scene);

            stage.show();





        }
    }


