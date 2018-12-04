package Model;

import View.PopUpLogic;
import View.ViewLogic;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Pacman extends Rectangle implements IMovable, Comparable,Serializable{
	 
	//for Serialization
	private static final long serialVersionUID = 1L;

    public boolean right, left, up, down;
    public int speed = 4;
    private Location location;
    public static int lifeScore = 3;
    public static int score = 0;
    public static ViewLogic viewInstance;
    public String PacmanNane;
    private int score1  ; //// just for checking how the score saved to a file

    public Pacman(int x, int y){
        setBounds(x, y, 32, 32);
        location = new Location(x,y);
    }

    public Pacman(int score2 ,String name){
    	  PacmanNane=name;
    	  score1=score2;
    }
    
    public static void setScore(int score) {
		Pacman.score = score;
	}

	@Override
    public void tick(){
        Maze maze = Game.maze;
         viewInstance = ViewLogic.getInstance();
        for (int i = 0; i < maze.candy.size(); i++){
            if (this.intersects(maze.candy.get(i))){
                if(maze.candy.get(i).getType() == "Yellow" && maze.candy.get(i) instanceof ScoreCandy){
                    ScoreCandy s = (ScoreCandy)maze.candy.get(i);
                    score += s.getYellowCandyScore();
                    maze.candy.remove(i);
                    viewInstance.setScoreForPacman();
                }
                else if(maze.candy.get(i).getType() == "Silver" && maze.candy.get(i) instanceof ScoreCandy){
                    ScoreCandy s = (ScoreCandy)maze.candy.get(i);
                    score += s.getSilverCandyScore();
                    maze.candy.remove(i);
                    viewInstance.setScoreForPacman();

                }
                else if(maze.candy.get(i).getType() == "QuestionCandy"){
                    right = false;
                    left = false;
                    up = false;
                    down=false;
                    Music("src\\Question-SOUND.wav");

                    showQuestion();
                    right = false;
                    left = false;
                    up = false;
                    down=false;
                    maze.candy.remove(i);
                } 
                else if(maze.candy.get(i).getType() == "PoisonCandy")
                {
                    right = false;
                    left = false;
                    up = false;
                    down=false;
                    if (lifeScore > 1) {
                        lifeScore--;
                        viewInstance.setLifeScoreForPacman();
                      //  setBounds(x, y, 32, 32);
                    }
                    else
                    {
              ///      	SysData.instance.AddPacman(score, name); when we will add a name 
                    	// its will add to pacman list that will be the winner at scores table
                        ShowGameOver();
                    }

                    right = false;
                    left = false;
                    up = false;
                    down=false;
                    maze.candy.remove(i);
                }
                break;
            }
        }


        if (right && canMove(x+ speed,y)) x += speed;
        else if (left && canMove(x-speed,y)) x -= speed;
        else if (up && canMove(x,y-speed)) y -= speed;
        else  if (down && canMove(x,y+speed)) y += speed;




        for (int j = 0; j < maze.ghosts.size(); j++){
            if (this.intersects(maze.ghosts.get(j))){
                maze.ghosts.remove(j);// he cant remove the ghost just when he eating spaciel candy
                Music("\\pacmandeath.wav");
                
                if (lifeScore > 1) {
                    lifeScore--;
                    viewInstance.setLifeScoreForPacman();
                }
                else
                {
                    ShowGameOver();
                }
                break;
            }
        }

        /* The method to end the game. we'll use it later.
        if (maze.candy.size() == 0){
            System.exit(1);
        }*/
    }
	
	public void  Music(String path2) {
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

    public void showQuestion(){
        PopUpLogic instance = PopUpLogic.getInstance();
        instance.ShowQuestion();
}

    public void ShowGameOver(){
        PopUpLogic instance = PopUpLogic.getInstance();
        instance.ShowGameOver(score);
    }
    public int getScore(){
        return score;
    }

    @Override
    public boolean canMove(int nextx, int nexty){
        Rectangle bounds = new Rectangle(nextx, nexty, width,  height);
        Maze maze = Game.maze;

        for (int xx = 0; xx < maze.walls.length; xx++){
            for (int yy = 0; yy < maze.walls[0].length; yy++){
                if (maze.walls[xx][yy] != null){
                    if (bounds.intersects(maze.walls[xx][yy])){

                        for (int x = 0; x < maze.walls.length; x++){
                            for (int y = 0; y < maze.walls[0].length; y++){
                                if (maze.walls[x][y] != null){
                                    if (bounds.intersects(maze.walls[x][y])){

                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void render(Graphics g){
        SpriteSheet sheet = Game.spriteSheet;
        g.drawImage(sheet.getSprite(0, 0), x, y, 32, 32 ,null);
    }

    public int getLifeScore(){
        return lifeScore;
    }
    @Override
	/**
	 * when we need to only one parameter
	 * sorting by score
	 * descending order
	 * @param o
	 * @return
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((Pacman) o).getScore()-this.getScore() ;
	}

	@Override
	public String toString() {
		return "Pacman [PacmanNane=" + PacmanNane + ", score=" + score1 + "]";
	}

 
}
