package View;

import Model.Game;
import Model.SysData;
import Model.TmpGhost;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 * class that used for messages to the user
 *
 */
public class PopUpLogic {

	static JFXComboBox combo9,combo2,combo7;
    static PopUpLogic instance;
	static JFXTextField player1; // name of player 1
	static JFXTextField player2; // name of player 2
	static int numOfPlayers = 0; // field that will say how many players are participate in the game
	static boolean isAnswerCorrect = false; //flag to know how to update the score for pacman
	static String correctAnswer;
	static int indexOfQuestion;
 	boolean flag= false;
 	static int Player1SizeArray=0,Player2SizeArray=0;
 	static boolean isTMPGhostCheetAppears = false;


    public static PopUpLogic getInstance() {

        if (instance == null)
            instance = new PopUpLogic();

        return  instance;
    }
/**
 * pop up that show question
 */
    public void ShowQuestion(){

		java.net.URL imgURL = getClass().getResource("/res/download.jpg");
    	ImageIcon icon =new ImageIcon(imgURL);
     	Random rand = new Random();

		indexOfQuestion=rand.nextInt(SysData.createInstance().getQuestions().size());
 		List<String> a=new ArrayList<>();
 		a= SysData.instance.getQuestions().get(indexOfQuestion).getAnswers();
     	String answer= a.get(0);
    	String answer1= a.get(1);
    	String answer2=	"";
    	String answer3=	"";
		if(a.size()>=3) {
			answer2="3. "+a.get(2);
		}
    	if(a.size()==4) {
    	  answer3="4. "+a.get(3);
    	}
    	if (Game.getPlayerIndex()==1) {
    		Player1SizeArray=a.size();
    	}
    	else {
    		Player2SizeArray=a.size();

    	}
    	JLabel label = new JLabel("<html>Question Candy: <br>"  +  SysData.instance.getQuestions().get(indexOfQuestion).getquestion()+" <br>"+" <br>" +"1. "+ answer+
    			" <br>"+" <br>"+"2. "+ answer1+
    			" <br>"+" <br>"+""+ answer2+
    			" <br>"+" <br>"+ answer3+" <br>"+
    			"</html>");
    	UIManager.put("OptionPane.minimumSize",new Dimension(150,150));
		ViewLogic.getInstance().CancelTimer();
    	label.setFont(new Font("Lucida Console", Font.BOLD, 13));
        JOptionPane.showMessageDialog(null,label,"Question",JOptionPane.QUESTION_MESSAGE,icon);
		ViewLogic.getInstance().getTimer();
	 	   if(!Game.pacmans.get(Game.getPlayerIndex()).isQuestionAppeared()) {
			   for (int i = 0; i < a.size(); i++) {
				   Game.mazes.get(Game.getPlayerIndex()).ghosts.add(new TmpGhost(
				   		Game.mazes.get(Game.getPlayerIndex()).ghosts.get(0).x,  Game.mazes.get(Game.getPlayerIndex()).ghosts.get(0).y));

			   }
			   Game.pacmans.get(Game.getPlayerIndex()).isQuestionAppeared(true);

		   }

    }

	/**
	 * Pop up that shows the result of the most recent question that appeared
	 * @param ghost
	 * @return
	 */
	public static int showQuestionResult(TmpGhost ghost){
    	if(SysData.instance.getQuestions().get(indexOfQuestion).getCorrect_ans().equals(String.valueOf(ghost.index))){
			UIManager.put("OptionPane.minimumSize",new Dimension(120,120));
			JOptionPane.showMessageDialog(null, "Correct Answer!","Correct",JOptionPane.INFORMATION_MESSAGE);
			isAnswerCorrect = true;
			return SysData.instance.getQuestions().get(indexOfQuestion).getlevel();
		}
		else
		{
			UIManager.put("OptionPane.minimumSize",new Dimension(120,120));
			JOptionPane.showMessageDialog(null, "Wrong Answer!","Wrong",JOptionPane.INFORMATION_MESSAGE);
			isAnswerCorrect = false;
			return SysData.instance.getQuestions().get(indexOfQuestion).getlevel();
		}
	}


