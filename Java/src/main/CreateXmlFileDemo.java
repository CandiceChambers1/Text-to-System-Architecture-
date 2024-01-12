package main;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
//import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class CreateXmlFileDemo {
    Sentences sentences;
    String modelxmiID;
    String xmiRootID;
    String diagramID;
    Blocks blocks = new Blocks();
    public CreateXmlFileDemo(Sentences sentences){
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
    public void generateOutput() throws ParserConfigurationException, TransformerException {

        /*
            Looping through the sentences and call the appropriate functions
         */

        String xmiPackageID, xmiOwnerID;
        int propertyCounter =0;
        xmiPackageID = generateXMI_ID("package");
//        System.out.println(xmiPackageID);


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        // root element
        Element rootElement = doc.createElement("XMI");
        doc.appendChild(rootElement);
        CreateXmlFileDemo.generateAttribute(doc, rootElement, "xmlns:UML", "omg.org/UML1.3");
        CreateXmlFileDemo.generateAttribute(doc,rootElement,"xmlns:UML","omg.org/UML1.3");

        Element collaboration = null;
//        String output = "<XMI xmi.version=\"1.1\" xmlns:UML=\"omg.org/UML1.3\">\n";
        generateHeader(doc, rootElement);
        Element namespaceContent = generateStartContent(doc, rootElement, xmiPackageID);
        generateTree();
        for (Block b : blocks.blocks) {
            if(b.getBlockName("sub")!=null){
                generateBlock(doc,namespaceContent, b.name,xmiPackageID,b.XmiID,sentences.getSentenceByStructNoun(b.name).isInternal);
            }
            if(b.getBlockName("internal")!=null){
                if (propertyCounter == 0){
                    collaboration = generateStartCollaboration(doc, namespaceContent);
                    propertyCounter=1;

                    String propertyTypeName = "";
                    propertyTypeName = sentences.getSentenceByStructNoun(b.name).structNouns.get(0);
                    String propertyTypeId = generatePropertyTypeID(blocks.getBlockByName(propertyTypeName).XmiID);
                    generateClassifier_Property(doc, collaboration, b.name, b.XmiID, xmiPackageID, b.ownerXMI, propertyTypeId);


                }
                else {
                    String propertyTypeName = "";
                    propertyTypeName = sentences.getSentenceByStructNoun(b.name).structNouns.get(0);
                    String propertyTypeId = generatePropertyTypeID(blocks.getBlockByName(propertyTypeName).XmiID);

                    generateClassifier_Property(doc, collaboration, b.name, b.XmiID, xmiPackageID, b.ownerXMI, propertyTypeId);

                }
            }
            if(b.getBlockName("ports")!= null){
                if(propertyCounter ==1){
                    generateEndCollaboration(doc, collaboration);
                    propertyCounter=0;
                }
                generatePort(doc,namespaceContent,b.name,b.XmiID, b.ownerXMI,xmiPackageID);
//                System.out.println(b.name+" "+ blocks.getNameByXMI(b.ownerXMI));

            }
        }
        for(Sentence s: sentences.sentences){
            if(s.sentenceType=="Instantiation"){
                String nouns = s.structNouns.toString().replace("[", "").replace("]", "");
                String noun = s.structNoun;
                ArrayList<String> ports = sentences.getSentenceByTypePort("Structural", nouns, true).structNouns;
                for (String port: ports){
                    String reuseProperty = blocks.getBlockByNameOwner(port, blocks.getXMI(nouns)).XmiID;
                    blocks.setPortProperty(port, generateXMI_ID("other"), blocks.getBlockByName(noun).XmiID, generatePropertyTypeID(reuseProperty));
                    PortProperty p = blocks.getPortProperty(port,blocks.getBlockByName(noun).XmiID);
                    generatePortProperty(doc, namespaceContent, p.name,p.XmiID,p.ownerXMI,xmiPackageID,p.reuseProperty);
                }
            }
            if(s.sentenceType=="Functional"){
                System.err.println("Ignored Flow Properties");
            }

        }
        for(Sentence s: sentences.sentences){
            if(s.sentenceType=="Connection"){
                String src,dest;
                ArrayList<String> nouns = s.structNouns;
                for (int i = 0; i < nouns.size(); i += 2) {
                    src = nouns.get(i);

                    if (i + 1 < nouns.size()) {
                        dest = nouns.get(i + 1);

                        PortProperty p1,p2;
                        String s1;
                        if(blocks.getPortProperty(src, blocks.getXMI(s.structNoun))!=null && blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun)) !=null) {
                            p1 = blocks.getPortProperty(src, blocks.getXMI(s.structNoun));
                            p2 = blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun));
                            generateAssociation(doc,namespaceContent,src,dest,p1.XmiID,p2.XmiID);
                        }
                        else if(blocks.getPortProperty(src, blocks.getXMI(s.structNoun))!=null && blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun)) ==null){
                            p1 = blocks.getPortProperty(src, blocks.getXMI(s.structNoun));
                            s1 = blocks.getXMI(dest);
                            generateAssociation(doc, namespaceContent,src,dest,p1.XmiID,s1);
                        }
                        else if(blocks.getPortProperty(src, blocks.getXMI(s.structNoun))==null && blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun)) !=null){
                            s1 = blocks.getXMI(src);
                            p2 = blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun));
                            generateAssociation(doc, namespaceContent,src,dest,s1, p2.XmiID);
                        }
                    } else {
                        continue;

                    }
                }

            }
        }

