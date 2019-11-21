package messer;

import java.util.Date;

import senser.AircraftSentence;

/* Get the following datafields out of the JSON sentence using Regex and String methods
 * and return a BasicAircraft
 *
 * For Lab3 replace usee JSO parsing instead
 *
 * "Icao":"3C5467", matches; first part is Icao, second part is 3C5467
 * "Op":"Lufthansa matches; first part is Op, second part is Lufthansa
 * "Species":1, matches; first part is Species, second part is 1
 * "PosTime":1504179914003, matches; first part is PosTime, second part is 1504179914003
 * "Lat":49.1912, matches; first part is Lat, second part is 49.1912
 * "Long":9.3915, matches; first part is Long, second part is 9.3915
 * "Spd":420.7, matches; first part is Spd, second part is 420.7
 * "Trak":6.72, matches; first part is Trak, second part is 6.72
 * "GAlt":34135, matches; first part is GAlt, second part is 34135
 */

public class AircraftFactory {

    public BasicAircraft fromAircraftSentence(AircraftSentence sentence) {
        // "4b16b9","SWR96C  ","Switzerland",1571320699,1571320699,9.7484,48.9778,9753.6,false,214.52,222.18,0,null,9959.34,"1000",false,0
        // https://github.com/openskynetwork/opensky-api/blob/master/docs/free/states-response.rst

        String icao = null;
        String callsign = null;
        String originCountry = null;
        Date timePos = null;
        double longitude = 0;
        double latitude = 0;
        double speed = 0;
        double track = 0;
        double altitude = 0;

        String sentenceStr = sentence.getString().replaceAll("\"", "");

        BasicAircraft msg = null;

        if (sentenceStr.length() != 0 && sentenceStr != null) {
            String[] aircraftInfo = sentenceStr.split(",");

            icao = aircraftInfo[0];
            callsign = aircraftInfo[1] != "null" ? aircraftInfo[1].trim() : null;
            originCountry = aircraftInfo[2] != "null" ? aircraftInfo[2].trim() : null;
            try {
                timePos = new Date(Long.parseLong(aircraftInfo[3]) * 1000);
            } catch (NumberFormatException e) {}
            try {
                longitude = Double.parseDouble(aircraftInfo[5]);
            } catch (NumberFormatException e) {}
            try {
                latitude = Double.parseDouble(aircraftInfo[6]);
            } catch (NumberFormatException e) {}
            try {
                speed = Double.parseDouble(aircraftInfo[9]);
            } catch (NumberFormatException e) {}
            try {
                track = Double.parseDouble(aircraftInfo[10]);
            } catch (NumberFormatException e) {}
            try {
                altitude = Double.parseDouble(aircraftInfo[13]);
            } catch (NumberFormatException e) {}

            msg = new BasicAircraft(icao, callsign, originCountry, timePos, new Coordinate(latitude, longitude), speed, track, altitude);
        }

        return msg;
    }
}
