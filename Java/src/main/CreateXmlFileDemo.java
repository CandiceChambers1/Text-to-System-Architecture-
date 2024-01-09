package main;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
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
    public String generateOutput() throws ParserConfigurationException {

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

        Attr attr = doc.createAttribute("xmi.version");
        attr.setValue("1.1");
        rootElement.setAttributeNode(attr);

        Attr attr1 = doc.createAttribute("xmlns:UML");
        attr1.setValue("omg.org/UML1.3");
        rootElement.setAttributeNode(attr1);

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
                generatePort(b.name,b.XmiID, b.ownerXMI,xmiPackageID);
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
                            output+=generateAssociation(src,dest,p1.XmiID,p2.XmiID);
                        }
                        else if(blocks.getPortProperty(src, blocks.getXMI(s.structNoun))!=null && blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun)) ==null){
                            p1 = blocks.getPortProperty(src, blocks.getXMI(s.structNoun));
                            s1 = blocks.getXMI(dest);
                            output+=generateAssociation(src,dest,p1.XmiID,s1);
                        }
                        else if(blocks.getPortProperty(src, blocks.getXMI(s.structNoun))==null && blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun)) !=null){
                            s1 = blocks.getXMI(src);
                            p2 = blocks.getPortProperty(dest, blocks.getXMI(s.connectionNoun));
                            output+=generateAssociation(src,dest,s1, p2.XmiID);
                        }
                    } else {
                        continue;

                    }
                }

            }
        }

        output += generateFinalContent();

