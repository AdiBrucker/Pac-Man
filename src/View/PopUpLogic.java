package View;

import Model.Pacman;
import Model.SysData;

import javax.swing.*;

 
import java.awt.*;
 import java.util.List;
import java.util.Random;

public class PopUpLogic {

    static PopUpLogic instance;

    public static PopUpLogic getInstance() {
        if (instance == null)
            instance = new PopUpLogic();
        return  instance;
    }

    public void ShowQuestion(){
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

    
    }

    public void ShowGameOver(int score){
        UIManager.put("OptionPane.minimumSize",new Dimension(120,120));
     	JOptionPane.showMessageDialog(null, "                       Game over!!! \n"+
       		 " You are final score is "+score+ " at "+ViewLogic.timeResults+" minutes","Game over",JOptionPane.INFORMATION_MESSAGE);


    }


}
