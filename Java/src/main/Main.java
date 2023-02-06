package main;

import lib.SysmlLexer;
import lib.SysmlParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileNotFoundException;
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
            visitor.visit(sysmlParser.nlparch());

//            for(int i =0; i<)
//            for(int i=0;i<=visitor.-1;i++){
//                System.out.println(visitor.system_declaration[i][0]);
//                System.out.println(visitor.system_declaration[i][1]);
//            }
//
//            for(int i=0;i<=visitor1.ind_system_in_features-1;i++)
//            {
//                System.out.println(visitor1.system_in_features[i]);
//            }

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
        return s;
    }
}