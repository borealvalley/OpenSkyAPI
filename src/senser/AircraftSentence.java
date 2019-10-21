package senser;

public class AircraftSentence {
    private String aircraftJson;

    public AircraftSentence(String aircraftJson) {
        this.aircraftJson = aircraftJson;
    }

    public String getString() {
        return aircraftJson;
    }

    public void setString(String aircraftJson) {
        this.aircraftJson = aircraftJson;
    }

    public String toString() {
        return aircraftJson;
    }
}
