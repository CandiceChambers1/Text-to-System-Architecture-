package main;

import java.util.*;
import java.lang.String.*;

public class Output {
    Sentences sentences;

    Blocks blocks = new Blocks();
    public Output(Sentences sentences){
        this.sentences = sentences;
    }
    public String getRoot(){
        return blocks.getBlockByType("root").name;
    }
    public void generateTree(){
        for(Sentence s : sentences.sentences){
            if (Objects.equals(s.sentenceType, "Structural")) {
                if (s.isInternal) {

                    for (String sub: s.structNouns) {
                        blocks.createBlockWithOwner("internal", sub, generateXMI_ID("other"), blocks.getXMI(s.structNoun));
                    }


                } else if (s.isPort) {
                    for (String sub: s.structNouns) {
                        blocks.createBlockWithOwner("ports", sub, generateXMI_ID("other"), blocks.getXMI(s.structNoun));
                    }


                } else {
//                    System.out.println(s.structNoun);
                    blocks.createBlock("root", s.structNoun, generateXMI_ID("other"));
                    for (String sub: s.structNouns) {
                        blocks.createBlock("sub", sub, generateXMI_ID("other"));
                    }
                }
            }
        }
    }

    public String generateOutput() {

        /*
            Looping through the sentences and call the appropriate functions
         */

        String xmiPackageID, xmiOwnerID;
        int propertyCounter =0;
        xmiPackageID = generateXMI_ID("package");
//        System.out.println(xmiPackageID);

        String output = "<XMI xmi.version=\"1.1\" xmlns:UML=\"omg.org/UML1.3\">\n";
        output += generateHeader();
        output += generateStartContent(xmiPackageID);
        generateTree();
        for (Block b : blocks.blocks) {
            if(b.getBlockName("sub")!=null){
                output += generateBlock(b.name,xmiPackageID,b.XmiID);
            }
            if(b.getBlockName("internal")!=null){
                if (propertyCounter == 0){
                    output += generateStartCollaboration();
                    propertyCounter=1;
                }
                else {

                    String propertyTypeName = "";
                    System.out.print(b.name +" ");
                    propertyTypeName = sentences.getSentenceByStructNoun(b.name).structNouns.get(0);
                    String propertyTypeId = generatePropertyTypeID(blocks.getBlockByName(propertyTypeName).XmiID);

                    output += generateClassifier_Property(b.name, b.XmiID, xmiPackageID, b.ownerXMI, propertyTypeId);
                }
            }
            if(b.getBlockName("ports")!= null){
                if(propertyCounter ==1){
                    output+=generateEndCollaboration();
                    propertyCounter=0;
                }
                output += generatePort(b.name,b.XmiID, b.ownerXMI,xmiPackageID);
            }
        }
        for(Sentence s: sentences.sentences){
            if(s.sentenceType=="Connection"){
                String nouns = s.structNouns.toString();
                String noun[] = nouns.split(", ");
                output += generateAssociation(
                        noun[2],
                        noun[3].replace("]",""),
                        blocks.getBlockByName(noun[2]).XmiID,
                        blocks.getBlockByName(noun[3].replace("]","")).XmiID);
            }
            if(s.sentenceType=="Functional"){
                System.out.println("");
            }
        }

















//        for (Sentence s : sentences.sentences) {
//            if (Objects.equals(s.sentenceType, "Structural")) {
//                if(s.isInternal){
////                    System.err.println(s.structNoun);
//                    xmiOwnerID = generateXMI_ID("other");
//                    output += generateBlock(s.structNoun,xmiPackageID,xmiOwnerID);
//                    output += generateClassifier_Property(s.structNouns,xmiPackageID,xmiOwnerID);
//                }else if (s.isPort) {
////                    System.err.println(s.structNoun);
//                    xmiOwnerID = generateXMI_ID("other");
//                    output += generateBlock(s.structNoun, xmiPackageID, xmiOwnerID);
//                    output += generatePort(s.structNouns, xmiOwnerID, xmiPackageID);
//
//                }
//                else{
//                    System.out.println(s.structNoun);
//
//                }
//            } else if(Objects.equals(s.sentenceType,"Functional")){
//                System.out.println(s.structNoun);
//            } else if (Objects.equals(s.sentenceType,"Instantiation")) {
//                System.out.println(s.structNoun);
//            }else if(Objects.equals(s.sentenceType,"Connection")){
//                System.out.println(s.structNoun);
//            }
//
//        }

//        for(java.lang.String sen : s.structNouns) {
//                    if(s.isInternal){
//                        // Generate the property for the IBD, However it needs the XMI_ID of the block it instantiates
//                    } else if(s.isPort){
//                        // Generate the uml class where the ea_stype is a port
////                        System.out.println(s.structNoun);
//                        output += generatePort();
//                    }
//
////                    String xmiID = generateXMI_ID("other");
//////                  output += generateBlock(sen, xmiPackageID, xmiID);
////                    System.out.println(sen);
//
//                }
//
////                    if (s.isInternal) {
//////                    System.out.println(s.structNoun);
////                        xmiOwnerID = generateXMI_ID("other");
////                        for(java.lang.String sen : s.structNouns){
//////                        output += generateClassifier_Property(sen,xmiPackageID,xmiOwnerID);
//////                        System.out.println(sen);
////                        }
//////
////                    } else if (s.isPort) {
////                        String portXMI_ID = generateXMI_ID("other");
////                        output += generatePort(sen, xmiPackageID, xmiID);
////                        System.out.println(s.structNoun);
////                        System.out.println(s.structNouns);
////                    }
////                    System.out.println(s.structNoun);
////                    System.out.println(s.structNouns);
////                    System.out.println(s.isInternal);
//                }
//            }



        output += generateFinalContent();

//        output += generateDirection();
//        output += generateEncapsulation();
//        output += generateDiagram();


        output += generateFooter();

//        System.out.println(sentences.getSentenceByType("Structural").structNoun);
//        System.out.println(sentences.getClass().getSimpleName());
//        System.out.println(sentences.getSentenceByType("Connection").structNouns);
//        System.out.println(sentences.getSentenceByType("Instantitation").structNouns);
//        System.out.println(sentences.getSentenceByType("Functional").structNouns);
//        System.out.println(output);
        return output;
    }

