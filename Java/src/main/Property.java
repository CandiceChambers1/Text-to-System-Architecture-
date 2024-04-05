package main;

public class Property {
    String name;
    String xmiID;
    String ownerXMI;
    String reusePropertyID;

    public Property(String name, String xmiID, String ownerXMI, String reusePropertyID) {
        this.name = name;
        this.xmiID =xmiID;
        this.ownerXMI=ownerXMI;
        this.reusePropertyID=reusePropertyID;
    }
}
