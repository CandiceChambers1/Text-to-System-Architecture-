package main;

public class Output {
    Sentences sentences;
    public Output(Sentences sentences){this.sentences = sentences;}

    public String generateOutput(){
        String output = "<XMI xmi.version=\"1.1\" xmlns:UML=\"omg.org/UML1.3\">\n";
        output += generateHeader();
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
        String output = "\n\t<XMI.extensions xmi.extender=\"Enterprise Architect 2.5\"/>\n" +
                "</XMI>";
        return output;
    }

    public String generateContent(){
        String output = "\t<XMI.content>\n";
               output += generateBlock();
               output += generatePort();
               output += generateIBD();
               output += generateDiagram();
               output += "\t</XMI.content>\n";
    }

    public String generatePort(){
        String output = "\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmi_ID + "\" namespace = \"" + xmi_package_ID + "\" >\n" +
                "\t\t<UML:ModelElement.taggedValue>\n" +
                "\t\t\t<UML:TaggedValue tag = \"ea_stype\" value = \"Port\"/>\n" +
                "\t\t\t<UML:TaggedValue tag = \"package\" value = \"" + xmi_package_ID + "\" />\n" +
                "\t\t\t<UML:TaggedValue tag = \"owner\" value = \"" + owner_xmi_ID + "\" />\n" +
                "\t\t\t<UML:TaggedValue tag = \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
                "\t\t</UML:ModelElement.taggedValue\n>" +
                "\t</UML:Class>\n";

        return output;
    }

    public String generateBlock(){
        String output = "\t<UML:Class name = \"" + noun + "\" xmi.id = \"" + xmi_ID + "\" namespace = \"" + xmi_package_ID + "\" >\n" +
                "\t\t<UML:ModelElement.stereotype>\n" +
                "\t\t\t<UML:Stereotype name = \"block\"/\n>" +
                "\t\t</UML:ModelElement.stereotype>\n" +
                "\t\t<UML:ModelElement.taggedValue\n>" +
                "\t\t\t<UML:TaggedValue tag= \"ea_stype\"' value = \"Class\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"package\" value = \"" + xmi_package_id  + "\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"package_name\" value = \"One Level Block Hierarchy\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"stereotype\" value = \"block\"/\n>" +
                "\t\t</UML:ModelElement.taggedValue\n>" +
                "\t</UML:Class\n>";

        return output;
    }

    public String generateIBD_Block(){
        String append_output = "\t\t\t<UML:TaggedValue tag=\"ea_ntype\" value=\"8\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"ea_ntype\" value=\"8\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"diagram\" value=\"" xmi_id_IBD + "\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"$ea_xref_property\" value=\"\"$XREFPROP=$XID={" +
                number {001EC013-2161-4f09-8362-A1A5114E8E8A}$XID}"};$NAM=DefaultDiagram$NAM;$TYP=element property$TYP;$SUP={" +
                number: {9E656A80-A25F-4ae3-A153-749CEFFFA468}"}$SUP;$ENDXREF;\"/>\n";

        return append_output;