    private String generatePropertyTypeID(String xmiID) {
        String ID = "";

        String tokens[] = xmiID.split("_");

        ID = tokens[1]+"-"+tokens[2]+"-"+tokens[3]+"-"+tokens[4]+"-"+tokens[5];
        return ID;
    }

    public String generateXMI_ID(String type){
            String ID;
            Random rand = new Random();
            if(Objects.equals(type, "package")) {
                ID = "EAPK_" + (rand.nextInt(99999999-10000000) + 10000000) + "_" + (rand.nextInt(9999-1000)
                        +1000)+ "_" + (rand.nextInt(9999-1000) +1000) + "_" + (rand.nextInt(9999-1000) +1000)
                        + "_" + (rand.nextInt(999999-100000) + 100000) + (rand.nextInt(999999-100000) + 100000);

            }else if (Objects.equals(type,"collab")){
                ID = "EAPK_" + (rand.nextInt(99999999-10000000) + 10000000) + "_" + (rand.nextInt(9999-1000)
                        +1000)+ "_" + (rand.nextInt(9999-1000) +1000) + "_" + (rand.nextInt(9999-1000) +1000)
                        + "_" + (rand.nextInt(999999-100000) + 100000) + (rand.nextInt(999999-100000) + 100000)+ "_Collaboration";
            } else{

                ID = "EAID_"+ (rand.nextInt(99999999-10000000) + 10000000) + "_" + (rand.nextInt(9999-1000)
                        +1000)+ "_" + (rand.nextInt(9999-1000) +1000) + "_" + (rand.nextInt(9999-1000) +1000)
                        + "_" + (rand.nextInt(999999-100000) + 100000) + (rand.nextInt(999999-100000) + 100000);
            }
            return ID;
    }
        public String generateHeader(){
            String output = "\t<XMI.header>\n" +
                    "\t\t<XMI.documentation>\n" +
                    "\t\t\t<XMI.exporter>Enterprise Architect</XMI.exporter>\n" +
                    "\t\t\t<XMI.exporterVersion>2.5</XMI.exporterVersion>\n" +
                    "\t\t</XMI.documentation>\n" +
                    "\t</XMI.header>\n";
            return output;
        }
        public String generateFooter(){
            String output ="\t</XMI.content>\n"+
                    "\t<XMI.difference/>\n"+
                    "\t<XMI.extensions xmi.extender=\"Enterprise Architect 2.5\"/>\n" +
                    "</XMI>";
            return output;
        }
    public String generateStartContent(String xmiPackageID){
//        String xmiId="", xmiRootId="",xmiPackageId="";
        //        System.err.println(sentences.getSentenceByType("Structural").structNoun);
        //        String noun = sentences.getSentenceByType("Structural").structNoun;
        String xmiID = generateXMI_ID("other");
        String xmiRootID = generateXMI_ID("other");

        String output = "\t<XMI.content>\n"+
                "\t\t<UML:Model name=\"EA Model\" xmi.id=\"" + xmiID + "\">\n"+
                "\t\t\t<UML:Namespace.ownedElement>\n"+
                "\t\t\t\t<UML:Class name=\"EARootClass\" xmi.id=\"" + xmiRootID + "\"/>\n"+
                "\t\t\t\t<UML:Package name=\"One Level Block Hierarchy\" xmi.id=\"" + xmiPackageID + "\">\n" +
                "\t\t\t\t\t<UML:Namespace.ownedElement>\n";

        //                output += generateDirection();
        //                output += generateEncapsulation();
        //                output += generateDiagram();

        return output;
    }

