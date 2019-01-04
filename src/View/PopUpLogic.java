package View;

import Model.Game;
import Model.Ghost;
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

    static PopUpLogic instance;
	static JFXTextField player1; // name of player 1
	static JFXTextField player2; // name of player 2
	static int numOfPlayers = 0; // field that will say how many players are participate in the game
	static boolean isAnswerCorrect = false; //flag to know how to update the score for pacman
	static String correctAnswer;
	static int indexOfQuestion;
 	boolean flag= false;
 	static int Player1SizeArray=0,Player2SizeArray=0;


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
     	indexOfQuestion = rand.nextInt(SysData.createInstance().getQuestions().size());
 		List<String> a=new ArrayList<>();
 		a= SysData.instance.getQuestions().get(indexOfQuestion).getAnswers();
     	String answer= a.get(0);
    	String answer1= a.get(1);
    	String answer2=	 a.get(2);
    	String answer3=	"";
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
    			" <br>"+" <br>"+"3. "+ answer2+
    			" <br>"+" <br>"+ answer3+" <br>"+
    			"</html>");
    	UIManager.put("OptionPane.minimumSize",new Dimension(150,150));
    	label.setFont(new Font("Lucida Console", Font.BOLD, 13));
        JOptionPane.showMessageDialog(null,label,"Question",JOptionPane.QUESTION_MESSAGE,icon);

	 	   if(!Game.pacmans.get(Game.getPlayerIndex()).isQuestionAppeared()) {
			   for (int i = 0; i < a.size(); i++) {
				   Game.mazes.get(Game.getPlayerIndex()).ghosts.add(new TmpGhost( 32*(i+4), 32*(i+4)));

			   }
			   Game.pacmans.get(Game.getPlayerIndex()).isQuestionAppeared(true);

		   }
    }

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

       	if(Game.pacmans.size() == 1) {
			UIManager.put("OptionPane.minimumSize", new Dimension(120, 120));
			JOptionPane.showMessageDialog(null, "                       Game over!!! \n" +
					" Your final score is " + score + " at " + ViewLogic.getInstance().GetTimeResults() + " minutes", "Game over", JOptionPane.INFORMATION_MESSAGE);
			ViewLogic.getInstance().setTimerCounting();
		}
		else if(Game.pacmans.size() == 2){
			UIManager.put("OptionPane.minimumSize", new Dimension(120, 120));
			JOptionPane.showMessageDialog(null, "                       Game over!!! \n" +
					 Game.pacmans.get(0).getPacmanName()+": Your final score is " + Game.pacmans.get(0).getScore() + " \n " +   Game.pacmans.get(1).getPacmanName()+" : Your final score is " + Game.pacmans.get(1).getScore(), "Game over", JOptionPane.INFORMATION_MESSAGE);
			ViewLogic.getInstance().setTimerCounting();
		}


    }

	/**
	 * Pop up that shows each time player turn
	 */
	public void showPlayerTurn(){

		UIManager.put("OptionPane.minimumSize",new Dimension(120,120));
	Game g=Game.getInstance();
		g.setFlag(true);
		ViewLogic.getInstance().CancelTimer();
		JOptionPane.showMessageDialog(null,
				"Player " + Game.pacmans.get(Game.getPlayerIndex()).getPacmanName() + " turn!", "Pause", JOptionPane.INFORMATION_MESSAGE);

	synchronized ( g) {
			g.notify();
		}
		ViewLogic.getInstance().getTimer();
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
          	System.out.println(	Game.getInstance().flag);
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
       	ViewLogic.getInstance().CancelTimer();
     	JOptionPane.showMessageDialog(null, "you stop the game ,press ok to continue  ","Pause",JOptionPane.INFORMATION_MESSAGE);
     	 synchronized ( g) {
			   g.notify();
     	 }
		ViewLogic.getInstance().getTimer();

    } 
    
    
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
							System.out.println(content.getBody().size());
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

	public GridPane chooseGameType(String option,GridPane gridPane){
		switch (option){
			case "One Player":
				if (gridPane.getChildren().contains(player2)){
					gridPane.getChildren().remove(player2);
				}
				player1 = new JFXTextField("Nickname player 1");
				GridPane.setConstraints(player1,0,2);
				gridPane.getChildren().addAll(player1);
				numOfPlayers =1;
				break;
			case "Two Players":
				player1 = new JFXTextField("Nickname player 1");
				GridPane.setConstraints(player1,0,2);
				player2 = new JFXTextField("Nickname player 2");
				GridPane.setConstraints(player2,0,3);
				gridPane.getChildren().addAll(player1,player2);
				numOfPlayers =2;
				break;
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
}
