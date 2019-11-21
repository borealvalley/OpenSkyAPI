package acamo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import messer.BasicAircraft;

public class ActiveAircrafts implements Observer {
    private HashMap<String, BasicAircraft> activeAircrafts;

    public ActiveAircrafts() {
        activeAircrafts = new HashMap<String, BasicAircraft>();
    }

    public synchronized void store(String icao, BasicAircraft ac) {
        activeAircrafts.put(icao, ac);
    }

    public synchronized void clear() {
        activeAircrafts.clear();
    }

    public synchronized BasicAircraft retrieve(String icao) {
        return activeAircrafts.get(icao);
    }

    public synchronized ArrayList<BasicAircraft> values() {
        return new ArrayList<BasicAircraft>(activeAircrafts.values());
    }

    public String toString() {
        return activeAircrafts.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        BasicAircraft ac = (BasicAircraft) arg;
        if (ac == null) {
            // Clear hashmap on seperator (null object)
            clear();
        } else {
            store(ac.getIcao(), ac);
        }
    }
}