    public String generateFinalContent(){
        String output;
        output = "\t\t\t\t\t</UML:Namespace.ownedElement>\n"+
                "\t\t\t\t</UML:Package>\n"+
                "\t\t\t</UML:Namespace.ownedElement>\n"+
                "\t\t</UML:Model>\n";

        return output;
    }
//        public void generateContent(){
//            String xmiId="", xmiRootId="",xmiPackageId="";
//    //        System.err.println(sentences.getSentenceByType("Structural").structNoun);
//    //        String noun = sentences.getSentenceByType("Structural").structNoun;
//
//            String output = "\t<XMI.content>\n"+
//                    "\t\t<UML:Model name=\"EA Model\" xmi.id=\"" + xmiId + "\">\n"+
//                    "\t\t\t<UML:Namespace.ownedElement>\n"+
//                    "\t\t\t\t<UML:Class name=\"EARootClass\" xmi.id=\"" + xmiRootId + "\"/>"+
//                    "\t\t\t\t<UML:Package name=\"One Level Block Hierarchy\" xmi.id=\"" + xmiPackageId + "\">\n" +
//                    "\t\t\t\t\t<UML:Namespace.ownedElement>";
//    //                output += generateBlock();
//    //                output += generatePort();
//    //                output += generateAssociation();
//    //                output += generateIBD();
//
//                    output += "\t\t\t\t\t</UML:Namespace.ownedElement>"+
//                            "\t\t\t\t</UML:Package>\n"+
//                            "\t\t\t</UML:Namespace.ownedElement>\n"+
//                            "\t\t</UML:Model>\n";
//
//    //                output += generateDirection();
//    //                output += generateEncapsulation();
//    //                output += generateDiagram();
//                    output += "\t</XMI.content>\n";
//
//            return output;
//        }


