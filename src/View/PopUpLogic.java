package View;

import Model.Game;
import Model.SysData;
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

    public static PopUpLogic getInstance() {
    	
        if (instance == null)
            instance = new PopUpLogic();
        
        return  instance;
    }
/**
 * pop up that show question
 */
    public void ShowQuestion(){
       	ViewLogic.getInstance().CancelTimer();

    	ImageIcon icon =new ImageIcon("src\\res\\download.jpg");
     	Random rand = new Random();
 		int indexOfQuestion = rand.nextInt(SysData.createInstance().getQuestions().size());
    	List<String> a= SysData.instance.getQuestions().get(indexOfQuestion).getAnswers();
     	String answer= a.get(0);
    	String answer1= a.get(1);
    	String answer2=	 a.get(2);
    	String answer3=	"";
    	if(a.size()==4) {
    	  answer3="4. "+a.get(3);
    	}
    	JLabel label = new JLabel("<html>Question Candy: <br>"  +  SysData.instance.getQuestions().get(indexOfQuestion).getquestion()+" <br>"+" <br>" +"1. "+ answer+
    			" <br>"+" <br>"+"2. "+ answer1+
    			" <br>"+" <br>"+"3. "+ answer2+
    			" <br>"+" <br>"+ answer3+" <br>"+
    			"</html>");
    	UIManager.put("OptionPane.minimumSize",new Dimension(150,150));
    	label.setFont(new Font("Lucida Console", Font.BOLD, 13));
        JOptionPane.showMessageDialog(null,label,"Question",JOptionPane.QUESTION_MESSAGE,icon);
	 	   ViewLogic.getInstance().getTimer();


    }
/**
 * pop up that show that the game is over 
 * @param score
 */
    public void ShowGameOver(int score){
       	ViewLogic.getInstance().CancelTimer();

        UIManager.put("OptionPane.minimumSize",new Dimension(120,120));
     	JOptionPane.showMessageDialog(null, "                       Game over!!! \n"+
       		 " You are final score is "+score+ " at "+ViewLogic.getInstance().GetTimeResults()+" minutes","Game over",JOptionPane.INFORMATION_MESSAGE);
		 ViewLogic.getInstance().setTimerCounting();


    }
    /**
     * pop up that show if we want to exit from the game
     * if the param is true its mean get out from all of the game 
     * if false its  just go out from the board game and 
     *	we need to freeze the ghosts before the popup
     * @param exitMain
     * @return
     */
    public int ShowEXit(boolean freeze ){
    	int g=4;
    	if (!freeze) {
          	Game.flag=true;
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
      	Game.flag=true;
       	ViewLogic.getInstance().CancelTimer();
     	JOptionPane.showMessageDialog(null, "you stop the game ,press ok to continue  ","Pause",JOptionPane.INFORMATION_MESSAGE);
     	 synchronized ( Game.getInstance()) {
			   Game.getInstance().notify();
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
		// content.setBody(gridPane);
		//StackPane stackPane = new StackPane();
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
                    GridPane.setConstraints(field, 0, 0);
                    gridPane.getChildren().addAll(field);
                }
				else {
					gridPane.getChildren().removeAll();
					content.setBody(new Text("Please wait..."));
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
		//gridPane.getChildren().addAll(gameType, button);
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
}
