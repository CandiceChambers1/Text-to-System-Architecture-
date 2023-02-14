package main;

public class Output {
    Sentences sentences;
    public Output(Sentences sentences){this.sentences = sentences;}

    public String generateOutput(){
        String output = "<XMI xmi.version=\"1.1\" xmlns:UML=\"omg.org/UML1.3\">\n";
        output += generateHeader();
        output += generateContent();
        output += generateFooter();
        return output;
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
        String output ="\t<XMI.difference/>\n"+
                "\t<XMI.extensions xmi.extender=\"Enterprise Architect 2.5\"/>\n" +
                "</XMI>";
        return output;
    }

    public String generateContent(){
        String noun="",xmiId="", xmiRootId="",xmiPackageId="", ownerXmiId="";
        String output = "\t<XMI.content>\n"+
                "\t\t<UML:Model name=\"EA Model\" xmi.id=\"" + xmiId + "\">\n"+
			    "\t\t\t<UML:Namespace.ownedElement>\n"+
				"\t\t\t\t<UML:Class name=\"EARootClass\" xmi.id=\"" + xmiRootId + "\"/>"+
                "\t\t\t\t<UML:Package name=\"One Level Block Hierarchy\" xmi.id=\"" + xmiPackageId + "\">\n" +
                "\t\t\t\t\t<UML:Namespace.ownedElement>";
                output += generateBlock();
                output += generatePort(noun, xmiId, xmiPackageId,ownerXmiId);
                output += generateAssociation();
                output += generateIBD();

                output += "\t\t\t\t\t</UML:Namespace.ownedElement>"+
                        "\t\t\t\t</UML:Package>\n"+
                        "\t\t\t</UML:Namespace.ownedElement>\n"+
                        "\t\t</UML:Model>\n";

                output += generateDirection();
                output += generateEncapsulation();
                output += generateDiagram();
                output += "\t</XMI.content>\n";

        return output;
    }


    public String generatePort(String noun, String xmiId, String xmiPackageId, String ownerXmiId){
        String output = "\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmiId + "\" namespace = \"" + xmiPackageId + "\" >\n" +
                "\t\t<UML:ModelElement.taggedValue>\n" +
                "\t\t\t<UML:TaggedValue tag = \"ea_stype\" value = \"Port\"/>\n" +
                "\t\t\t<UML:TaggedValue tag = \"package\" value = \"" + xmiPackageId + "\" />\n" +
                "\t\t\t<UML:TaggedValue tag = \"owner\" value = \"" + ownerXmiId + "\" />\n" +
                "\t\t\t<UML:TaggedValue tag = \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
                "\t\t</UML:ModelElement.taggedValue\n>" +
                "\t</UML:Class>\n";

        return output;
    }

    public String generateBlock(String noun, String xmiId, String xmiPackageId){
        String output = "\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmiId + "\" namespace = \"" + xmiPackageId + "\" >\n" +
                "\t\t<UML:ModelElement.stereotype>\n" +
                "\t\t\t<UML:Stereotype name = \"block\"/\n>" +
                "\t\t</UML:ModelElement.stereotype>\n" +
                "\t\t<UML:ModelElement.taggedValue\n>" +
                "\t\t\t<UML:TaggedValue tag= \"ea_stype\"' value = \"Class\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"package\" value = \"" + xmiPackageId  + "\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"stereotype\" value = \"block\"/\n>" +
                "\t\t</UML:ModelElement.taggedValue\n>" +
                "\t</UML:Class\n>";

        return output;
    }