        public String generatePort(String noun,String xmiID,String xmiOwnerID, String xmiPackageID){
            String output = "";

                output += "\t\t\t\t\t\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmiID + "\" namespace = \"" + xmiPackageID + "\" >\n" +
                        "\t\t\t\t\t\t\t<UML:ModelElement.taggedValue>\n" +
                        "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"ea_stype\" value = \"Port\"/>\n" +
                        "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"package\" value = \"" + xmiPackageID + "\" />\n" +
                        "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"owner\" value = \"" + xmiOwnerID + "\" />\n" +
                        "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
                        "\t\t\t\t\t\t\t</UML:ModelElement.taggedValue>\n" +
                        "\t\t\t\t\t\t</UML:Class>\n";


            return output;
        }

        public String generateBlock(String noun, String xmiPackageID, String xmiID ){


            String output = "\t\t\t\t\t\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmiID + "\" namespace = \"" + xmiPackageID + "\" >\n" +
                    "\t\t\t\t\t\t\t<UML:ModelElement.stereotype>\n" +
                    "\t\t\t\t\t\t\t\t<UML:Stereotype name = \"block\"/>\n" +
                    "\t\t\t\t\t\t\t</UML:ModelElement.stereotype>\n" +
                    "\t\t\t\t\t\t\t<UML:ModelElement.taggedValue>\n" +
                    "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"ea_stype\" value = \"Class\"/>\n" +
                    "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"package\" value = \"" + xmiPackageID  + "\"/>\n" +
                    "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
                    "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"stereotype\" value = \"block\"/>\n" +
                    "\t\t\t\t\t\t\t</UML:ModelElement.taggedValue>\n" +
                    "\t\t\t\t\t\t</UML:Class>\n";

            return output;
        }

        public String generateAssociation(String srcNoun,String destNoun, String srcXmiId, String destXmiId){
            String xmiId = generateXMI_ID("other");
            String output = "\t\t\t\t\t\t<UML:Association xmi.id=\"" + xmiId + "\">\n"+
                "\t\t\t\t\t\t\t<UML:ModelElement.taggedValue>\n"+
                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"ea_type\" value=\"Connector\"/>\n"+
                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"direction\" value=\"Source -&gt; Destination\"/>\n"+
                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"ea_sourceName\" value=\"" + srcNoun +"\"/>\n"+
                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"ea_targetName\" value=\"" + destNoun +"\"/>\n"+
                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"ea_sourceType\" value=\"Port\"/>\n" +
                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"ea_targetType\" value=\"Port\"/>\n"+
                "\t\t\t\t\t\t\t</UML:ModelElement.taggedValue>\n"+
                "\t\t\t\t\t\t\t<UML:Association.connection>\n" +
                "\t\t\t\t\t\t\t\t<UML:AssociationEnd type=\"" + srcXmiId + "\">\n" +
                "\t\t\t\t\t\t\t\t</UML:AssociationEnd>\n" +
                "\t\t\t\t\t\t\t\t<UML:AssociationEnd type=\""+ destXmiId + "\">\n"+
                "\t\t\t\t\t\t\t\t</UML:AssociationEnd>"+ "\">\n"+
                "\t\t\t\t\t\t\t</UML:Association.connection>\n"+
                "\t\t\t\t\t\t</UML:Association>\n";


            return output;
        }
        public String generateIBD_Block(String xmiIdIBD) {
            String append_output = "\t\t\t<UML:TaggedValue tag=\"ea_ntype\" value=\"8\"/>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"ea_ntype\" value=\"8\"/>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"diagram\" value=\"" + xmiIdIBD + "\"/>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"$ea_xref_property\" value=\"\"$XREFPROP=$XID={" +
                    "number {001EC013-2161-4f09-8362-A1A5114E8E8A}$XID}\"};$NAM=DefaultDiagram$NAM;$TYP=element property$TYP;$SUP={" +
                    "number: {9E656A80-A25F-4ae3-A153-749CEFFFA468}\"}$SUP;$ENDXREF;\"/>\n";

            return append_output;
        }
        public String generateStartCollaboration(){
            String xmiID_Collboration;

            xmiID_Collboration = generateXMI_ID("collab");
            String output = "\t\t\t\t\t\t<UML:Collaboration xmi.id =\"" + xmiID_Collboration + "\" name =\"Collaborations\">\n";
            output += "\t\t\t\t\t\t\t<UML:Namespace.ownedElement>\n";


            return output;
         }

