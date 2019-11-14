package acamo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import messer.BasicAircraft;

//TODO: create hash map and complete all operations
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
    // TODO: store arg in hashmap using the method above
    public void update(Observable o, Object arg) {
        BasicAircraft ac = (BasicAircraft) arg;
        store(ac.getIcao(), ac);
    }
}