	public static boolean isAnswerCorrect(){
    	return isAnswerCorrect;
	}

/**
 * pop up that show that the game is over
 * @param score
 */
public void ShowGameOver(int score){
	ViewLogic.getInstance().CancelTimer();
	String winnerName="";
	String Losser= "";

	java.net.URL imgURL = getClass().getResource("/res/winner-gif-13.gif");
	ImageIcon icon =new ImageIcon(imgURL);
	icon.getImage().getScaledInstance(5,5,Image.SCALE_AREA_AVERAGING);

	if(Game.pacmans.size() == 1) {
		JLabel label2 = new JLabel("<html> Final score: " +Game.pacmans.get(0).getScore()+"</html>" );

		label2.setFont(new Font("Lucida Console", Font.BOLD, 20));


		UIManager.put("OptionPane.minimumSize",new Dimension(200,200));
		JOptionPane.showMessageDialog(null, label2, "Podium ",JOptionPane.INFORMATION_MESSAGE,icon);
		synchronized ( Game.getInstance()) {
			Game.getInstance().notify();
		}
		ViewLogic.getInstance().setTimerCounting();
	}
	else if(Game.pacmans.size() == 2){
		if(Game.pacmans.get(0).getScore()>Game.pacmans.get(1).getScore())
		{
			winnerName= "" + Game.pacmans.get(0).getPacmanName() +"with score " + Game.pacmans.get(0).getScore();
			Losser = ""+ Game.pacmans.get(1).getPacmanName()+"with score " + Game.pacmans.get(1).getScore();;
		}
		else{
			winnerName= "" + Game.pacmans.get(1).getPacmanName()+ " with score " + Game.pacmans.get(1).getScore();;
			Losser = ""+ Game.pacmans.get(0).getPacmanName()+" with score " + Game.pacmans.get(0).getScore();;
		}


		JLabel label2 = new JLabel("<html> And the Winner is: \n" + winnerName +" with score" + "<br>" +" <br>"+" <br>" + Losser+  " You lose!!!</html>" );

		label2.setFont(new Font("Lucida Console", Font.BOLD, 20));


		UIManager.put("OptionPane.minimumSize",new Dimension(200,200));
		JOptionPane.showMessageDialog(null, label2, "Podium ",JOptionPane.INFORMATION_MESSAGE,icon);
		synchronized ( Game.getInstance()) {
			Game.getInstance().notify();
		}

	}
	ViewLogic.getInstance().setTimerCounting();

}


	/**
	 * Pop up that shows each time player turn
	 */
	public void showPlayerTurn(){
		UIManager.put("OptionPane.minimumSize",new Dimension(120,120));
	Game g=Game.getInstance();
		g.setFlag(true);

		JOptionPane.showMessageDialog(null,
				"Player " + Game.pacmans.get(Game.getPlayerIndex()).getPacmanName() + " turn!", "Pause", JOptionPane.INFORMATION_MESSAGE);

synchronized ( g) {
			g.notify();
		}
	}
    /**
     * pop up that show if we want to exit from the game
     * if the param is true its mean get out from all of the game
     * if false its  just go out from the board game and
     *	we need to freeze the ghosts before the popup
     * @param
     * @return
     */
    public int ShowEXit(boolean freeze ){
    	int g=4;
    	if (!freeze) {
          	Game.getInstance().setFlag(true);
           	ViewLogic.getInstance().CancelTimer();
 			 g =  JOptionPane.showConfirmDialog(null, "Would you like to exit from the game?", "Exit?", JOptionPane.YES_NO_OPTION);
 			synchronized ( Game.getInstance()) {
						   Game.getInstance().notify();
 			}
 			 if(g==1) {// the user didnt leave the game
 				 ViewLogic.getInstance().getTimer();

 			 }
  			 else {// the user leave the game we need to init the TimerSecond
 				 ViewLogic.getInstance().setTimerCounting();
 			 }

    	}

    	else {

          	 g =  JOptionPane.showConfirmDialog(null, "Would you like to exit from the game?", "Exit?", JOptionPane.YES_NO_OPTION);
    	}

     	return g;
    }
    /**
     * pop up with a message that the game is pause
     * occur when the player press on space keyboard
     */

    public void pauseGame(){
        UIManager.put("OptionPane.minimumSize",new Dimension(120,120));
        Game g=Game.getInstance();
       	g.setFlag(true);
     	JOptionPane.showMessageDialog(null, "you stop the game ,press ok to continue  ","Pause",JOptionPane.INFORMATION_MESSAGE);
     	 synchronized ( g) {
			   g.notify();
     	 }


    }

	/**
	 * Pop up that shows the game type- one player or two players mode
	 * @param pane
	 */

	public void showGameType(StackPane pane){
        javafx.scene.control.Label field = new Label("Please choose how many players");
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		ObservableList<String> options = FXCollections.observableArrayList("One Player","Two Players");
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text("Let's start!"));
 		pane.autosize();
		JFXDialog dialog = new JFXDialog(pane,content, JFXDialog.DialogTransition.CENTER );
		JFXComboBox gameType=new JFXComboBox(options);
		gameType.setPromptText("How many players?");
		gameType.setOnAction(e -> chooseGameType(gameType.getValue().toString(), gridPane));


