package messer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

public class BasicAircraft {
    private String icao;
    private String callsign;
    private String originCountry;
    private Date posTime;
    private Coordinate coordinate;
    private Double speed;
    private Double track;
    private Double altitude;

    /**
     * @param icao
     * @param callsign
     * @param originCountry
     * @param posTime
     * @param coordinate
     * @param speed
     * @param track
     * @param altitude
     */
    public BasicAircraft(String icao, String callsign, String originCountry, Date posTime, Coordinate coordinate,
            Double speed, Double track, Double altitude) {
        this.icao = icao;
        this.callsign = callsign;
        this.originCountry = originCountry;
        this.posTime = posTime;
        this.coordinate = coordinate;
        this.speed = speed;
        this.track = track;
        this.altitude = altitude;
    }

    // TODO: Lab 4-6 return attribute names and values for table
    public static ArrayList<String> getAttributesNames() {
        return null;
    }

    public static ArrayList<Object> getAttributesValues(BasicAircraft ac) {
        return null;
    }

    @Override
    public String toString() {
        return String.format(
                "BasicAircraft [altitude = %s, coordinate = %s, icao = %s, callsign = %s, posTime = %s, originCountry = %s, speed = %s, track = %s]",
                altitude, coordinate, icao, callsign, posTime, originCountry, speed, track);
    }

    /**
     * @return the icao
     */
    public String getIcao() {
        return icao;
    }

    /**
     * @param icao the icao to set
     */
    public void setIcao(String icao) {
        this.icao = icao;
    }

    /**
     * @return the callsign
     */
    public String getcallsign() {
        return callsign;
    }

    /**
     * @param callsign the callsign to set
     */
    public void setcallsign(String callsign) {
        this.callsign = callsign;
    }

    /**
     * @return the originCountry
     */
    public String getoriginCountry() {
        return originCountry;
    }

    /**
     * @param originCountry the originCountry to set
     */
    public void setoriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    /**
     * @return the posTime
     */
    public Date getPosTime() {
        return posTime;
    }

    /**
     * @param posTime the posTime to set
     */
    public void setPosTime(Date posTime) {
        this.posTime = posTime;
    }

    /**
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return the speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return the track
     */
    public Double gettrack() {
        return track;
    }

    /**
     * @param track the track to set
     */
    public void settrack(Double track) {
        this.track = track;
    }

    /**
     * @return the altitude
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }
}
