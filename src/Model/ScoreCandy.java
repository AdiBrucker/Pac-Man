package Model;

/**
 * Represents the candy which add to the score of the player
 */
public class ScoreCandy extends Candy{

    private final int yellowCandy = 1;
    private final int silverCandy = 50;

    /**
     * Constructor- Creates a score candy
     * @param x
     * @param y
     * @param type
     */
    public ScoreCandy(int x, int y, String type) {
        super(x, y, type);
    }

    /**
     * Gets a yellow candy
     * @return
     */
    public int getYellowCandyScore() {
        return yellowCandy;
    }

    /**
     * Gets a silver candy
     * @return
     */
    public int getSilverCandyScore() {
        return silverCandy;
    }

    /**
     * Gets a gold candy
     * @return
     */
    public int getGoldCandyScore() {/// if  its gold candy the score is 0 , but the play will get +1 life
        return 0;
    }
}
