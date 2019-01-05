package View;

import Model.Game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import java.awt.*;


/**
 * Manages the view logic between the different screens.
 */
public class ViewLogic {

    private static Label lScore;
    private static Label lifeScore;
    public static Label timer;
    private static Label nickname;
    private static ViewLogic instance;
    private static Game game;
    private int TimerCounting = 0;// counting seconds
    private int saveCountingToContinue = 0;// counting seconds

    private static String timeResults = "";///
    private TimerTask task;
    public static boolean restart = false;


    public static void SetInstance() {
        instance = null;
    }

    public String GetTimeResults() {
        return timeResults;
    }

    public static Label getNickname() {
        return nickname;
    }

    public static ViewLogic getInstance() {
        if (instance == null) {
            instance = new ViewLogic();
            //   game = Game.getInstance();
            nickname = new Label(Game.pacmans.get(Game.getPlayerIndex()).getPacmanName());

            nickname.setPreferredSize(new Dimension(300, 40));
            nickname.setForeground(Color.WHITE);
            nickname.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD
                    | java.awt.Font.ITALIC, 36));
            nickname.setBackground(Color.BLACK);

            lScore = new Label("SCORE: " + String.valueOf(Game.pacmans.get(Game.getPlayerIndex()).getScore()));
            lScore.setPreferredSize(new Dimension(300, 40));
            lScore.setForeground(Color.WHITE);
            lScore.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD
                    | java.awt.Font.ITALIC, 36));
            lScore.setBackground(Color.BLACK);

            lifeScore = new Label("LIFE SCORE: " + String.valueOf(Game.pacmans.get(Game.getPlayerIndex()).getLifeScore()));
            lifeScore.setForeground(Color.WHITE);
            lifeScore.setMinimumSize(new Dimension(300, 40));
            lifeScore.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD
                    | java.awt.Font.ITALIC, 36));
            lifeScore.setBackground(Color.BLACK);

            timer = new Label("timer : 00:00");
            timer.setForeground(Color.WHITE);
            timer.setMinimumSize(new Dimension(200, 40));
            timer.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD
                    | java.awt.Font.ITALIC, 36));
            timer.setBackground(Color.BLACK);
        }

        return instance;
    }

    public static void setScoreForPacman() {
        lScore.setText("SCORE: " + Game.pacmans.get(Game.getPlayerIndex()).getScore());
    }

    public void setTimerCounting() {
        TimerCounting = 0;
    }

    public Label getlScoreForPacman() {
        return lScore;
    }

    public static void setLifeScoreForPacman() {
        lifeScore.setText("LIFE SCORE: " + Game.pacmans.get(Game.getPlayerIndex()).getLifeScore());
    }

    public Label getLifeScoreForPacman() {

        return lifeScore;
    }

    /**
     * cancel timer when there are a pause at the game
     * the func save the time and cancel the timer
     */
    public void CancelTimer() {
        saveCountingToContinue = TimerCounting;
        restart = true;
    }

    /**
     * init the timer
     *
     * @return
     */
    public void getTimer() {

        Timer timer1 = new Timer();
        task = new TimerTask() {
            public void run() {

                if (restart) {
                    TimerCounting = saveCountingToContinue;
                    task.cancel();
                    timer1.cancel(); //In order to gracefully terminate the timer thread
                    restart = false;
                }
                TimerCounting++;
                int minutes1 = (int) Math.floor(TimerCounting / 60F);
                int second1 = (int) Math.floor(TimerCounting - minutes1 * 60);

                if (second1 % 10 == 0) {//// if 40 second passed its replace with the other users
                    setPacmanTurn();
                    Timer timer = new Timer();

                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (PopUpLogic.getNumOfPlayers() > 1)
                                PopUpLogic.getInstance().showPlayerTurn();
                        }
                    }, 1);

                }


                // checking if the second is less then 10 its will add a 0 at the begging of the second view
                if (second1 < 10) {
                    if (minutes1 < 10) { // checking if the minutes is less then 10 its will add a 0 at the begging of the second view
                        timeResults = "0" + minutes1 + ":0" + second1;
                        timer.setText("Timer: " + timeResults);
                    } else {
                        timeResults = "" + minutes1 + ":0" + second1;
                        timer.setText("Timer: " + timeResults);

                    }
                } else {
                    if (minutes1 < 10) {
                        timeResults = "0" + minutes1 + ":" + second1;
                        timer.setText("Timer: " + timeResults);
                    } else {
                        timeResults = "" + minutes1 + ":" + second1;
                        timer.setText("Timer: " + timeResults);
                    }
                }


            }
        };
        timer1.scheduleAtFixedRate(task, 1000, 1000);/// the time will work the same as a regular timer
        resetPacmanMovements();
    }


    public static void setPacmanTurn() {

        ArrayList<Game> GameList = Game.getInstanceList();

        if (Game.getPlayerIndex() == 0 && PopUpLogic.getNumOfPlayers() > 1) {

            Game.setPlayerIndex(1);
            setNickname();
            setLifeScoreForPacman();
            setScoreForPacman();
        } else {

            Game.setPlayerIndex(0);
            setNickname();
            setLifeScoreForPacman();
            setScoreForPacman();
        }
    }

    private static void resetPacmanMovements() {
        Game.pacmans.get(0).right = false;
        Game.pacmans.get(0).left = false;
        Game.pacmans.get(0).up = false;
        Game.pacmans.get(0).down = false;
        Game.pacmans.get(1).right = false;
        Game.pacmans.get(1).left = false;
        Game.pacmans.get(1).up = false;
        Game.pacmans.get(1).down = false;
    }

    public static void setNickname() {
        ViewLogic.nickname.setText(Game.pacmans.get(Game.getPlayerIndex()).getPacmanName());
    }
}
