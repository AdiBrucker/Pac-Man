package Model;

public class ScoreCandy extends Candy{

    private final int yellowCandy = 1;
    private final int silverCandy = 50;

    public ScoreCandy(int x, int y, String type) {
        super(x, y, type);
    }

    public int getYellowCandyScore() {
        return yellowCandy;
    }

    public int getSilverCandyScore() {
        return silverCandy;
    }
}
