package main;

import java.util.ArrayList;

public class Function {
    String functionVerb;

    String adjv;
    ArrayList<String> flows;

    public static final int MATERIAL=0, ENERGY=1, SIGNAL=2;
    public Function(String functionVerb,String adjv){
        this.functionVerb=functionVerb;
        this.adjv = adjv;
        this.flows= new ArrayList<String>();
    }
    public void addFlows(String flow){
        flows.add(flow);
    }
}
