package View;

import Model.Game;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import java.awt.*;

public class ViewLogic {

	private static Label lScore;
    private static Label lifeScore;
    private static Label timer;
    private static ViewLogic instance;
    private static  Game game;
    private static  int s = 0;// counting seconds
    private static String timeResults="";/// 


    
    public static void SetInstance() {
    	instance=null;
    }
    public   String GetTimeResults() {
    	return timeResults;
    }
   
    public static  ViewLogic getInstance() {
        if (instance == null) {
            instance = new ViewLogic();
            game = Game.getInstance();
            lScore = new Label("SCORE: " + String.valueOf(Game.pacman.getScore()));    
            lScore.setPreferredSize(new Dimension(400,70));
            lScore.setForeground(Color.WHITE);
            lScore.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD
                    | java.awt.Font.ITALIC, 36));
            lScore.setBackground(Color.BLACK);

            lifeScore = new Label("LIFE SCORE: " + String.valueOf(Game.pacman.getLifeScore()));
            lifeScore.setForeground(Color.WHITE);
            lifeScore.setMinimumSize(new Dimension(200,50));
            lifeScore.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD
                    | java.awt.Font.ITALIC, 36));
            lifeScore.setBackground(Color.BLACK);                   
            timer = new Label("timer : 00:00");
            timer.setForeground(Color.WHITE);
            timer.setMinimumSize(new Dimension(200,50));
            timer.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD
                    | java.awt.Font.ITALIC, 36));
            timer.setBackground(Color.BLACK);
        }

        return instance;
    }

    public void setScoreForPacman() {
        lScore.setText("SCORE: " + Game.pacman.getScore());
    }
    public Label getlScoreForPacman(){
        return lScore;
    }

    public void setLifeScoreForPacman() {
        lifeScore.setText("LIFE SCORE: " + Game.pacman.getLifeScore());
    }
    public Label getLifeScoreForPacman(){
        return lifeScore;
    }
    
    public Label getTimer() {
     s=0;
        Timer timer1=new Timer();
        TimerTask task =new TimerTask() {
    	    	public void run () {
    	        	s++;
    	            int minutes1 = (int) Math.floor(s / 60F);
    	            int second1 = (int) Math.floor(s - minutes1 * 60);
    	            if (second1<10&&minutes1<10) {
    	            	timeResults="0"+minutes1+":0"+second1;
    	            	timer.setText("Timer: " + timeResults) ;
    	            }
    	            else {
    	            	timeResults="0"+minutes1+":"+second1;
     	            	timer.setText("Timer: " + timeResults) ;
    	            }
     
    	         	
    	    	}
    	   };
    	timer1.scheduleAtFixedRate(task,1000,1000);
    	return timer;

        
    }
}
