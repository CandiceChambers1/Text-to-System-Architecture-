package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Sentences {
    ArrayList<Sentence> sentences;
    LinkedHashMap <String, Function> functions;

    public Sentences(){
        sentences = new ArrayList<Sentence>();
        functions = new LinkedHashMap<String, Function>();
    }
    public void addFunctionFlow(String functionVerb, String functionFlow){
        Function f = new Function(functionVerb);
        f.addFlows(functionFlow);
        functions.put(functionVerb, f);
    }
    public void createNewSentence(String type, String noun){sentences.add(new Sentence(type,noun));}

    public Sentence getSentenceByStructNoun(String noun){
        for(Sentence sentence: sentences){
            if(sentence.structNoun.equals(noun)){
                return sentence;
            }
        }
        return null;
    }
    public Sentence getSentenceByType(String type){
        for(Sentence sentence: sentences){
            if(sentence.sentenceType.equals(type)){
                return sentence;
            }
        }
        return null;
    }

}
