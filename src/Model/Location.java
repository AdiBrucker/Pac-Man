package Model;

public class Location {

    private float Longitude;
    private float Latitude;

    public Location(float longitude, float latitude) {
        Longitude = longitude;
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }
}
