package main;

import lib.SysmlParser;
import lib.SysmlLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        try {

            String inputFile = readInputFile("./src/data/FGS_NLP.txt");
            String input = cleanText(inputFile);
//            System.out.println(input);
            ANTLRInputStream inputStream = new ANTLRInputStream(input);
            SysmlLexer sysmlLexer = new SysmlLexer((inputStream));
            CommonTokenStream tokenStream = new CommonTokenStream(sysmlLexer);
            SysmlParser sysmlParser = new SysmlParser(tokenStream);
            Visitor visitor = new Visitor();
            visitor.sentences = new Sentences();
            visitor.visit(sysmlParser.nlparch());
//            Output outputFormatter = new Output(visitor.sentences);
            CreateXmlFileDemo outputFormatter= new CreateXmlFileDemo(visitor.sentences);
            outputFormatter.generateOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String readInputFile(String path) throws FileNotFoundException {
        File NLP_Output = new File(path);
        Scanner sc = new Scanner(NLP_Output);
        String text = "";
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            text += s+"\n";
        }
        return text;
    }
    public static String cleanText(String s){
        String keywordBlackList[] = {};
        for(int i=0; i< keywordBlackList.length; i++){
            s = s.replaceAll(keywordBlackList[i], "");
        }

        return s;
    }
}