//        generateFinalContent();

//        output += generateDirection();
        for (Block b : blocks.blocks) {
            if (b.getBlockName("sub") != null) {
                generateEncapsulation(doc,rootElement,generateXMI_ID("other"),b.XmiID);
            }
        }
        generateDiagram(doc,rootElement,generateXMI_ID("other"),xmiPackageID);
        generateFooter(doc,rootElement);

        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        String fileName = getRoot();
        StreamResult result = new StreamResult(new File("src/xml/" + fileName +".xml"));
        transformer.transform(source, result);

        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);


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
    public static Element generateElement(Document doc, Element elem, String sub, String text){
        Element element = doc.createElement(sub);
        elem.appendChild(element);

        if(text.isEmpty()){
            elem.appendChild(element);
        }else {
            element.appendChild(doc.createTextNode(text));
            elem.appendChild(element);
        }

        return element;
    }

    public static void generateAttribute(Document doc, Element elem, String sub, String text){
        Attr attr = doc.createAttribute(sub);
        attr.setValue(text);
        elem.setAttributeNode(attr);
    }

    public void generateHeader(Document doc, Element root){
        Element header = generateElement(doc, root, "XMI.header", "");
        Element documentation = generateElement(doc, header, "XMI.documentation", "");
        Element exporter = generateElement(doc, documentation, "XMI.documentation","Enterprise Architect");
        Element exporterVersion = generateElement(doc, documentation,"XMI.exporterVersion", "2.5");
    }
    public void generateFooter(Document doc, Element root){
        Element difference = generateElement(doc,root,"XMI.difference", "");
        Element extension = generateElement(doc, root,"XMI.extension", "");
        generateAttribute(doc,extension,"xmi.extension","Enterprise Architect 2.5");
    }
    public Element generateStartContent(Document doc, Element root, String xmiPackageID){

        modelxmiID = generateXMI_ID("other");
        xmiRootID = generateXMI_ID("other");

        Element content = generateElement(doc,root,"XMI.content", "");

        Element model = generateElement(doc,content,"UML:Model","");
        generateAttribute(doc,model,"name","EA Model");
        generateAttribute(doc,model,"xmi.id", "MX_"+modelxmiID);

        Element namespace = generateElement(doc,model,"UML:Namespace.ownedElement","");
        Element umlClass = generateElement(doc,namespace,"UML:Class","");
        generateAttribute(doc,umlClass,"name","EARootClass");
        generateAttribute(doc,umlClass,"xmi.id",xmiRootID);

        Element umlPackage = generateElement(doc,namespace,"UML:Package","");
        generateAttribute(doc,umlPackage,"name","One Level Block Hierarchy");
        generateAttribute(doc,umlPackage,"xmi.id",xmiPackageID);

        return generateElement(doc,umlPackage,"UML:Namespace.ownedElement","");

    }
    public void generatePort(Document doc, Element namespace, String noun,String xmiID,String xmiOwnerID, String xmiPackageID){
        Element umlClass = generateElement(doc,namespace,"UML:Class","");
        generateAttribute(doc,umlClass,"name",noun);
        generateAttribute(doc,umlClass,"xmi.id",xmiID);
        generateAttribute(doc,umlClass,"namespace",xmiPackageID);

        Element modelElementTag = generateElement(doc,umlClass,"UML:ModelElement.taggedValue","");

        Element tag_1 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","ea-stype");
        generateAttribute(doc,tag_1,"value","Port");

        Element tag_2 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_2,"tag","package");
        generateAttribute(doc,tag_2,"value",xmiPackageID);

        Element tag_3 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_3,"tag","owner");
        generateAttribute(doc,tag_3,"value",xmiOwnerID);

        Element tag_4 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_4,"tag","package_name");
        generateAttribute(doc,tag_4,"value","One Level Block Hierarchy");

    }
    public void generatePortProperty(Document doc, Element namespace, String noun,String xmiID,String xmiOwnerID, String xmiPackageID, String reuseProperty){
        Element umlClass = generateElement(doc,namespace,"UML:Class","");
        generateAttribute(doc,umlClass,"name",noun);
        generateAttribute(doc,umlClass, "xmi.id",xmiID);
        generateAttribute(doc,umlClass,"namespace",xmiPackageID);


        Element modelElementTag = generateElement(doc,umlClass,"UML:ModelElement.taggedValue","");
        Element tag_1 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","ea_stype");
        generateAttribute(doc,tag_1,"value","Port");

        Element tag_2 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_2,"tag","package");
        generateAttribute(doc,tag_2,"value",xmiPackageID);

        Element tag_3 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_3,"tag","owner");
        generateAttribute(doc,tag_3,"value",xmiOwnerID);

        Element tag_4 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_4,"tag","package_name");
        generateAttribute(doc,tag_4,"value","One Level Block Hierarchy");

        Element tag_5 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_5,"tag","reusesProperty");
        generateAttribute(doc,tag_5,"value","{"+reuseProperty+"}");
    }
    public void generateBlock(Document doc, Element namespace, String noun, String xmiPackageID, String xmiID, boolean isInternal ){

        Element umlClass = generateElement(doc,namespace,"UML:Class", "");
        generateAttribute(doc,umlClass,"name",noun);
        generateAttribute(doc,umlClass,"xmi.id",xmiID);
        generateAttribute(doc,umlClass,"namespace",xmiPackageID);

        Element modelElementStereo = generateElement(doc,umlClass,"UML:ModelElement.stereotype","");
        Element stereotype = generateElement(doc,modelElementStereo,"UML:Stereotype","");
        generateAttribute(doc,stereotype,"name","block");

        Element modelElementTag = generateElement(doc,umlClass,"UML:ModelElement.taggedValue","");

        Element tag_1 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","ea_stype");
        generateAttribute(doc,tag_1,"value","class");

        Element tag_2 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_2,"tag","package");
        generateAttribute(doc,tag_2,"value",xmiPackageID);

        Element tag_3 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_3,"tag","package_name");
        generateAttribute(doc,tag_3,"value","One Level Block Hierarchy");

        Element tag_4 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
        generateAttribute(doc,tag_4,"tag","stereotype");
        generateAttribute(doc,tag_4,"value","block");

        if(isInternal){
            diagramID = generateXMI_ID("other");
            Element tag_5 = generateElement(doc,modelElementTag,"UML:TaggedValue","");
            generateAttribute(doc,tag_5,"tag","diagram");
            generateAttribute(doc,tag_5,"value",diagramID);

        }
    }

    public void generateAssociation(Document doc, Element namespace, String srcNoun,String destNoun, String srcXmiId, String destXmiId){
        String xmiId = generateXMI_ID("other");
        Element association = generateElement(doc,namespace,"UML:Association","");
        generateAttribute(doc,association,"xmi.id",xmiId);

        Element model = generateElement(doc,association,"UML:ModelElement.taggedValue","");
        Element tag_1 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","ea_type");
        generateAttribute(doc,tag_1,"value","Connector");

        Element tag_2 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_2,"tag","direction");
        generateAttribute(doc,tag_2,"value","Source -&gt; Destination");

        Element tag_3 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_3,"tag","ea_sourceName");
        generateAttribute(doc,tag_3,"value",srcNoun);

        Element tag_4 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_4,"tag","ea_targetName");
        generateAttribute(doc,tag_4,"value",destNoun);

        Element tag_5 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_5,"tag","ea_sourceType");
        generateAttribute(doc,tag_5,"value","Port");

        Element tag_6 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_6,"tag","ea_targetType");
        generateAttribute(doc,tag_6,"value","Port");

        Element associationConnection = generateElement(doc,association,"UML:Association.connection","");
        Element associationEnd_1 = generateElement(doc,associationConnection,"UML:AssociationEnd","");
        generateAttribute(doc,associationEnd_1,"type",srcXmiId);

        Element associationEnd_2 = generateElement(doc,associationConnection,"UML:AssociationEnd","");
        generateAttribute(doc,associationEnd_2,"type",destXmiId);

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
    public Element generateStartCollaboration(Document doc, Element namespace){

        Element collaboration = generateElement(doc,namespace,"UML:Collaboraion", "");
        generateAttribute(doc,collaboration,"xmi.id",modelxmiID+"_Collaboration");
        generateAttribute(doc,collaboration,"name","Collaborations");


        Element namespace1 = generateElement(doc,collaboration,"UML:Namespace.ownedElement","");
        return namespace1;
    }

    public void generateEndCollaboration(Document doc, Element collaboration){
        Element collaborationInter = generateElement(doc,collaboration,"UML:Collaboration.interaction","");
        }
    public void generateClassifier_Property(Document doc, Element collaboration, String noun, String xmiID, String xmiPackageID, String xmiOwnerID, String xmiPropertyTypeID) {

        Element classifier =  generateElement(doc,collaboration,"UML:ClassifierRole","");
        generateAttribute(doc,classifier,"name",noun);
        generateAttribute(doc,classifier,"xmi.id",xmiID);
        generateAttribute(doc,classifier,"base",xmiRootID);

        Element model = generateElement(doc,classifier,"UML:ModelElement.taggedValue","");

        Element tag_1 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","ea_stype");
        generateAttribute(doc,tag_1,"value","Part");

        Element tag_2 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_2,"tag","package");
        generateAttribute(doc,tag_2,"value",xmiPackageID);

        Element tag_3 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_3,"tag","owner");
        generateAttribute(doc,tag_3,"value",xmiOwnerID);

        Element tag_4 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_4,"tag","package_name");
        generateAttribute(doc,tag_4,"value","One Level Block Hierarchy");

        Element tag_5 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","propertyType");
        generateAttribute(doc,tag_1,"value","{"+xmiPropertyTypeID+"}");
    }
