package main;

public class Block {
    Property property;
    String type;
    String name;
    String XmiID;
    String ownerXMI;
    public Block(String type, String name, String XmiID){
        this.type=type;
        this.name=name;
        this.XmiID=XmiID;
    }
    public Block(String type, String name, String XmiID, String ownerXMI){
        this.type=type;
        this.name=name;
        this.XmiID=XmiID;
        this.ownerXMI=ownerXMI;
    }

    public String getBlockName(String type){
            if(this.type.equals(type)){
                return name;
            }
        return null;
    }
    public Property getProperty() {
        return property;
    }
}
