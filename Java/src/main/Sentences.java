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
    public void addFunctionFlow(String functionVerb, String adjv, String functionFlow){
        Function f = new Function(functionVerb, adjv);
        f.addFlows(functionFlow);
        functions.put(functionVerb, f);
    }
    public void createNewSentence(String type, String noun){sentences.add(new Sentence(type,noun));}
    public void createNewSentence(String type, String src, String dest){sentences.add(new Sentence(type,src,dest));}
    public void createNewSentencePortInternal(String type, String noun, boolean isPort, boolean isInternal){
        sentences.add(new Sentence(type,noun,isPort,isInternal));}

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
    public Sentence getSentenceByTypeName(String type, String name){
        for(Sentence sentence: sentences){
            if(sentence.sentenceType.equals(type) && sentence.structNoun.equals(name)){
                return sentence;
            }
        }
        return null;
    }
    public Sentence getSentenceByTypePort(String type, String name, boolean isport){
        for(Sentence sentence: sentences){
            if(sentence.sentenceType.equals(type) && sentence.structNoun.equals(name) && sentence.isPort==isport){
                return sentence;
            }
        }
        return null;
    }

    public Sentence getSentenceByConnection(String connection, String noun, String src) {
        for(Sentence sentence: sentences){
            if(sentence.sentenceType.equals(connection) && sentence.structNoun.equals(noun) && sentence.connectionNoun.equals(src)){
                return sentence;
            }
        }
        return null;
    }
}