//    public String generateClassifier_Flow(String noun, String xmiId, String xmiPackageId, String xmiOwnerId, String xmiIdBoolean) {
//        String output = "\t<UML:ClassifierRole name =\"" + noun + "\" xmi.id =\"" + xmiId + "\" base =\"" + xmiPackageId + "\">\n" +
//                "\t\t<UML:ModelElement.stereotype>\n"+
//                "\t\t\t<UML:Stereotype name=\"FlowProperty\"/>" +
//                "\t\t</UML:ModelElement.stereotype>"+
//                "\t\t<UML:ModelElement.taggedValue>\n" +
//                "\t\t\t<UML:TaggedValue tag=\"ea_stype\" value=\"Part\"/>\n" +
//                "\t\t\t<UML:TaggedValue tag= \"package\" value=\"" + xmiPackageId + "/>\n" +
//                "\t\t\t<UML:TaggedValue tag=\"owner\" value = \"" + xmiOwnerId + "\">\n" +
//                "\t\t\t<UML:TaggedValue tag=\"package_name\" value=\"One Level Block Hierarchy\"/>\n" +
//                "\t\t\t<UML:TaggedValue tag=\"stereotype\" value=\"FlowProperty\">"+
//                "\t\t\t<UML:TaggedValue tag=\"propertyType\" value=\"{" + xmiIdBoolean + "}\"/>\n" +
//                "\t\t</UML:ModelElement.taggedValue>\n" +
//                "\t</UML:ClassifierRole>\n";
//
//        return output;
//    }