    public String generateClassifier_Property() {
        String output = "\t<UML:ClassifierRole name =\"" + noun + "\" xmi.id =\"" + xmi_id + "\" base =\"" + xmi_package_id + "\">\n" +
                "\t\t<UML:ModelElement.taggedValue>\n" +
                "\t\t\t<UML:TaggedValue tag=\"ea_stype\" value=\"Part\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"package\" value=\"" + xmi_package_id + "/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"owner\" value = \"" + xmi_owner_id + "\">\n" +
                "\t\t\t<UML:TaggedValue tag=\"package_name\" value=\"One Level Block Hierarchy\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"propertyType\" value=\"{" + xmi_id_convert_underscores_to_dashes + "}\"/>\n" +
                "\t\t</UML:ModelElement.taggedValue>\n" +
                "\t</UML:ClassifierRole>\n";
        return output;
    }
    public String generateClassifier_Flow() {
        String output = "\t<UML:ClassifierRole name =\"" + noun + "\" xmi.id =\"" + xmi_id + "\" base =\"" + xmi_package_id + "\">\n" +
                "\t\t<UML:ModelElement.stereotype>\n"+
                "\t\t\t<UML:Stereotype name=\"FlowProperty\"/>" +
                "\t\t</UML:ModelElement.stereotype>"+
                "\t\t<UML:ModelElement.taggedValue>\n" +
                "\t\t\t<UML:TaggedValue tag=\"ea_stype\" value=\"Part\"/>\n" +
                "\t\t\t<UML:TaggedValue tag= \"package\" value=\"" + xmi_package_id + "/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"owner\" value = \"" + xmi_owner_id + "\">\n" +
                "\t\t\t<UML:TaggedValue tag=\"package_name\" value=\"One Level Block Hierarchy\"/>\n" +
                "\t\t\t<UML:TaggedValue tag=\"stereotype\" value=\"FlowProperty\">"+
                "\t\t\t<UML:TaggedValue tag=\"propertyType\" value=\"{" + xmi_id_for_Boolean + "}\"/>\n" +
                "\t\t</UML:ModelElement.taggedValue>\n" +
                "\t</UML:ClassifierRole>\n";

        return output;
    }

    public String generateIBD() {
        String output = "\t<UML:Collaboration xmi.id =\"" + xmi_id_Collaboration + "\" name =\"Collaborations\">\n";
        output += "\t\t<UML:Namespace.ownedElement>\n";
        output += generateClassifier_Property();

//        if direction == TRUE
                output += generateClassifier_Flow();

        output += "\t\t</UML:Namespace.ownedElement>\n";
        output += "\t\t<UML:Collaboration.interaction/>\n";
        output += "\t</UML:Collaboration>\n";
        return output;
    }


    public String generateDiagram(){
        generateDiagramBDD();
        generateDiagramIBD();

    }

    public generateDiagramBDD(){
//        <UML:Diagram name="One Level Block Hierarchy" xmi.id="EAID_B05FCB2A_1BAA_4f7c_83BB_4535B9AD1E0B" diagramType="ClassDiagram" owner="EAPK_FEA10000_0000_0000_0000_000000000000" toolName="Enterprise Architect 2.5">
//			<UML:ModelElement.taggedValue>
//				<UML:TaggedValue tag="package" value="EAPK_FEA10000_0000_0000_0000_000000000000"/>
//				<UML:TaggedValue tag="styleex" value="MDGDgm=SysML1.4::BlockDefinition;SF=1;"/>
//			</UML:ModelElement.taggedValue>
//			<UML:Diagram.element>
//				<UML:DiagramElement geometry="Left=565;Top=155;Right=690;Bottom=195;" subject="EAID_17000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=703;Top=139;Right=718;Bottom=154;" subject="EAID_28000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=703;Top=180;Right=718;Bottom=195;" subject="EAID_16000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=536;Top=180;Right=551;Bottom=195;" subject="EAID_26000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=536;Top=214;Right=551;Bottom=229;" subject="EAID_15000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=536;Top=139;Right=551;Bottom=154;" subject="EAID_10000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=543;Top=113;Right=710;Bottom=254;" subject="EAID_32000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=355;Top=398;Right=370;Bottom=413;" subject="EAID_29000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=355;Top=365;Right=370;Bottom=380;" subject="EAID_36000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=355;Top=326;Right=370;Bottom=341;" subject="EAID_14000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=362;Top=284;Right=519;Bottom=430;" subject="EAID_27000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=183;Top=113;Right=348;Bottom=254;" subject="EAID_23000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=10;Top=10;Right=988;Bottom=465;" subject="EAID_FEA3AB37_2826_45c9_B911_4F76D374163E"/>
//			</UML:Diagram.element>
//			</UML:Diagram>
    }

