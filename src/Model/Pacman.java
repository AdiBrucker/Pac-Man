package Model;

import View.PacmanAnimation;
import View.GameView;
import View.PopUpLogic;
import View.ViewLogic;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Holds the pacman model.
 */
public class Pacman extends Rectangle implements IMovable, Comparable, Serializable {

    //for Serialization
    private static final long serialVersionUID = 1L;

    //Boolean which indicates to which direction the pacman is heading
    public boolean right, left, up, down;
    //defines pacmans speed
    public int speed = 4;
    public int score1 = 0;
    public int lifeScore = 3;
    public int score = 0;
    public static ViewLogic viewInstance;
    public String PacmanNane;
    private int animationTime = 0;
    //variables which are responsible for the animation of pacman
    private int targetAnimationTime = 10;
    private int animationIndexImage = 0;
    private int lastDir = 1;

    /**
     * Constructor creates the instance of pacman
     * @param x
     * @param y
     * @param nickname
     */
    public Pacman(int x, int y, String nickname) {
        setBounds(x, y, 26, 26);
        new Location(x, y);
        score = 0;
        lifeScore = 3;
        PacmanNane = nickname;
    }

    /**
     * Constructor creates the players name and his score
     * @param score2
     * @param name
     */
    public Pacman(int score2, String name) {
        PacmanNane = name;
        score1 = score2;
    }

    /**
     * Responsible for the movements of the pacman and handles the cases when pacman is eating a candy or intersects with a ghost.
     * The method is also responsible to delete the the ghosts are the candies when pacman intersects with them.
     */
    @Override
    public void tick() {
        Maze maze = Game.mazes.get(Game.getPlayerIndex());
        viewInstance = ViewLogic.getInstance();
        animatePacman();
        eatingCandies(maze);
        pacmansMovements();
        ghostIntersections(maze);
        //when there are no more candies the game is finished
        if (maze.candy.size() == 0) {
            System.exit(1);
        }
    }

    /**
     * Handles what happens when pacman intersects with a ghost.
     * LifeScore-- and the ghost is removed.
     * @param maze
     */
    private void ghostIntersections(Maze maze) {
        for (int j = 0; j < maze.ghosts.size(); j++) {
            if (this.intersects(maze.ghosts.get(j))) {
                maze.ghosts.remove(j);// he cant remove the ghost just when he eating spaciel candy
                Music("\\src\\res\\pacmandeath.wav");

                if (lifeScore > 1) {
                    lifeScore--;
                    viewInstance.setLifeScoreForPacman();
                } else {
                    SysData.instance.AddPacman(score, PacmanNane);// when we will add a name
                    // its will add to pacman list that will be the winner at scores table
                    ShowGameOver();
                }
                break;
            }
        }
    }

    /**
     * This methods manages pacman's movements and makes sure it can move only in the maze's boundaries.
     */
    private void pacmansMovements() {
        if (right && Game.canMove(x + speed, y, width, height)) {
            x += speed;
            lastDir = 1;
        } else if (left && Game.canMove(x - speed, y, width, height)) {
            x -= speed;
            lastDir = -1;
        } else if (up && Game.canMove(x, y - speed, width, height)) {
            y -= speed;
            lastDir = 2;
        } else if (down && Game.canMove(x, y + speed, width, height)) {
            y += speed;
            lastDir = -2;
        }
    }

    /**
     * Handles what happens when pacman eats the different types of the candies.
     * adding to pacman's score and remove the candies from the game.
     * @param maze
     */
    private void eatingCandies(Maze maze) {
        for (int i = 0; i < maze.candy.size(); i++) {
            if (this.intersects(maze.candy.get(i))) {
                if (maze.candy.get(i).getType() == "Yellow" && maze.candy.get(i) instanceof ScoreCandy) {
                    ScoreCandy s = (ScoreCandy) maze.candy.get(i);
                    score += s.getYellowCandyScore();
                    maze.candy.remove(i);
                    viewInstance.setScoreForPacman();
                } else if (maze.candy.get(i).getType() == "Silver" && maze.candy.get(i) instanceof ScoreCandy) {
                    ScoreCandy s = (ScoreCandy) maze.candy.get(i);
                    score += s.getSilverCandyScore();
                    maze.candy.remove(i);
                    viewInstance.setScoreForPacman();

                } else if (maze.candy.get(i).getType() == "Gold" && maze.candy.get(i) instanceof ScoreCandy) {
                    lifeScore++;
                    maze.candy.remove(i);
                    viewInstance.setLifeScoreForPacman();

                } else if (maze.candy.get(i).getType() == "QuestionCandy") {
                    right = false;
                    left = false;
                    up = false;
                    down = false;
                    Music("\\src\\res\\Question-SOUND.wav");

                    showQuestion();
                    right = false;
                    left = false;
                    up = false;
                    down = false;
                    maze.candy.remove(i);
                } else if (maze.candy.get(i).getType() == "PoisonCandy") {
                    right = false;
                    left = false;
                    up = false;
                    down = false;
                    if (lifeScore > 1) {
                        lifeScore--;
                        viewInstance.setLifeScoreForPacman();
                    } else {
                        ShowGameOver();
                    }

                    right = false;
                    left = false;
                    up = false;
                    down = false;
                    maze.candy.remove(i);
                }
                break;
            }
        }
    }

    /**
     * Responsible for the animation of pacman's mouth.
     */
    private void animatePacman() {
        animationTime++;
        if (animationTime == targetAnimationTime) {
            animationTime = 0;
            animationIndexImage++;
        }
    }

    /**
     * Plays the game's music
     * @param path2
     */
    public void Music(String path2) {
        String path = new File("").getAbsolutePath() + path2;
        //Make a File object with a path to the audio file.
        File sound = new File(path);

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            Clip c = AudioSystem.getClip();
            c.open(ais); //Clip opens AudioInputStream
            c.start(); //Start playing audio

            //sleep thread for length of the song
            // Thread.sleep((int)(c.getMicrosecondLength() * 0.001));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Shows a question as a popup window when pacman eats a question candy
     */
    public void showQuestion() {
        PopUpLogic.getInstance().ShowQuestion();
    }

    /**
     * shows a popup when the game is over
     */
    public void ShowGameOver() {
        PopUpLogic.getInstance().ShowGameOver(score);
        GameView.closewindow();
    }

    /**
     * Get the game's score
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the final's game score
     * @return
     */
    public int getScoreResult() {
        return score1;
    }

    /**
     * Draws the pacman and its animation.
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (lastDir == 1) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2], x, y, width, height, null);
        }
        if (lastDir == -1) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2], x + 32, y, -width, height, null);
        }
        if (lastDir == 2) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2 + 2], x, y, width, height, null);
        }
        if (lastDir == -2) {
            g.drawImage(PacmanAnimation.pacman[animationIndexImage % 2 + 4], x, y, width, height, null);
        }
    }

    /**
     * Gets the life score
     * @return
     */
    public int getLifeScore() {
        return lifeScore;
    }

    @Override
    /**
     * when we need only one parameter
     * sorting by score
     * descending order
     * @param o
     * @return
     */
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        return ((Pacman) o).getScoreResult() - this.getScoreResult();
    }

    /**
     * Gets pacman's name
     * @return
     */
    public String getPacmanName() {
        return PacmanNane;
    }

    /**
     * Strings the pacman's player name and its score
     * @return
     */
    @Override
    public String toString() {
        return "Pacman [PacmanNane=" + PacmanNane + ", score=" + score1 + "]";
    }


}
