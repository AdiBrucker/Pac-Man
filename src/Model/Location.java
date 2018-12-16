package Model;

/**
 * Define the location of the object in the game
 */
public class Location {

    private float Longitude;
    private float Latitude;

    /**
     * constructs the location of an object on the game's board.
     * @param longitude
     * @param latitude
     */
    public Location(float longitude, float latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }
}