		GridPane.setConstraints(gameType,0,1);
		gridPane.getChildren().addAll(gameType);
		JFXButton button = new JFXButton("Start game!");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if(gameType.getValue() == null) {
					if (!flag) {
                    GridPane.setConstraints(field, 0, 0);
                    gridPane.getChildren().addAll(field);
                    flag=true;
					}
                }
				else {
					flag= false;
					gridPane.getChildren().removeAll();
					content.setBody(new Text("Please wait..."));
					button.setDisable(true);

					Timer timer = new Timer();

					timer.schedule(new TimerTask() {
						public void run() {
							new GameView();
							dialog.close();
						}
					}, 1);
				}
				}
		});



		GridPane.setConstraints(button,0,1);
 		content.setBody(gridPane);
		content.setActions(button);
		dialog.show();
	}

	public void setPacManColor(String comboBox1 )
	{

		switch (comboBox1) {
			case "Green":
			PacmanAnimation.path="/res/sprites/green.png";
				break;
			case "colorful":
				PacmanAnimation.path="/res/sprites/colorful.png";
				break;
			case "Purple":
				PacmanAnimation.path="/res/sprites/pink.png";
				break;
		}


	}

	public void setPacManMap(String comboBox1 )
	{

		switch (comboBox1) {
			case "Game map":
				Game.path1="/res/map/map.png";
				break;
			case "Dev map":
				Game.path1="/res/map/map2.png";
				break;
			case "SW map":
				Game.path1="/res/map/map1.png";
				break;
		}


	}
	/**
	 * Update the pop up that responsible of the game type.
	 * It will give to set nick name and pacman color for each player
	 * @param option
	 * @param gridPane
	 * @return
	 */
	public GridPane chooseGameType(String option,GridPane gridPane) {

		switch (option) {
			case "One Player": {
				if (gridPane.getChildren().contains(player2) ) {
					gridPane.getChildren().removeAll(player2, combo2, combo9);
					System.out.print("aa");
				}
				player1 = new JFXTextField("Nickname player 1");
				GridPane.setConstraints(player1, 0, 2);

				ObservableList<String> comboBox1 = FXCollections.observableArrayList("Green", "colorful", "Purple");
				combo2 = new JFXComboBox(comboBox1);
				combo2.setPromptText("Pac-Man color");
				GridPane.setConstraints(combo2, 3, 1);
				combo2.setOnAction(e -> setPacManColor(combo2.getValue().toString()));

				ObservableList<String> comboBoxpl = FXCollections.observableArrayList("Game map", "Dev map", "SW map");
				combo9 = new JFXComboBox(comboBoxpl);
				combo9.setPromptText("Choose map");
				GridPane.setConstraints(combo9, 3, 2);
				combo9.setOnAction(e -> setPacManMap(combo9.getValue().toString()));


				gridPane.getChildren().addAll(player1, combo2, combo9);
				numOfPlayers = 1;
				break;
			}

			case "Two Players": {

				if (gridPane.getChildren().contains(player1) ){
					gridPane.getChildren().removeAll(player1, combo2, combo9);
					System.out.print("SS");
				}
				ObservableList<String> comboBox = FXCollections.observableArrayList("Green", "colorful", "Purple");
				combo2 = new JFXComboBox(comboBox);
				combo2.setPromptText("Pac Man color");
				GridPane.setConstraints(combo2, 3, 2);
				combo2.setOnAction(e -> setPacManColor(combo2.getValue().toString()));


				ObservableList<String> comboBoxp = FXCollections.observableArrayList("Game map", "Dev map", "SW map");
				combo9 = new JFXComboBox(comboBoxp);
				combo9.setPromptText("Choose map");
				GridPane.setConstraints(combo9, 3, 3);
				combo9.setOnAction(e -> setPacManMap(combo9.getValue().toString()));


				player1 = new JFXTextField("Nickname player 1");
				GridPane.setConstraints(player1, 0, 2);
				player2 = new JFXTextField("Nickname player 2");
				GridPane.setConstraints(player2, 0, 3);
				gridPane.getChildren().addAll(player1, player2, combo2, combo9);
				numOfPlayers = 2;
				break;
			}
		}

		return gridPane;
	}

	public static String getPlayer1() {
		return player1.getText();
	}


	public static String getPlayer2() {
		return player2.getText();
	}

	public static int getNumOfPlayers() {
		return numOfPlayers;
	}


/**
 * pop up that show that there are a mistake
 */
    public void QuestionMistake(){

        UIManager.put("OptionPane.minimumSize",new Dimension(120,120));

     	JOptionPane.showMessageDialog(null, "you didnt fill all the fields correctly ","Error",JOptionPane.ERROR_MESSAGE);

    }
/**
 * pop up that show that the question was added
 */
    public void QuestionAdded(){

        UIManager.put("OptionPane.minimumSize",new Dimension(120,120));

     	JOptionPane.showMessageDialog(null, "you added the question ","Add",JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * pop up that show that the question was  Edit
     */
        public void QuestionEdit(){

            UIManager.put("OptionPane.minimumSize",new Dimension(120,120));

         	JOptionPane.showMessageDialog(null, "you Edit the question ","Edit",JOptionPane.INFORMATION_MESSAGE);

        }


        /**
         * pop up that show that the question was  remove
         */
            public void QuestionRemove(){

                UIManager.put("OptionPane.minimumSize",new Dimension(120,120));

             	JOptionPane.showMessageDialog(null, "you Removed the question ","Remove",JOptionPane.INFORMATION_MESSAGE);

            }



 	public static int getPlayer1SizeArra() {
 		return Player1SizeArray;
 	}
 	public static int getPlayer2SizeArra() {
 		return Player2SizeArray;
 	}
 	public static boolean isTMPGhostCheetAppears(){return isTMPGhostCheetAppears;}
	public static void isTMPGhostCheetAppears(boolean isAppears){isTMPGhostCheetAppears = isAppears;}
	public int getindexOfQuestion(){return indexOfQuestion;}
}
