package messer;

import java.util.ArrayList;
import java.util.Arrays;
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
     * @param icao Unique ICAO 24-bit address of the transponder in hex string representation.
     * @param callsign Callsign of the vehicle (8 chars).
     * @param originCountry Country name inferred from the ICAO 24-bit address.
     * @param posTime Timestamp for the last position update.
     * @param coordinate WGS-84 coordinates in decimal degrees.
     * @param speed Velocity over ground in m/s.
     * @param track True track in decimal degrees clockwise from north (north = 0°).
     * @param altitude Geometric altitude in meters.
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

    /**
     * @return ArrayList of all attributes names in BasicAircraft
     */
    public static ArrayList<String> getAttributesNames() {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList("icao", "callsign", "originCountry", "posTime", "coordinate", "speed", "track", "altitude"));
        return list;
    }


    /**
     * @param ac The aircraft
     * @return ArrayList of all attributes values in BasicAircraft
     */
    public static ArrayList<Object> getAttributesValues(BasicAircraft ac) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.addAll(Arrays.asList(ac.icao, ac.callsign, ac.originCountry, ac.posTime, ac.coordinate, ac.speed, ac.track, ac.altitude));
        return list;
    }

    @Override
    public String toString() {
        return String.format(
                "BasicAircraft [altitude = %s, coordinate = %s, icao = %s, callsign = %s, posTime = %s, originCountry = %s, speed = %s, track = %s]",
                altitude, coordinate, icao, callsign, posTime, originCountry, speed, track);
    }

    /**
     * @return Unique ICAO 24-bit address of the transponder in hex string representation.
     */
    public String getIcao() {
        return icao;
    }

    /**
     * @param icao Unique ICAO 24-bit address of the transponder in hex string representation.
     */
    public void setIcao(String icao) {
        this.icao = icao;
    }

    /**
     * @return Callsign of the vehicle (8 chars). Can be null if no callsign has been received.
     */
    public String getCallsign() {
        return callsign;
    }

    /**
     * @param callsign Callsign of the vehicle (8 chars).
     */
    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    /**
     * @return Country name inferred from the ICAO 24-bit address.
     */
    public String getOriginCountry() {
        return originCountry;
    }

    /**
     * @param originCountry Country name inferred from the ICAO 24-bit address.
     */
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    /**
     * @return Timestamp for the last position update. Can be null.
     */
    public Date getPosTime() {
        return posTime;
    }

    /**
     * @param posTime Timestamp for the last position update.
     */
    public void setPosTime(Date posTime) {
        this.posTime = posTime;
    }

    /**
     * @return WGS-84 coordinates in decimal degrees.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate WGS-84 coordinates in decimal degrees.
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return Velocity over ground in m/s.
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed Velocity over ground in m/s.
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return True track in decimal degrees clockwise from north (north = 0°). Can be null.
     */
    public Double getTrack() {
        return track;
    }

    /**
     * @param track True track in decimal degrees clockwise from north (north = 0°).
     */
    public void setTrack(Double track) {
        this.track = track;
    }

    /**
     * @return Geometric altitude in meters. Can be null.
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * @param altitude Geometric altitude in meters.
     */
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }
}
