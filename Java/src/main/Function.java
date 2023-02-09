package main;

import java.util.ArrayList;

public class Function {
    String functionVerb;
    ArrayList<String> flows;

    public static final int MATERIAL=0, ENERGY=1, SIGNAL=2;
    public Function(String functionVerb){
        this.functionVerb=functionVerb;
        this.flows= new ArrayList<String>();
    }
    public void addFlows(String flow){
        flows.add(flow);
    }
}