        public String generateEndCollaboration(){
            String output = "";
            output += "\t\t\t\t\t\t\t</UML:Namespace.ownedElement>\n";
            output += "\t\t\t\t\t\t\t<UML:Collaboration.interaction/>\n";
            output += "\t\t\t\t\t\t</UML:Collaboration>\n";

            return output;
        }
        public String generateClassifier_Property(String noun, String xmiID, String xmiPackageID, String xmiOwnerID, String xmiPropertyTypeID) {
//
            String output = "\t\t\t\t\t\t\t\t<UML:ClassifierRole name =\"" + noun + "\" xmi.id =\"" + xmiID + "\" base =\"" + xmiPackageID + "\">\n" +
                    "\t\t\t\t\t\t\t\t\t<UML:ModelElement.taggedValue>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"ea_stype\" value=\"Part\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"package\" value=\"" + xmiPackageID + "\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"owner\" value = \"" + xmiOwnerID + "\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"package_name\" value=\"One Level Block Hierarchy\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"propertyType\" value=\"{" + xmiPropertyTypeID + "}\"/>\n" +
                    "\t\t\t\t\t\t\t\t\t</UML:ModelElement.taggedValue>\n" +
                    "\t\t\t\t\t\t\t\t</UML:ClassifierRole>\n";


            return output;
        }
        public String generateClassifier_Flow(String noun, String xmiId, String xmiPackageId, String xmiOwnerId, String xmiIdBoolean) {
            String output = "\t<UML:ClassifierRole name =\"" + noun + "\" xmi.id =\"" + xmiId + "\" base =\"" + xmiPackageId + "\">\n" +
                    "\t\t<UML:ModelElement.stereotype>\n"+
                    "\t\t\t<UML:Stereotype name=\"FlowProperty\"/>" +
                    "\t\t</UML:ModelElement.stereotype>"+
                    "\t\t<UML:ModelElement.taggedValue>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"ea_stype\" value=\"Part\"/>\n" +
                    "\t\t\t<UML:TaggedValue tag= \"package\" value=\"" + xmiPackageId + "/>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"owner\" value = \"" + xmiOwnerId + "\">\n" +
                    "\t\t\t<UML:TaggedValue tag=\"package_name\" value=\"One Level Block Hierarchy\"/>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"stereotype\" value=\"FlowProperty\">"+
                    "\t\t\t<UML:TaggedValue tag=\"propertyType\" value=\"{" + xmiIdBoolean + "}\"/>\n" +
                    "\t\t</UML:ModelElement.taggedValue>\n" +
                    "\t</UML:ClassifierRole>\n";

            return output;
        }

        public String generateIBD(String noun, String xmiId, String xmiPackageId, String xmiOwnerId, String xmiIdBoolean, String xmiIdCollaboration) {
            String output = "\t<UML:Collaboration xmi.id =\"" + xmiIdCollaboration + "\" name =\"Collaborations\">\n";
            output += "\t\t<UML:Namespace.ownedElement>\n";
//            output += generateClassifier_Property(noun, xmiId, xmiPackageId, xmiOwnerId);

    //        if direction == TRUE
                    output += generateClassifier_Flow(noun, xmiId, xmiPackageId, xmiOwnerId, xmiIdBoolean);

            output += "\t\t</UML:Namespace.ownedElement>\n";
            output += "\t\t<UML:Collaboration.interaction/>\n";
            output += "\t</UML:Collaboration>\n";
            return output;
        }

        public String generateDirection(String xmiId, String direction){
            String output = "\t<UML:TaggedValue tag=\"direction\" xmi.id=\"" + xmiId + "\" value=\"" + direction + "#NOTES#Values: in,out,inout,none;\" modelElement=\"" + xmiId+ "\"/>\n";
            return output;
        }

