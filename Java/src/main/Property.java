package main;

public class Property {
    String name;
    String xmiID;
    String ownerName;
    String ownerXMI;
    String propertyType;

    public Property(String name, String xmiID, String ownerName, String ownerXMI, String propertyType) {
        this.name = name;
        this.xmiID =xmiID;
        this.ownerName = ownerName;
        this.ownerXMI=ownerXMI;
        this.propertyType=propertyType;
    }

}