    public generateDiagramIBD(){
//        <UML:Diagram name="FGS_System" xmi.id="EAID_9E656A80_A25F_4ae3_A153_749CEFFFA468" diagramType="CompositeStructureDiagram" owner="EAPK_FEA10000_0000_0000_0000_000000000000" toolName="Enterprise Architect 2.5">
//			<UML:ModelElement.taggedValue>
//				<UML:TaggedValue tag="package" value="EAPK_FEA10000_0000_0000_0000_000000000000"/>
//				<UML:TaggedValue tag="type" value="CompositeStructure"/>
//				<UML:TaggedValue tag="ea_localid" value="2"/>
//				<UML:TaggedValue tag="parent" value="EAID_23000000_0000_0000_0000_000000000000"/>
//				<UML:TaggedValue tag="styleex" value="MDGDgm=SysML1.4::InternalBlock;SF=1;"/>
//			</UML:ModelElement.taggedValue>
//			<UML:Diagram.element>
//				<UML:DiagramElement geometry="Left=543;Top=202;Right=558;Bottom=217;" subject="EAID_80000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=543;Top=128;Right=558;Bottom=143;" subject="EAID_35000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=699;Top=181;Right=714;Bottom=196;" subject="EAID_30000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=699;Top=238;Right=714;Bottom=253;" subject="EAID_11000000_0000_0000_0000_000000000000" />
//				<UML:DiagramElement geometry="Left=699;Top=129;Right=714;Bottom=144;" subject="EAID_37000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=550;Top=103;Right=706;Bottom=273;" subject="EAID_18000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=251;Top=128;Right=266;Bottom=143;" subject="EAID_50000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=251;Top=205;Right=266;Bottom=220;" subject="EAID_70000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=100;Top=190;Right=115;Bottom=205;" subject="EAID_34000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=100;Top=238;Right=115;Bottom=253;" subject="EAID_33000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=100;Top=132;Right=115;Bottom=147;" subject="EAID_38000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=107;Top=103;Right=258;Bottom=273;" subject="EAID_22000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=370;Top=120;Right=440;Bottom=152;" subject="EAID_21000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=433;Top=128;Right=448;Bottom=143;" subject="EAID_24000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=394;Top=113;Right=409;Bottom=128;" subject="EAID_31000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=363;Top=128;Right=378;Bottom=143;" subject="EAID_13000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=368;Top=194;Right=443;Bottom=228;" subject="EAID_19000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=436;Top=202;Right=451;Bottom=217;" subject="EAID_12000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=394;Top=187;Right=409;Bottom=202;" subject="EAID_40000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=361;Top=205;Right=376;Bottom=220;" subject="EAID_20000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="Left=10;Top=20;Right=830;Bottom=310;" subject="EAID_23000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="SX=0;SY=0;EX=0;EY=0;EDGE=4;$LLB=;LLT=;LMT=;LMB=;LRT=;LRB=;IRHS=;ILHS=;Path=;" subject="EAID_30000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="SX=0;SY=0;EX=0;EY=0;EDGE=4;$LLB=;LLT=;LMT=;LMB=;LRT=;LRB=;IRHS=;ILHS=;Path=;" subject="EAID_90000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="SX=0;SY=0;EX=0;EY=0;EDGE=2;$LLB=;LLT=;LMT=;LMB=;LRT=;LRB=;IRHS=;ILHS=;Path=;" subject="EAID_25000000_0000_0000_0000_000000000000"/>
//				<UML:DiagramElement geometry="SX=0;SY=0;EX=0;EY=0;EDGE=2;$LLB=;LLT=;LMT=;LMB=;LRT=;LRB=;IRHS=;ILHS=;Path=;" subject="EAID_60000000_0000_0000_0000_000000000000"/>
//			</UML:Diagram.element>
//		</UML:Diagram>
    }



}
