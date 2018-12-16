package View;

import Model.Question;
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

import java.util.ArrayList;
import java.util.List;

/**
 * The view for adding a question to the game
 */
public class AddQuestion {



        Button undoBut,redoBut,saveBut ;
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
        TextField Text_Answer1,Text_Answer2,Text_Answer3,Text_Answer4 ;
        HBox bar;
        TextField teamName;
        int saveFiles = 0, currentArticle = 0;

        ComboBox<String> comboBox,cureectAns;
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


            ObservableList<String> options = FXCollections.observableArrayList("Easy","Normal","Hard");

            comboBox = new ComboBox<>(options);
            comboBox.setPromptText("Choose Difficulty");
            comboBox.setMaxSize(350,35);
            comboBox.setMinSize(350,35);
            comboBox.setStyle("-fx-background-color: yellow; -fx-text-fill: white ; -fx-font-weight: bold; -fx-font-size: 18 " );
            GridPane.setConstraints(comboBox,4,13);


            Label l_Team = new Label("Team:");
            l_Team.setMaxSize(60,35);
            l_Team.setMinSize(60,35);
            l_Team.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Team,3,15);


              teamName = new TextField("");
              teamName.setMaxSize(350,35);
             teamName.setMinSize(350,35);
             teamName.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(teamName,4,15);


            Label l_Question = new Label("Question:");
            l_Question.setMaxSize(60,35);
            l_Question.setMinSize(60,35);
            l_Question.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Question,3,16);

             QuestText = new TextField("");
            QuestText.setMaxSize(350,35);
            QuestText.setMinSize(350,35);
            QuestText.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(QuestText,4,16);


            Label l_Answer1 = new Label("Answer 1:");
            l_Answer1.setMaxSize(60,35);
            l_Answer1.setMinSize(60,35);
            l_Answer1.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer1,3,17);


             Text_Answer1 = new TextField("");
            Text_Answer1.setMaxSize(350,35);
            Text_Answer1.setMinSize(350,35);
            Text_Answer1.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer1,4,17);

            Label l_Answer2 = new Label("Answer 2:");
            l_Answer2.setMaxSize(60,35);
            l_Answer2.setMinSize(60,35);
            l_Answer2.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer2,3,18);

             Text_Answer2 = new TextField("");
            Text_Answer2.setMaxSize(350,35);
            Text_Answer2.setMinSize(350,35);
            Text_Answer2.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer2,4,18);

            Label l_Answer3 = new Label("Answer 3:");
            l_Answer3.setMaxSize(60,35);
            l_Answer3.setMinSize(60,35);
            l_Answer3.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer3,3,19);

             Text_Answer3 = new TextField("");
            Text_Answer3.setMaxSize(350,35);
            Text_Answer3.setMinSize(350,35);
            Text_Answer3.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer3,4,19);

            Label l_Answer4 = new Label("Answer 4:");
            l_Answer4.setMaxSize(60,35);
            l_Answer4.setMinSize(60,35);
            l_Answer4.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_Answer4,3,20);

             Text_Answer4 = new TextField("");
            Text_Answer4.setMaxSize(350,35);
            Text_Answer4.setMinSize(350,35);
            Text_Answer4.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(Text_Answer4,4,20);

            Label l_curect_Ans = new Label("curect Ans");
            l_curect_Ans.setMaxSize(60,35);
            l_curect_Ans.setMinSize(60,35);
            l_curect_Ans.setStyle("-fx-background-color: yellow");
            GridPane.setConstraints(l_curect_Ans,3,21);


             ObservableList<String> Text_cureectAns = FXCollections.observableArrayList("1","2","3","4");

              cureectAns = new ComboBox<>(Text_cureectAns);
              cureectAns.setPromptText("Choose answer number");
              cureectAns.setMaxSize(350,35);
              cureectAns.setMinSize(350,35);
              cureectAns.setStyle("-fx-background-color: yellow; -fx-text-fill: white ; -fx-font-weight: bold; -fx-font-size: 18 " );

             GridPane.setConstraints(cureectAns,4,21);

            HBox bar = new HBox();


            Button knapp5 = new Button("Back");
            knapp5.setMaxSize(65,35 );
            knapp5.setMinSize(65,35);
            knapp5.setOnAction(e->
            callBack(stage));

            saveBut = new Button("Add Question");
            saveBut.setMaxSize(100,35 );
            saveBut.setMinSize(100,35);
            saveBut.setOnAction(e->handleAddQuestions("Add"));

            undoBut = new Button("Undo");
            undoBut.setMaxSize(65,35 );
            undoBut.setMinSize(65,35);
            undoBut.setOnAction(e->new StartGame(stage) );
            undoBut.setOnAction(e->handleAddQuestions("Undo"));
            undoBut.setDisable(true); // will disable the Button

            redoBut = new Button("Redo");
            redoBut.setMaxSize(65,35 );
            redoBut.setMinSize(65,35);
            redoBut.setOnAction(e->new StartGame(stage) );
            redoBut.setOnAction(e->handleAddQuestions("Redo"));
            redoBut.setDisable(true); // will disable the Button



            bar.getChildren().addAll(knapp5,saveBut,undoBut,redoBut);

            GridPane.setConstraints(bar,4,23);

            pane.getChildren().addAll(l_Difficulty,comboBox, l_Team,teamName,l_Question,QuestText,l_Answer1,Text_Answer1,l_Answer2,Text_Answer2,l_Answer3,Text_Answer3,l_Answer4,Text_Answer4,l_curect_Ans,cureectAns,bar);

            Scene scene = new Scene(pane, 550,600);
            stage.setScene(scene);

            stage.show();

        }

        /**
         * checking the validation of the question
         * and add it to the Question list
         * get pop up depend if the added success or not
         */
        public void handleAddQuestions(String choose) {


        	if(choose.equals("Add")) {
	        	int level = 0;
	            List<String> in_answers = new ArrayList<>() ;
	            if (comboBox.getValue()!=null&&cureectAns.getValue()!=null) {/// check first if the user choose from the comboBox

	            		switch (comboBox.getValue()) {//convert from text to number
							case "Normal":
								level = 1;
								break;
							case "Easy":
								level = 0;
								break;
							case "Hard":
								level = 2;
								break;

						}


		  	            if (!Text_Answer1.getText().isEmpty()) {///adding the answer to the list
		 	 	           in_answers.add(Text_Answer1.getText());

		 	            }
		 	           if (!Text_Answer2.getText().isEmpty()) {///adding the answer to the list
		 	 	           in_answers.add(Text_Answer2.getText());

		 	            }if (!Text_Answer3.getText().isEmpty()) {///adding the answer to the list
		  	 	           in_answers.add(Text_Answer3.getText());

		  	            }if (!Text_Answer4.getText().isEmpty()) {///adding the answer to the list
		  	 	           in_answers.add(Text_Answer4.getText());

		  	            }

		  	            boolean flag=true;//checking if there are a answer to the answer number that the user choose
		  	          switch (cureectAns.getValue()) {
						case "1":
							if (Text_Answer1.getText().isEmpty())
								flag=false;
							break;
						case "2":
							if (Text_Answer2.getText().isEmpty())
								flag=false;
							break;
						case "3":
							if (Text_Answer3.getText().isEmpty())
								flag=false;
							break;
						case "4":
							if (Text_Answer4.getText().isEmpty())
								flag=false;
							break;
						}




			       if( flag	&& SysData.instance.addQuestion(QuestText.getText(),
			    		   level, teamName.getText(), in_answers,  cureectAns.getValue()))
			       {
			    	   PopUpLogic.instance.QuestionAdded();
			    	   Text_Answer1.setText("");
			    	   Text_Answer2.setText("");
			    	   Text_Answer3.setText("");
			    	   Text_Answer4.setText("");
			    	   QuestText.setText("");
			    	   teamName.setText("");
			    	// saveFiles monitors how many articles are saved
			       // currentArticle monitors the current article displayed

			    	   saveFiles++;
			    	   currentArticle++;
			           undoBut.setDisable(false); // will enable it again


			       }
			       else {
			    	   PopUpLogic.instance.QuestionMistake();
			       }

	            }else {
	 	    	   PopUpLogic.instance.QuestionMistake();
	             }
	        }

        else if (choose.equals("Undo")) {
            if(currentArticle >=1){//
            	// Decrement to the current question displayed
            		currentArticle--;
               // Get the older question saved and display it in JTextArea
            		  Question q= SysData.instance.UndoRedo.get(currentArticle);
              		// Get the newer question saved and display it
              		  SetVisibleQuestion(q);




            redoBut.setDisable(false);// will enable it again
           } else {

             // Don't allow user to click Undo
              undoBut.setDisable(true); // will disable the Button


           }

        }
        else if (choose.equals("Redo")) {
        	  if((saveFiles-1 ) > currentArticle){
        		// Increment to the current article displayed

        		  currentArticle++;

        		  Question q= SysData.instance.UndoRedo.get(currentArticle);
        		// Get the newer question saved and display it
        		  SetVisibleQuestion(q);
        		  undoBut.setDisable(false);// will enable it again

        	  }
        	  else {
        		// Don't allow user to click Undo
        		  redoBut.setDisable(true); // will disable the Button
        	  }
        }


     }

        /**
         * set the question on the screen
         * @param q
         */
        public void SetVisibleQuestion(Question q) {

    		switch (q.getlevel()) {//convert from number  to text
			case 1:
				comboBox.setValue("Normal");
				break;
			case 2:
				comboBox.setValue("Easy");
				break;
			case 3:
				comboBox.setValue("Hard");
				break;

    		}
    		QuestText.setText(q.getquestion());
    		teamName.setText(q.getTeam());
    		cureectAns.setValue(q.getCorrect_ans());
    		List<String> answer=new ArrayList<String>();
    		answer= q.getAnswers();

    		 Text_Answer1.setText(answer.get(0));
	    	 Text_Answer2.setText(answer.get(1));
	    	 if(answer.size()==3) {
	    	 Text_Answer3.setText(answer.get(2));
	    	 }
	    	 else if( answer.size()>3){
		    	 Text_Answer4.setText(answer.get(3));
	    	 }
        }
        /**
         *  when the user press back array he will came here
         *  first will be a merege betweem the 2 array ( the first line)
         *  and then go back to the last screem
         * @param SameStage
         */
       public void callBack(Stage SameStage) {
    	   SysData.instance.UpdateQuestionArray(currentArticle) ;
            new QuestionManager(SameStage);
       }
}
