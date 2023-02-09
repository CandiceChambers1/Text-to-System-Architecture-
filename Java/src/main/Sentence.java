package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Sentence {
    String sentenceType;
    String structNoun;
    ArrayList <String> structNouns;
    HashMap<String, String> contextMap;
    boolean isInternal = false;
    boolean isPort = false;

    public Sentence(String type, String structNoun){
        this.sentenceType = type;
        this.structNoun = structNoun;
        this.structNouns = new ArrayList<String>();
    }

    public void populateDuplicate(Sentence newSentence) {
        for (String key : this.contextMap.keySet()) {
            newSentence.addContext(key, this.contextMap.get(key));
        }
        for (String val : structNouns) {
            newSentence.structNouns.add(val);
        }
    }
    public void addStructNoun(String var){
        structNouns.add(var);
    }
    public void addContext(String var, String context){
        this.contextMap.put(var, context);
    }
    public String getContext(String var){
        return this.contextMap.get(var);
    }
}
