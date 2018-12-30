package View;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Model.SysData;
import Model.Question;
 import Model.QuestionResultsFromJSON;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager implements EventHandler {

    // Image image = new Image("res/first.PNG");
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
    TextField Text_Difficulty;
    TextField Text_Team;
    TextField QuestText;
    TextField Text_Answer1;
    TextField Text_Answer2;
    TextField Text_Answer3;
    TextField Text_Answer4;
    TextField Text_cureectAns;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    Stage stage;
    Scene scene;
    HBox bar;
       static int index = 0;
    Button knapp3 = new Button(">>");
    Button knapp1 = new Button("<<");


    ComboBox<String> comboBox;
    ComboBox<String> comboBox_num;
    ComboBox<String> comboBox_team;

    public QuestionManager(Stage stage)
    {
        try {
            initQuestionManager(stage);
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


//
        Text_Difficulty = new TextField(""+ SysData.instance.getQuestions().get(index).getlevel());
        Text_Difficulty.setMaxSize(350,35);
        Text_Difficulty.setMinSize(350,35);
        Text_Difficulty.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(Text_Difficulty,4,13);

        Text_Team = new TextField(""+ SysData.instance.getQuestions().get(index).getTeam());
        Text_Team.setMaxSize(350,35);
        Text_Team.setMinSize(350,35);
        Text_Team.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(Text_Team,4,14);

        QuestText = new TextField(SysData.instance.getQuestions().get(index).getquestion());
        QuestText.setMaxSize(350,35);
        QuestText.setMinSize(350,35);
        QuestText.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(QuestText,4,16);

        Text_cureectAns = new TextField(SysData.instance.getQuestions().get(index).getCorrect_ans());
        Text_cureectAns.setMaxSize(100,35);
        Text_cureectAns.setMinSize(100,35);
        Text_cureectAns.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(Text_cureectAns,4,21);

        getAnswerByIndex(0);


        Label l_Team = new Label("Team:");
        l_Team.setMaxSize(60,35);
        l_Team.setMinSize(60,35);
        l_Team.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(l_Team,3,14);

        Label l_Question = new Label("Question:");
        l_Question.setMaxSize(60,35);
        l_Question.setMinSize(60,35);
        l_Question.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(l_Question,3,16);


        Label l_Answer1 = new Label("Answer 1:");
        l_Answer1.setMaxSize(60,35);
        l_Answer1.setMinSize(60,35);
        l_Answer1.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(l_Answer1,3,17);

        Text_Answer1 = new TextField(answer1);
        Text_Answer1.setMaxSize(350,35);
        Text_Answer1.setMinSize(350,35);
        Text_Answer1.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(Text_Answer1,4,17);

        Label l_Answer2 = new Label("Answer 2:");
        l_Answer2.setMaxSize(60,35);
        l_Answer2.setMinSize(60,35);
        l_Answer2.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(l_Answer2,3,18);

        Text_Answer2 = new TextField(answer2);
        Text_Answer2.setMaxSize(350,35);
        Text_Answer2.setMinSize(350,35);
        Text_Answer2.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(Text_Answer2,4,18);

        Label l_Answer3 = new Label("Answer 3:");
        l_Answer3.setMaxSize(60,35);
        l_Answer3.setMinSize(60,35);
        l_Answer3.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(l_Answer3,3,19);

        Text_Answer3 = new TextField(answer3);
        Text_Answer3.setMaxSize(350,35);
        Text_Answer3.setMinSize(350,35);
        Text_Answer3.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(Text_Answer3,4,19);

        Label l_Answer4 = new Label("Answer 4:");
        l_Answer4.setMaxSize(60,35);
        l_Answer4.setMinSize(60,35);
        l_Answer4.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(l_Answer4,3,20);

        Text_Answer4 = new TextField(answer4);
        Text_Answer4.setMaxSize(350,35);
        Text_Answer4.setMinSize(350,35);
        Text_Answer4.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(Text_Answer4,4,20);

        Label l_curect_Ans = new Label("curect Ans");
        l_curect_Ans.setMaxSize(60,35);
        l_curect_Ans.setMinSize(60,35);
        l_curect_Ans.setStyle("-fx-background-color: yellow");
        GridPane.setConstraints(l_curect_Ans,3,21);

        HBox bar = new HBox();
        bar.setSpacing(10);
         knapp1.setMaxSize(65,35 );
        knapp1.setMinSize(65,35);
        knapp1.setOnAction(this);
        knapp1.setDisable(true);
        Button knapp2 = new Button("Edit");
         knapp2.setMaxSize(65,35 );
        knapp2.setMinSize(65,35);
        knapp2.setOnAction(e-> EditQuestion()  );
        knapp3.setMaxSize(65,35 );
        knapp3.setMinSize(65,35);
        knapp3.setOnAction(this);

        
        Button knapp5 = new Button("remove");
        knapp5.setMaxSize(65,35 );
        knapp5.setMinSize(65,35);
        knapp5.setOnAction(e->RemoveQuestion());
        Button knapp4 = new Button("Add");
        knapp4.setMaxSize(65,35 );
        knapp4.setMinSize(65,35);
        knapp4.setOnAction(e->new AddQuestion(stage) );


        Button B_back = new Button("Back");
        B_back.setMaxSize(65,35);
        B_back.setMinSize(65,35);
        B_back.setOnAction(e->new StartGame(stage) );

        bar.getChildren().addAll(knapp5,B_back,knapp1, knapp2,knapp3,knapp4 );


        pane.getChildren().addAll( Text_Team,Text_Difficulty,l_Difficulty, l_Team,l_Question,QuestText,l_Answer1,Text_Answer1,l_Answer2,Text_Answer2,l_Answer3,Text_Answer3,l_Answer4,Text_Answer4,l_curect_Ans,Text_cureectAns);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 70, 10, 50 ));
        vBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.getChildren().addAll(pane,bar);

        scene = new Scene(vBox, 550,600);
        stage.setScene(scene);

        stage.show();

    }
    
    private void RemoveQuestion() {
     	 if(SysData.instance.removeQuestion(index)) {
     		 PopUpLogic.instance.QuestionRemove();
     		 SetTextToEmpty();

     	 }
     	 else
     		PopUpLogic.getInstance().QuestionMistake();

    }
    
    private void EditQuestion() {

	if( Text_cureectAns.getText()!=null&&!Text_cureectAns.getText().isEmpty() 
		&& QuestText.getText()!=null&&!QuestText.getText().isEmpty()  &&
		Text_Team.getText()!=null&&!Text_Team.getText().isEmpty()&&
		Text_Difficulty.getText()!=null&&!Text_Difficulty.getText().isEmpty()&&
		Text_Answer1.getText()!=null&&!Text_Answer1.getText().isEmpty()&&
		Text_Answer2.getText()!=null&&!Text_Answer2.getText().isEmpty()) {
 	
		Question q = SysData.instance.getQuestions().get(index);
    	q.setCorrect_ans(Text_cureectAns.getText());
    	q.setquestion(QuestText.getText());
    	q.setTeam(Text_Team.getText());
    	q.setlevel(Integer.valueOf(Text_Difficulty.getText()));
    	List<String> answer=new ArrayList<String>();
    	answer.add(Text_Answer1.getText());
    	answer.add(Text_Answer2.getText());
  		if(Text_Answer3.getText()!=null&&!Text_Answer3.getText().isEmpty()) {
   	    	answer.add(Text_Answer3.getText());
    	 }
  		if(Text_Answer4.getText()!=null&&!Text_Answer4.getText().isEmpty()) {
    	    	answer.add(Text_Answer4.getText());
    	 }
    	q.setAnswers(answer);
    	PopUpLogic.getInstance().QuestionEdit();
    	SetTextToEmpty();
  	   	
	}
	else {
		PopUpLogic.getInstance().QuestionMistake();
	}
     }
    

    private void getAnswerByIndex(int index) {
        List<String> a= SysData.instance.getQuestions().get(index).getAnswers();
        answer1= a.get(0);
        answer2= a.get(1);
        answer3="";
        if(a.size()>=3)
        answer3 =a.get(2);
        answer4="";
        if(a.size()==4) {
            answer4 = a.get(3);
        }
    }
    private void changeIndex(int index) {
        Text_Team.setText("" + SysData.instance.getQuestions().get(index).getTeam());
        QuestText.setText(SysData.instance.getQuestions().get(index).getquestion());
        Text_Difficulty.setText(""+ SysData.instance.getQuestions().get(index).getlevel());
        this.getAnswerByIndex(index);
        Text_Answer1.setText(answer1);
        Text_Answer2.setText(answer2);
        Text_Answer3.setText(answer3);
        Text_Answer4.setText(answer4);
        Text_cureectAns.setText(SysData.instance.getQuestions().get(index).getCorrect_ans());
    }

    @Override
    public void handle(Event event) {

        if(event.getSource()==knapp3){

            if(index<SysData.instance.getQuestions().size()-1) {
                index++;
                this.changeIndex(index);
                knapp1.setDisable(false); // will  able the Button


            }
            else{
                index=index;
                this.changeIndex(index);
                knapp3.setDisable(true); // will disable the Button


            }


        }

        if(event.getSource()==knapp1){
            if(index!=0) {
                index = index - 1;
                this.changeIndex(index);
                knapp3.setDisable(false); // will  able the Button
            }
            else
            {
            	knapp1.setDisable(true); // will disable the Button
                index = index;
                this.changeIndex(index);

            }

        }


    }
    public void SetTextToEmpty() {
    	Text_Answer1.setText("");
    	Text_Answer2.setText("");
    	Text_Answer3.setText("");
    	Text_Answer4.setText("");
    	QuestText.setText("");
    	Text_Team.setText("");
    	Text_cureectAns.setText("");
  	   	Text_Difficulty.setText("");
    }
     
}
