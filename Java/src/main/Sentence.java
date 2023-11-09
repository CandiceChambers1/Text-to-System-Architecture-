package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Sentence {
    String sentenceType;
    String structNoun;
    String connectionNoun;
    ArrayList <String> structNouns;

    String funcVerb;
    String funcFlowState;
    String funcAdjv;
    HashMap<String, String> contextMap;
    boolean isInternal = false;
    boolean isPort = false;
    boolean isInstantiation = false;

    boolean isConnection = false;

    public Sentence(String type, String structNoun){
        this.sentenceType = type;
        this.structNoun = structNoun;
        this.structNouns = new ArrayList<String>();
    }

    public Sentence(String type, String noun, boolean isPort, boolean isInternal) {
        this.sentenceType = type;
        this.structNoun = noun;
        this.structNouns = new ArrayList<String>();
        this.isPort=isPort;
        this.isInternal=isInternal;
    }

    public Sentence(String type, String src, String dest) {
        this.sentenceType= type;
        this.structNoun = src;
        this.connectionNoun = dest;
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
    public void addFunctionVerb(String verb){
        funcVerb = verb;
    }
    public void addFunctionFlowState(String flow){
        funcFlowState = flow;
    }
    public void addFunctionAdjv(String adjv){
        funcAdjv = adjv;
    }


    public void addContext(String var, String context){
        this.contextMap.put(var, context);
    }
    public String getContext(String var){
        return this.contextMap.get(var);
    }
}