//    public String generateIBD(String noun, String xmiId, String xmiPackageId, String xmiOwnerId, String xmiIdBoolean, String xmiIdCollaboration) {
//        String output = "\t<UML:Collaboration xmi.id =\"" + xmiIdCollaboration + "\" name =\"Collaborations\">\n";
//        output += "\t\t<UML:Namespace.ownedElement>\n";
////            output += generateClassifier_Property(noun, xmiId, xmiPackageId, xmiOwnerId);
//
//        //        if direction == TRUE
//        output += generateClassifier_Flow(noun, xmiId, xmiPackageId, xmiOwnerId, xmiIdBoolean);
//
//        output += "\t\t</UML:Namespace.ownedElement>\n";
//        output += "\t\t<UML:Collaboration.interaction/>\n";
//        output += "\t</UML:Collaboration>\n";
//        return output;
//    }

//    public String generateDirection(String xmiId, String direction){
//        String output = "\t<UML:TaggedValue tag=\"direction\" xmi.id=\"" + xmiId + "\" value=\"" + direction + "#NOTES#Values: in,out,inout,none;\" modelElement=\"" + xmiId+ "\"/>\n";
//        return output;
//    }

    public void generateEncapsulation(Document doc, Element root, String xmiId, String modelXMI_ID){
        Element tag = generateElement(doc,root,"UML:TaggedValue","");
        generateAttribute(doc,tag,"tag","isEncapsulated");
        generateAttribute(doc,tag,"xmi.id",xmiId);
        generateAttribute(doc,tag,"value","#NOTES#Values: true,false&#10;");
        generateAttribute(doc,tag,"modelElement",modelXMI_ID);
    }
    public void generateDiagram(Document doc, Element root,String xmiID, String xmiPackage) {
        generateDiagramBDD(doc,root,xmiID,xmiPackage);
        for(Sentence s: sentences.sentences) {
            if (s.isInternal) {
                generateDiagramIBD(doc,root,s.structNoun, diagramID, xmiPackage, blocks.getBlockByName(s.structNoun).XmiID);
            }
        }
    }


    public void generateDiagramBDD(Document doc, Element root, String xmiID, String xmiPackage){

        Element diagram = generateElement(doc,root,"UML:Diagram","");
        generateAttribute(doc,diagram,"xmi.id",xmiID);
        generateAttribute(doc,diagram,"diagramType","ClassDiagram");
        generateAttribute(doc,diagram,"owner",xmiPackage);
        generateAttribute(doc,diagram,"toolName","Enterprise Architect 2.5");

        Element model = generateElement(doc,diagram,"UML:ModelElement.taggedValue","");

        Element tag_1 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","package");
        generateAttribute(doc,tag_1,"value",xmiPackage);

        Element tag_2 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_2,"tag","styleex");
        generateAttribute(doc,tag_2,"value","MDGDgm=SysML1.4::BlockDefinition;SF=1;");

        int b_left, b_right, b_top, b_bottom;
        b_left = 60;
        b_right = 180;
        b_top = 60;
        b_bottom = 180;

        int p_left, p_right, p_top, p_bottom;
        int max_bLeft = 0;
        p_left = 60;
        p_right = 160;
        p_top = 60;
        p_bottom  = 160;
        String currentXMI = " ";

        Element diagram_ele = generateElement(doc,diagram,"UML:Diagram.element","");
        for(Block b: blocks.blocks){
            if (b.getBlockName("sub") != null) {
//                    System.out.println(b.name);
                Element diagramElement_1 = generateElement(doc,diagram_ele,"UML:DiagramElement","");
                generateAttribute(doc,diagramElement_1,"geometry","Left="+b_left+";Top="+b_top+";Right="+b_right+";Bottom="+b_bottom+";");
                generateAttribute(doc,diagramElement_1,"subject",b.XmiID);
                b_left = b_left +200;
                b_right  = b_right +200;
                max_bLeft = b_left - 200;
                currentXMI += b.XmiID;
//                    System.out.println(b.name + " " + b.XmiID);
            }if (b.getBlockName("ports") !=null) {
                //                    System.out.println(b.name);
//                    System.out.println(b.name + " " + b.ownerXMI);
                p_left = max_bLeft;
                if (!b.ownerXMI.equals(currentXMI)){
                    b_left = b_left - 200;
                    p_left = b_left;
                    currentXMI = " ";
                    currentXMI += b.ownerXMI;
                }

//                    p_right = b_right;
//                    String currentXMI = b.XmiID;
                Element diagramElement_2 = generateElement(doc,diagram_ele,"UML:DiagramElement","");
                generateAttribute(doc,diagramElement_2,"geometry","Left="+p_left+";Top="+p_top+";Right="+p_right+";Bottom="+p_bottom+";");
                generateAttribute(doc,diagramElement_2,"subject",b.XmiID);

                p_bottom = p_bottom + 20;
                p_top = p_top + 20;
            }

        }
    }

    public void generateDiagramIBD(Document doc, Element root, String noun, String xmiId, String xmiPackage,String parentXmi){
        String nounsIndv[] = new String[0];

        Element diagram = generateElement(doc,root,"UML:Diagram","");
        generateAttribute(doc,diagram,"name",noun);
        generateAttribute(doc,diagram,"xmi.id",xmiId);
        generateAttribute(doc,diagram,"diagramType","CompositeStructureDiagram");
        generateAttribute(doc,diagram,"owner",xmiPackage);
        generateAttribute(doc,diagram,"toolName","Enterprise Architect 2.5");

        Element model = generateElement(doc,diagram,"UML:ModelElement.taggedValue","");

        Element tag_1 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_1,"tag","package");
        generateAttribute(doc,tag_1,"value",xmiPackage);

        Element tag_2 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_2,"tag","type");
        generateAttribute(doc,tag_2,"value","CompositeStructure");

        Element tag_3 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_3,"tag","ea-localid");
        generateAttribute(doc,tag_3,"value","4");

        Element tag_4 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_4,"tag","parent");
        generateAttribute(doc,tag_4,"value",parentXmi);

        Element tag_5 = generateElement(doc,model,"UML:TaggedValue","");
        generateAttribute(doc,tag_5,"tag","styleex");
        generateAttribute(doc,tag_5,"value","MDGDgm=SysML1.4::InternalBlock;SF=1;");

        if (sentences.getSentenceByStructNoun(noun).isPort) {
            ArrayList<String> BDDports = sentences.getSentenceByTypePort("Structural", sentences.getSentenceByStructNoun(noun).structNoun, true).structNouns;
            for (String port : BDDports) {
                Element tag_6 = generateElement(doc,model,"UML:DiagramElement","");
                generateAttribute(doc,tag_6,"geometry","Left=699;Top=129;Right=714;Bottom=144;");
                generateAttribute(doc,tag_6,"subject", blocks.getBlockByName(port).XmiID);
            }
        }
        for(Sentence s: sentences.sentences){
            if(s.isInternal && s.structNoun.equals(noun)){
                String nouns = s.structNouns.toString().replace("[", "").replace("]", "");
                nounsIndv = nouns.split(", ");
            }
        }
        int bLeft=60, bTop=60, bRight=160, bBottom=160;
        for(String nounIndv: nounsIndv) {
            int pLeft=bLeft, pTop=bTop, pRight=bRight-55, pBottom=bBottom-45;
            Element tag_7 = generateElement(doc,model,"UML:DiagramElement","");
            generateAttribute(doc,tag_7,"geometry", "Left="+bLeft+";Top="+bTop+";Right="+bRight+";Bottom="+bBottom+";");
            generateAttribute(doc,tag_7,"subject",blocks.getBlockByName(nounIndv).XmiID);
//            output += "\t\t\t<UML:DiagramElement geometry=\"Left="+bLeft+";Top="+bTop+";Right="+bRight+";Bottom="+bBottom+";\" subject=\"" + blocks.getBlockByName(nounIndv).XmiID + "\"/>\n";
//                System.err.println(nounIndv);
            ArrayList<String> ports = sentences.getSentenceByTypePort("Structural", sentences.getSentenceByStructNoun(nounIndv).structNouns.get(0), true).structNouns;
            for (String port: ports){
//                    System.out.println(port);
                PortProperty p = blocks.getPortProperty(port,blocks.getBlockByName(nounIndv).XmiID);
                Element tag_8 = generateElement(doc,model,"UML:DiagramElement","");
                generateAttribute(doc,tag_8,"geometry", "Left="+pLeft+";Top="+pTop+";Right="+pRight+";Bottom="+pBottom+";");
                generateAttribute(doc,tag_8,"subject",p.XmiID);
//                output += "\t\t\t<UML:DiagramElement geometry=\"Left="+pLeft+";Top="+pTop+";Right="+pRight+";Bottom="+pBottom+";\" subject=\"" + p.XmiID + "\"/>\n";
                pTop+=20;
                pBottom+=20;
            }
            bLeft+=200;
            bRight+=200;
        }

//        output += "\t\t</UML:Diagram.element>\n"+
//                "\t</UML:Diagram>\n";
//        return output;
    }

}