    public String generateAssociation(String noun, String xmiId){

        String output = "<UML:Association xmi.id=\"" + xmiId + "\">\n"+
            "\t\t<UML:ModelElement.taggedValue>\n"+
            "\t\t\t<UML:TaggedValue tag=\"ea_type\" value=\"Connector\"/>\n"+
            "\t\t\t<UML:TaggedValue tag=\"direction\" value=\"Source -&gt; Destination\"/>\n"+
            "\t\t\t<UML:TaggedValue tag=\"ea_sourceName\" value=\"" + noun +"\"/>\n"+
            "\t\t\t<UML:TaggedValue tag=\"ea_targetName\" value=\"" + noun +"\"/>\n"+
            "\t\t\t<UML:TaggedValue tag=\"ea_sourceType\" value=\"Port\"/>\n" +
            "\t\t\t<UML:TaggedValue tag=\"ea_targetType\" value=\"Port\"/>\n"+
            "\t\t</UML:ModelElement.taggedValue>\n"+
            "\t\t<UML:Association.connection>\n" +
            "\t\t\t<UML:AssociationEnd type=\"" + xmiId + "\">\n" +
            "\t\t\t</UML:AssociationEnd>\n" +
            "\t\t\t<UML:AssociationEnd type=\""+ xmiId + "\">\n"+
            "\t\t</UML:AssociationEnd>";

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
    public String generateClassifier_Property(String noun, String xmiID, String xmiPackageId, String xmiOwnerId) {
        String xmiIdConvertUnderscoresToDashes = xmiID;
        String output = "\t<UML:ClassifierRole name =\"" + noun + "\" xmi.id =\"" + xmiID + "\" base =\"" + xmiPackageId + "\">\n" +
                "\t\t<UML:ModelElement.taggedValue>\n" +
                "\t\t\t<UML:TaggedValue tag=\"ea_stype\" value=\"Part\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"package\" value=\"" + xmiPackageId + "/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"owner\" value = \"" + xmiOwnerId + "\">\n" +
                "\t\t\t<UML:TaggedValue tag=\"package_name\" value=\"One Level Block Hierarchy\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"propertyType\" value=\"{" + xmiIdConvertUnderscoresToDashes + "}\"/>\n" +
                "\t\t</UML:ModelElement.taggedValue>\n" +
                "\t</UML:ClassifierRole>\n";
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
        output += generateClassifier_Property(noun, xmiId, xmiPackageId, xmiOwnerId);

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
        if (internal==TRUE) {
            output += generateDiagramIBD();
        }
        return output;
    }

    public generateDiagramBDD(String xmiId, String xmiPackage){

        String output = "\t<UML:Diagram name = \"One Level Block Hierarchy\" xmi.id=\"" + xmiId + "\" diagramType = \"ClassDiagram\" owner=\"" + xmiId + "\" toolName = \"Enterprise Architect 2.5\">\n" +
                "\t\t<UML:ModelElement.taggedValue>\n" +
                "\t\t\t<UML:TaggedValue tag=\"package\" value=\"" + xmiPackage + "\">\n"+
                "\t\t\t<UML:TaggedValue tag=\"styleex\" value=\"MDGDgm=SysML1.4::BlockDefinition;SF=1;\"/>\n"+
                "\t\t</UML:ModelElement.taggedValue>\n"+
                "\t\t<UML:Diagram.element>\n";
                for(amountOfFiguresToBeDrawn){
                    if (block) {
                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=500;Top=150;Right=690;Bottom=190;\" subject=\"" + xmiId + "\"/>\n";
                    }if (port) {
                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=300;Top=400;Right=400;Bottom=300;\" subject=\"" + xmiId + "\"/>\n";
                    }
                }
                output += "\t\t</UML:Diagram.element>\n"+
                        "\t</UML:Diagram>\n";

        return output;

    }

    public generateDiagramIBD(String noun, String xmiId, String xmiPackage){
        String output = "\t<UML:Diagram name=\"" + noun +"\" xmi.id=\"" + xmiId+ "\" diagramType=\"CompositeStructureDiagram\" owner=\"" + xmiPackage + "\" toolName=\"Enterprise Architect 2.5\">\n"+
                "\t\tUML:ModelElement.taggedValue>\n"+
                "\t\t\t<UML:TaggedValue tag=\"package\" value=\"" + xmiPackage + "\"/>\n"+
				"\t\t\t<UML:TaggedValue tag=\"type\" value=\"CompositeStructure\"/>\n"+
				"\t\t\t<UML:TaggedValue tag=\"ea_localid\" value=\"2\"/>\n" +
				"\t\t\t<UML:TaggedValue tag=\"parent\" value=\"" + xmiId + "\"/>\n" +
				"\t\t\t<UML:TaggedValue tag=\"styleex\" value=\"MDGDgm=SysML1.4::InternalBlock;SF=1;\"/>"+
                "\t\t</UML:ModelElement.taggedValue>\n"+
                "\t\t<UML:Diagram.element>\n";
                for(amountOfFiguresToBeDrawn) {
                    if (port) {
                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=543;Top=202;Right=558;Bottom=217;\" subject=\"" + xmiId + "\"/>\n";
                    }if (property) {
                        output += "\t\t\t<UML:DiagramElement geometry=\"Left=699;Top=129;Right=714;Bottom=144;\" subject=\"" + xmiId + "\"/>\n";
                    }if (connection){
                        output += "\t\t\t<UML:DiagramElement geometry=\"SX=0;SY=0;EX=0;EY=0;EDGE=2;$LLB=;LLT=;LMT=;LMB=;LRT=;LRB=;IRHS=;ILHS=;Path=;\" subject=\"" + xmiId + "\"/>\n";
                    }
                }
                output += "\t\t</UML:Diagram.element>\n"+
                        "\t</UML:Diagram>\n";
        return output;
    }



}