        public String generateEncapsulation(String xmiId){
            String output = "\t<UML:TaggedValue tag=\"isEncapsulated\" xmi.id=\"" + xmiId + "\" value=\"#NOTES#Values: true,false&#xA;\" modelElement=\"" + xmiId + "\"/>\n";
            return output;
        }
        public String generateDiagram(){
            String output = generateDiagramBDD();
    //        if (internal==TRUE) {
    //            output += generateDiagramIBD();
    //        }
            return output;
        }

        public String generateDiagramBDD(){
            String noun="",xmiId="", xmiRootId="",xmiPackageId="",ownerXmiId="", xmiPackage="";
            String output = "\t<UML:Diagram name = \"One Level Block Hierarchy\" xmi.id=\"" + xmiId + "\" diagramType = \"ClassDiagram\" owner=\"" + xmiId + "\" toolName = \"Enterprise Architect 2.5\">\n" +
                    "\t\t<UML:ModelElement.taggedValue>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"package\" value=\"" + xmiPackage + "\">\n"+
                    "\t\t\t<UML:TaggedValue tag=\"styleex\" value=\"MDGDgm=SysML1.4::BlockDefinition;SF=1;\"/>\n"+
                    "\t\t</UML:ModelElement.taggedValue>\n"+
                    "\t\t<UML:Diagram.element>\n";
    //                for(amountOfFiguresToBeDrawn){
    //                    if (block) {
    //                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=500;Top=150;Right=690;Bottom=190;\" subject=\"" + xmiId + "\"/>\n";
    //                    }if (port) {
    //                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=300;Top=400;Right=400;Bottom=300;\" subject=\"" + xmiId + "\"/>\n";
    //                    }
    //                }
                    output += "\t\t</UML:Diagram.element>\n"+
                            "\t</UML:Diagram>\n";

            return output;

        }

        public String generateDiagramIBD(String noun, String xmiId, String xmiPackage){
            String output = "\t<UML:Diagram name=\"" + noun +"\" xmi.id=\"" + xmiId+ "\" diagramType=\"CompositeStructureDiagram\" owner=\"" + xmiPackage + "\" toolName=\"Enterprise Architect 2.5\">\n"+
                    "\t\tUML:ModelElement.taggedValue>\n"+
                    "\t\t\t<UML:TaggedValue tag=\"package\" value=\"" + xmiPackage + "\"/>\n"+
                    "\t\t\t<UML:TaggedValue tag=\"type\" value=\"CompositeStructure\"/>\n"+
                    "\t\t\t<UML:TaggedValue tag=\"ea_localid\" value=\"2\"/>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"parent\" value=\"" + xmiId + "\"/>\n" +
                    "\t\t\t<UML:TaggedValue tag=\"styleex\" value=\"MDGDgm=SysML1.4::InternalBlock;SF=1;\"/>"+
                    "\t\t</UML:ModelElement.taggedValue>\n"+
                    "\t\t<UML:Diagram.element>\n";
    //                for(amountOfFiguresToBeDrawn) {
    //                    if (port) {
    //                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=543;Top=202;Right=558;Bottom=217;\" subject=\"" + xmiId + "\"/>\n";
    //                    }if (property) {
    //                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=699;Top=129;Right=714;Bottom=144;\" subject=\"" + xmiId + "\"/>\n";
    //                    }if (connection){
    //                        output += "\t\t\t<UML:DiagramElement geometry=\"SX=0;SY=0;EX=0;EY=0;EDGE=2;$LLB=;LLT=;LMT=;LMB=;LRT=;LRB=;IRHS=;ILHS=;Path=;\" subject=\"" + xmiId + "\"/>\n";
    //                    }
    //                }
                    output += "\t\t</UML:Diagram.element>\n"+
                            "\t</UML:Diagram>\n";
            return output;
        }



}
