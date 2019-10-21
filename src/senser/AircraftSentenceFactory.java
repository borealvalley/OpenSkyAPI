package senser;

import java.util.ArrayList;

public class AircraftSentenceFactory {
    public ArrayList<AircraftSentence> fromAircraftJson(String jsonAircraftList) {
        ArrayList<AircraftSentence> sentencelist = new ArrayList<AircraftSentence>();

        //get relevant info
        String[] sentences = jsonAircraftList.split("(\\]\\s?,\\s?\\[)|(\\])|(\\[)");

        for (String sentence : sentences) {
            sentencelist.add(new AircraftSentence(sentence));
        }

        return sentencelist;
    }
}