//        output += generateDirection();
        for (Block b : blocks.blocks) {
            if (b.getBlockName("sub") != null) {
                output += generateEncapsulation(generateXMI_ID("other"),b.XmiID);
            }
        }

        output += generateDiagram(generateXMI_ID("other"),xmiPackageID);
        generateFooter(doc,rootElement);

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
    public void generateHeader(Document doc, Element root){
        Element header = doc.createElement("XMI.header");
        root.appendChild(header);
        Element documentation = doc.createElement("XMI.documentation");
        header.appendChild(documentation);

        Element exporter = doc.createElement("XMI.exporter");
        exporter.appendChild(doc.createTextNode("Enterprise Architect"));
        documentation.appendChild(exporter);

        Element exporterVersion = doc.createElement("XMI.exporterVersion");
        exporterVersion.appendChild(doc.createTextNode("2.5"));
        documentation.appendChild(exporterVersion);


//        String output = "\t<XMI.header>\n" +
//                "\t\t<XMI.documentation>\n" +
//                "\t\t\t<XMI.exporter>Enterprise Architect</XMI.exporter>\n" +
//                "\t\t\t<XMI.exporterVersion>2.5</XMI.exporterVersion>\n" +
//                "\t\t</XMI.documentation>\n" +
//                "\t</XMI.header>\n";
//        return output;
    }
    public void generateFooter(Document doc, Element root){
        Element difference = doc.createElement("XMI.difference");
        root.appendChild(difference);

        Element extension = doc.createElement("XMI.extension");
        Attr attrType = doc.createAttribute("xmi.extender");
        attrType.setValue("Enterprise Architect 2.5");
        extension.setAttributeNode(attrType);
        doc.appendChild(extension);

//        String output ="\t</XMI.content>\n"+
//                "\t<XMI.difference/>\n"+
//                "\t<XMI.extensions xmi.extender=\"Enterprise Architect 2.5\"/>\n" +
//                "</XMI>";
//        return output;
    }
    public Element generateStartContent(Document doc, Element root, String xmiPackageID){

        modelxmiID = generateXMI_ID("other");
        xmiRootID = generateXMI_ID("other");

        Element content = doc.createElement("XMI.content");
        root.appendChild(content);

        Element model = doc.createElement("UML:Model");
        Attr attr1 = doc.createAttribute("name");
        attr1.setValue("EA Model");
        model.setAttributeNode(attr1);

        Attr attr2 = doc.createAttribute("xmi.id");
        attr2.setValue("MX_"+modelxmiID);
        model.setAttributeNode(attr2);

        Element namespace = doc.createElement("UML:Namespace.ownedElement");
        model.appendChild(namespace);

        Element umlClass = doc.createElement("UML:Class");
        Attr attr3 = doc.createAttribute("name");
        attr3.setValue("EARootClass");
        umlClass.setAttributeNode(attr3);

        Attr attr4 = doc.createAttribute("xmi.id");
        attr4.setValue(xmiRootID);
        umlClass.setAttributeNode(attr4);
        namespace.appendChild(umlClass);

        Element umlPackage = doc.createElement("UML:Package");
        Attr attr5 = doc.createAttribute("name");
        attr5.setValue("One Level Block Hierarchy");
        umlPackage.setAttributeNode(attr5);

        Attr attr6 = doc.createAttribute("xmi.id");
        attr6.setValue(xmiPackageID);
        umlPackage.setAttributeNode(attr6);

        namespace.appendChild(umlPackage);

        Element namespace1 = doc.createElement("UML:Namespace.ownedElement");
        umlPackage.appendChild(namespace1);



//        String output = "\t<XMI.content>\n"+
//                "\t\t<UML:Model name=\"EA Model\" xmi.id=\"MX_" + modelxmiID + "\">\n"+
//                "\t\t\t<UML:Namespace.ownedElement>\n"+
//                "\t\t\t\t<UML:Class name=\"EARootClass\" xmi.id=\"" + xmiRootID + "\"/>\n"+
//                "\t\t\t\t<UML:Package name=\"One Level Block Hierarchy\" xmi.id=\"" + xmiPackageID + "\">\n" +
//                "\t\t\t\t\t<UML:Namespace.ownedElement>\n";

        //                output += generateDirection();
        //                output += generateEncapsulation();
        //                output += generateDiagram();

        return namespace1;
    }
    public void generatePort(Document doc, Element namespace, String noun,String xmiID,String xmiOwnerID, String xmiPackageID){
        Element umlClass = doc.createElement("UML:Class");
        Attr attr1 = doc.createAttribute("noun");
        attr1.setValue(noun);
        umlClass.setAttributeNode(attr1);

        Attr attr2 = doc.createAttribute("xmi.id");
        attr2.setValue(xmiID);
        umlClass.setAttributeNode(attr2);

        Attr attr3 = doc.createAttribute("namespace");
        attr3.setValue(xmiPackageID);
        umlClass.setAttributeNode(attr3);

        namespace.appendChild(umlClass);

        Element modelElementTag = doc.createElement("UML:ModelElement.taggedValue");
        umlClass.appendChild(modelElementTag);

        Element tag1 = doc.createElement("UML:TaggedValue");
        Attr attr5 = doc.createAttribute("tag");
        attr5.setValue("ea_stype");
        tag1.setAttributeNode(attr5);

        Attr attr6 = doc.createAttribute("value");
        attr6.setValue("Port");
        tag1.setAttributeNode(attr6);
        modelElementTag.appendChild(tag1);

        Element tag2 = doc.createElement("UML:TaggedValue");
        Attr attr7 = doc.createAttribute("tag");
        attr7.setValue("package");
        tag2.setAttributeNode(attr7);

        Attr attr8 = doc.createAttribute("value");
        attr8.setValue(xmiPackageID);
        tag2.setAttributeNode(attr8);
        modelElementTag.appendChild(tag2);

        Element tag3 = doc.createElement("UML:TaggedValue");
        Attr attr9 = doc.createAttribute("tag");
        attr9.setValue("owner");
        tag3.setAttributeNode(attr9);

        Attr attr10 = doc.createAttribute("value");
        attr10.setValue(xmiOwnerID);
        tag3.setAttributeNode(attr10);
        modelElementTag.appendChild(tag3);

        Element tag4 = doc.createElement("UML:TaggedValue");
        Attr attr11 = doc.createAttribute("tag");
        attr11.setValue("package_name");
        tag4.setAttributeNode(attr11);

        Attr attr12 = doc.createAttribute("value");
        attr12.setValue("One Level Block Hierarchy");
        tag4.setAttributeNode(attr12);
        modelElementTag.appendChild(tag4);

//        String output = "";
//
//        output += "\t\t\t\t\t\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmiID + "\" namespace = \"" + xmiPackageID + "\" >\n" +
//                "\t\t\t\t\t\t\t<UML:ModelElement.taggedValue>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"ea_stype\" value = \"Port\"/>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"package\" value = \"" + xmiPackageID + "\" />\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"owner\" value = \"" + xmiOwnerID + "\" />\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
//                "\t\t\t\t\t\t\t</UML:ModelElement.taggedValue>\n" +
//                "\t\t\t\t\t\t</UML:Class>\n";
//        return output;
    }
    public void generatePortProperty(Document doc, Element namespace, String noun,String xmiID,String xmiOwnerID, String xmiPackageID, String reuseProperty){
        Element umlClass = doc.createElement("UML:Class");
        Attr attr1 = doc.createAttribute("noun");
        attr1.setValue(noun);
        umlClass.setAttributeNode(attr1);

        Attr attr2 = doc.createAttribute("xmi.id");
        attr2.setValue(xmiID);
        umlClass.setAttributeNode(attr2);

        Attr attr3 = doc.createAttribute("namespace");
        attr3.setValue(xmiPackageID);
        umlClass.setAttributeNode(attr3);

        namespace.appendChild(umlClass);

        Element modelElementTag = doc.createElement("UML:ModelElement.taggedValue");
        umlClass.appendChild(modelElementTag);

        Element tag1 = doc.createElement("UML:TaggedValue");
        Attr attr5 = doc.createAttribute("tag");
        attr5.setValue("ea_stype");
        tag1.setAttributeNode(attr5);

        Attr attr6 = doc.createAttribute("value");
        attr6.setValue("Port");
        tag1.setAttributeNode(attr6);
        modelElementTag.appendChild(tag1);

        Element tag2 = doc.createElement("UML:TaggedValue");
        Attr attr7 = doc.createAttribute("tag");
        attr7.setValue("package");
        tag2.setAttributeNode(attr7);

        Attr attr8 = doc.createAttribute("value");
        attr8.setValue(xmiPackageID);
        tag2.setAttributeNode(attr8);
        modelElementTag.appendChild(tag2);

        Element tag3 = doc.createElement("UML:TaggedValue");
        Attr attr9 = doc.createAttribute("tag");
        attr9.setValue("owner");
        tag3.setAttributeNode(attr9);

        Attr attr10 = doc.createAttribute("value");
        attr10.setValue(xmiOwnerID);
        tag3.setAttributeNode(attr10);
        modelElementTag.appendChild(tag3);

        Element tag4 = doc.createElement("UML:TaggedValue");
        Attr attr11 = doc.createAttribute("tag");
        attr11.setValue("package_name");
        tag4.setAttributeNode(attr11);

        Attr attr12 = doc.createAttribute("value");
        attr12.setValue("One Level Block Hierarchy");
        tag4.setAttributeNode(attr12);
        modelElementTag.appendChild(tag4);

        Element tag5 = doc.createElement("UML:TaggedValue");
        Attr attr13 = doc.createAttribute("tag");
        attr13.setValue("reusesProperty");
        tag5.setAttributeNode(attr13);

        Attr attr14 = doc.createAttribute("value");
        attr14.setValue("{" + reuseProperty + "}");
        tag5.setAttributeNode(attr14);
        modelElementTag.appendChild(tag5);


//        String output = "";
//
//        output += "\t\t\t\t\t\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmiID + "\" namespace = \"" + xmiPackageID + "\" >\n" +
//                "\t\t\t\t\t\t\t<UML:ModelElement.taggedValue>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"ea_stype\" value = \"Port\"/>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"package\" value = \"" + xmiPackageID + "\" />\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"owner\" value = \"" + xmiOwnerID + "\" />\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag = \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag=\"reusesProperty\" value=\"{" + reuseProperty +"}\"/>\n" +
//                "\t\t\t\t\t\t\t</UML:ModelElement.taggedValue>\n" +
//                "\t\t\t\t\t\t</UML:Class>\n";
//
//
//        return output;
    }
    public void generateBlock(Document doc, Element namespace, String noun, String xmiPackageID, String xmiID, boolean isInternal ){
        Element umlClass = doc.createElement("UML:Class");
        Attr attr1 = doc.createAttribute("noun");
        attr1.setValue(noun);
        umlClass.setAttributeNode(attr1);

        Attr attr2 = doc.createAttribute("xmi.id");
        attr2.setValue(xmiID);
        umlClass.setAttributeNode(attr2);

        Attr attr3 = doc.createAttribute("namespace");
        attr3.setValue(xmiPackageID);
        umlClass.setAttributeNode(attr3);

        namespace.appendChild(umlClass);

        Element modelElementStereo = doc.createElement("UML:ModelElement.stereotype");
        umlClass.appendChild(modelElementStereo);

        Element stereotype = doc.createElement("UML:Stereotype");
        Attr attr4 = doc.createAttribute("name");
        attr4.setValue("block");
        stereotype.setAttributeNode(attr4);
        modelElementStereo.appendChild(stereotype);

        Element modelElementTag = doc.createElement("UML:ModelElement.taggedValue");
        umlClass.appendChild(modelElementTag);

        Element tag1 = doc.createElement("UML:TaggedValue");
        Attr attr5 = doc.createAttribute("tag");
        attr5.setValue("ea_stype");
        tag1.setAttributeNode(attr5);

        Attr attr6 = doc.createAttribute("value");
        attr6.setValue("Class");
        tag1.setAttributeNode(attr6);
        modelElementTag.appendChild(tag1);

        Element tag2 = doc.createElement("UML:TaggedValue");
        Attr attr7 = doc.createAttribute("tag");
        attr7.setValue("package");
        tag2.setAttributeNode(attr7);

        Attr attr8 = doc.createAttribute("value");
        attr8.setValue(xmiPackageID);
        tag2.setAttributeNode(attr8);
        modelElementTag.appendChild(tag2);

        Element tag3 = doc.createElement("UML:TaggedValue");
        Attr attr9 = doc.createAttribute("tag");
        attr9.setValue("package_name");
        tag3.setAttributeNode(attr9);

        Attr attr10 = doc.createAttribute("value");
        attr10.setValue("One Level Block Hierarchy");
        tag3.setAttributeNode(attr10);
        modelElementTag.appendChild(tag3);

        Element tag4 = doc.createElement("UML:TaggedValue");
        Attr attr11 = doc.createAttribute("tag");
        attr11.setValue("stereotype");
        tag4.setAttributeNode(attr11);

        Attr attr12 = doc.createAttribute("value");
        attr12.setValue("block");
        tag4.setAttributeNode(attr12);
        modelElementTag.appendChild(tag4);





//        String output = "\t\t\t\t\t\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmiID + "\" namespace = \"" + xmiPackageID + "\" >\n" +
//                "\t\t\t\t\t\t\t<UML:ModelElement.stereotype>\n" +
//                "\t\t\t\t\t\t\t\t<UML:Stereotype name = \"block\"/>\n" +
//                "\t\t\t\t\t\t\t</UML:ModelElement.stereotype>\n" +
//                "\t\t\t\t\t\t\t<UML:ModelElement.taggedValue>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"ea_stype\" value = \"Class\"/>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"package\" value = \"" + xmiPackageID  + "\"/>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
//                "\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"stereotype\" value = \"block\"/>\n";
        if(isInternal){
            diagramID = generateXMI_ID("other");
            Element tag5 = doc.createElement("UML:TaggedValue");
            Attr attr13 = doc.createAttribute("tag");
            attr13.setValue("diagram");
            tag5.setAttributeNode(attr13);

            Attr attr14 = doc.createAttribute("value");
            attr14.setValue(diagramID);
            tag5.setAttributeNode(attr12);
            modelElementTag.appendChild(tag5);

//            output+="\t\t\t\t\t\t\t\t<UML:TaggedValue tag= \"diagram\" value = \""+diagramID+"\"/>\n";
        }
//        output+=        "\t\t\t\t\t\t\t</UML:ModelElement.taggedValue>\n" +
//                "\t\t\t\t\t\t</UML:Class>\n";

//        return output;
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
    public Element generateStartCollaboration(Document doc, Element namespace){

        Element collaboration = doc.createElement("UML:Collaboration");
        Attr attr1 = doc.createAttribute("xmi.id");
        attr1.setValue(modelxmiID + "_Collaboration");
        collaboration.setAttributeNode(attr1);

        Attr attr2 = doc.createAttribute("name");
        attr2.setValue("Collaborations");
        collaboration.setAttributeNode(attr2);

        namespace.appendChild(collaboration);

        Element namespace1 = doc.createElement("UML:Namespace.ownedElement");
        collaboration.appendChild(namespace);

        return namespace1;

//        String xmiID_Collboration;
//
//        xmiID_Collboration = modelxmiID+"_Collaboration";
//        String output = "\t\t\t\t\t\t<UML:Collaboration xmi.id =\"" + xmiID_Collboration + "\" name =\"Collaborations\">\n";
//        output += "\t\t\t\t\t\t\t<UML:Namespace.ownedElement>\n";
//
//
//        return output;
    }

    public void generateEndCollaboration(Document doc, Element collaboration){
        Element collaborationInter = doc.createElement("UML:Collaboration.interaction");
        collaboration.appendChild(collaborationInter);
    }
    public String generateClassifier_Property(Document doc, Element collaboration, String noun, String xmiID, String xmiPackageID, String xmiOwnerID, String xmiPropertyTypeID) {
        Element classifier = doc.createElement("UML:ClassifierRole");

        Attr attr1 = doc.createAttribute("name");
        attr1.setValue(noun);
        classifier.setAttributeNode(attr1);

        Attr attr2 = doc.createAttribute("xmi.id");
        attr2.setValue(xmiID);
        classifier.setAttributeNode(attr2);

        Attr attr1 = doc.createAttribute("name");
        attr1.setValue(noun);
        classifier.setAttributeNode(attr1);

        Attr attr1 = doc.createAttribute("name");
        attr1.setValue(noun);
        classifier.setAttributeNode(attr1);

        collaboration.appendChild(classifier);

        String output = "\t\t\t\t\t\t\t\t<UML:ClassifierRole name =\"" + noun + "\" xmi.id =\"" + xmiID + "\" base =\"" + xmiRootID + "\">\n" +
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

    public String generateEncapsulation(String xmiId, String modelXMI_ID){
        String output = "\t<UML:TaggedValue tag=\"isEncapsulated\" xmi.id=\"" + xmiId + "\" value=\"#NOTES#Values: true,false&#10;\" modelElement=\"" + modelXMI_ID + "\"/>\n";
        return output;
    }
    public String generateDiagram(String xmiID, String xmiPackage) {
        String output = generateDiagramBDD(xmiID,xmiPackage);
        for(Sentence s: sentences.sentences){
            if(s.isInternal){
                output += generateDiagramIBD(s.structNoun,diagramID,xmiPackage,blocks.getBlockByName(s.structNoun).XmiID);
            }

        }
        //            if (true) {
//
//            }
        return output;
    }


    public String generateDiagramBDD(String xmiID, String xmiPackage){

        String output = "\t<UML:Diagram name = \"One Level Block Hierarchy\" xmi.id=\"" + xmiID + "\" diagramType = \"ClassDiagram\" owner=\"" + xmiPackage + "\" toolName = \"Enterprise Architect 2.5\">\n" +
                "\t\t<UML:ModelElement.taggedValue>\n" +
                "\t\t\t<UML:TaggedValue tag=\"package\" value=\"" + xmiPackage + "\"/>\n"+
                "\t\t\t<UML:TaggedValue tag=\"styleex\" value=\"MDGDgm=SysML1.4::BlockDefinition;SF=1;\"/>\n"+
                "\t\t</UML:ModelElement.taggedValue>\n"+
                "\t\t<UML:Diagram.element>\n";
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

        for(Block b: blocks.blocks){
            if (b.getBlockName("sub") != null) {
//                    System.out.println(b.name);
                output += "\t\t\t<UML:DiagramElement geometry=\"Left=" + b_left+ ";Top=" + b_top+ ";Right=" + b_right+";Bottom=" + b_bottom +";\" subject=\"" + b.XmiID + "\"/>\n";
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

                output += "\t\t\t<UML:DiagramElement geometry=\"Left=" + p_left + ";Top=" + p_top + ";Right=" + p_right + ";Bottom=" + p_bottom + ";\" subject=\"" + b.XmiID + "\"/>\n";
//                    output += "\t\t\t<UML:DiagramElement geometry=\"Left=200;Top=200;Right=50;Bottom=50;\" subject=\"" + b.XmiID + "\"/>\n";
//                    p_left = p_left +200;
                p_bottom = p_bottom + 20;
                p_top = p_top + 20;
            }

        }
        output += "\t\t</UML:Diagram.element>\n"+
                "\t</UML:Diagram>\n";

        return output;

    }

    public String generateDiagramIBD(String noun, String xmiId, String xmiPackage,String parentXmi){
        String nounsIndv[] = new String[0];
        String output = "\t<UML:Diagram name=\"" + noun +"\" xmi.id=\"" + xmiId+ "\" diagramType=\"CompositeStructureDiagram\" owner=\"" + xmiPackage + "\" toolName=\"Enterprise Architect 2.5\">\n"+
                "\t\t<UML:ModelElement.taggedValue>\n"+
                "\t\t\t<UML:TaggedValue tag=\"package\" value=\"" + xmiPackage + "\"/>\n"+
                "\t\t\t<UML:TaggedValue tag=\"type\" value=\"CompositeStructure\"/>\n"+
                "\t\t\t<UML:TaggedValue tag=\"ea_localid\" value=\"4\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"parent\" value=\"" + parentXmi + "\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"styleex\" value=\"MDGDgm=SysML1.4::InternalBlock;SF=1;\"/>\n"+
                "\t\t</UML:ModelElement.taggedValue>\n"+
                "\t\t<UML:Diagram.element>\n";
        if (sentences.getSentenceByStructNoun(noun).isPort) {
            ArrayList<String> BDDports = sentences.getSentenceByTypePort("Structural", sentences.getSentenceByStructNoun(noun).structNoun, true).structNouns;
            for (String port : BDDports) {
                output += "\t\t\t<UML:DiagramElement geometry=\"Left=699;Top=129;Right=714;Bottom=144;\" subject=\"" + blocks.getBlockByName(port).XmiID + "\"/>\n";
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
            output += "\t\t\t<UML:DiagramElement geometry=\"Left="+bLeft+";Top="+bTop+";Right="+bRight+";Bottom="+bBottom+";\" subject=\"" + blocks.getBlockByName(nounIndv).XmiID + "\"/>\n";
//                System.err.println(nounIndv);
            ArrayList<String> ports = sentences.getSentenceByTypePort("Structural", sentences.getSentenceByStructNoun(nounIndv).structNouns.get(0), true).structNouns;
            for (String port: ports){
//                    System.out.println(port);
                PortProperty p = blocks.getPortProperty(port,blocks.getBlockByName(nounIndv).XmiID);
                output += "\t\t\t<UML:DiagramElement geometry=\"Left="+pLeft+";Top="+pTop+";Right="+pRight+";Bottom="+pBottom+";\" subject=\"" + p.XmiID + "\"/>\n";
                pTop+=20;
                pBottom+=20;
            }
            bLeft+=200;
            bRight+=200;
        }

        output += "\t\t</UML:Diagram.element>\n"+
                "\t</UML:Diagram>\n";
        return output;
    }

}
