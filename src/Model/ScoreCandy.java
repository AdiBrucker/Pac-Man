package Model;

public class ScoreCandy extends Candy{

    private final int yellowCandy = 1;
    private final int silverCandy = 50;

    public ScoreCandy(int x, int y, String type) {
        super(x, y, type);
         LifeBonus=new isntLife();/// set this when there are a gold silver !! 
    }

    public int getYellowCandyScore() {
        return yellowCandy;
    }

    public int getSilverCandyScore() {
        return silverCandy;
    }
    
    public int getGoldCandyScore() {/// if  its gold candy the score is 0 , but the play will get +1 life
        return 0;
    }
}
