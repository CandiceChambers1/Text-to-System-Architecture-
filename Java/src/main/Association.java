package main;

public class Association {
    String xmiID;
    String srcXMI;
    String destXMI;
    String srcName;
    String destName;

    public Association(String xmiID, String srcName, String srcXMI, String destName, String destXMI){
        this.xmiID = xmiID;
        this.srcName = srcName;
        this.srcXMI = srcXMI;
        this.destName = destName;
        this.destXMI = destXMI;
    }
}

