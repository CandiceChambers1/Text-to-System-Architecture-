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
                "\t</XMI.header>";
        return output;
    }
    public String generateFooter(){
        String output = "\t<XMI.extensions xmi.extender=\"Enterprise Architect 2.5\"/>\n" +
                "</XMI>";
        return output;
    }
}
