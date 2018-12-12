package View;

import Model.Game;
import Model.ModelLogic;
import Model.Pacman;
import Model.SysData;

import javax.swing.*;

 
import java.awt.*;
 import java.util.List;
import java.util.Random;
/**
 * class that used for messages to the user 
 *
 */
public class PopUpLogic {

    static PopUpLogic instance;

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

    	ImageIcon icon =new ImageIcon("src//download.jpg");
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

}
