package main;

public class Blocks {
    Property property;
    String name;
    String XmiID;
    public Blocks(String name, String XmiID){
        this.name=name;
        this.XmiID=XmiID;
    }

    public Property getProperty() {
        return property;
    }
}
