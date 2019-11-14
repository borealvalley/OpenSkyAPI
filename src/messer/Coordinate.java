package messer;

public class Coordinate {
    private double latitude;
    private double longitude;

    /**
     * @param latitude WGS-84 latitude in decimal degrees.
     * @param longitude WGS-84 longitude in decimal degrees.
     */
    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", latitude, longitude);
    }

    /**
     * @return WGS-84 latitude in decimal degrees. Can be null.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude WGS-84 latitude in decimal degrees.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return WGS-84 longitude in decimal degrees. Can be null.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude WGS-84 longitude in decimal degrees.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
