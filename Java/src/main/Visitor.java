package main;
import lib.SysmlParser;
import lib.SysmlVisitor;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Visitor <Object> extends AbstractParseTreeVisitor<Object> implements SysmlVisitor<Object> {
    public int index =0;
    Sentence currentSentence;
    public Sentences sentences;
    String currentContext="";
    /**
     * Visit a parse tree produced by {@link SysmlParser#nlparch}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitNlparch(SysmlParser.NlparchContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#sentences}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitSentences(SysmlParser.SentencesContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#sentence}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitSentence(SysmlParser.SentenceContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#structural_stmts}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStructural_stmts(SysmlParser.Structural_stmtsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#structural_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStructural_stmt(SysmlParser.Structural_stmtContext ctx) {

        String sentence = ctx.getText();
        boolean internal = false, port=false;
        if(sentence.contains("internal_components")) {
            internal = true;
        } if (sentence.contains("port_components")) {
            port=true;
        }
        String structN = ctx.Struct_noun().getText();
//        System.err.println(structN);
        sentences.createNewSentence("Structural", structN);
        currentSentence = sentences.getSentenceByStructNoun(structN);
        currentSentence.isInternal = internal;
        currentSentence.isPort = port;
        currentSentence.structNoun = structN;
        visit(ctx.struct_multinoun());
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#connection_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitConnection_stmt(SysmlParser.Connection_stmtContext ctx) {
        String sentence = ctx.getText();
        List structN = ctx.Struct_noun();
        sentences.createNewSentence("Connection", String.valueOf(structN.get(0)));
        currentSentence = sentences.getSentenceByStructNoun(String.valueOf(structN.get(0)));
        currentSentence.structNoun = String.valueOf(structN.get(0));
        currentSentence.structNouns.addAll(structN);
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#instantitation_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitInstantitation_stmt(SysmlParser.Instantitation_stmtContext ctx) {
        String sentence = ctx.getText();
        List structN = ctx.Struct_noun();
        sentences.createNewSentence("Instantitation", String.valueOf(structN.get(0)));
        currentSentence = sentences.getSentenceByStructNoun(String.valueOf(structN.get(0)));
        currentSentence.structNoun = String.valueOf(structN.get(0));
        currentSentence.structNouns.add(String.valueOf(structN.get(1)));
        currentSentence.isInstantitation=true;
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#functional_stmts}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFunctional_stmts(SysmlParser.Functional_stmtsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#functional_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFunctional_stmt(SysmlParser.Functional_stmtContext ctx) {
        String sentence = ctx.getText();
        List structN = ctx.Struct_noun();
        sentences.createNewSentence("Functional", String.valueOf(structN.get(0)));
        currentSentence = sentences.getSentenceByStructNoun(String.valueOf(structN.get(0)));
        currentSentence.structNoun = String.valueOf(structN.get(0));
        currentSentence.structNouns.addAll(structN);
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#struct_multinoun}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStruct_multinoun(SysmlParser.Struct_multinounContext ctx) {
        if (ctx.struct_multinoun() != null) {
            visitStruct_multinoun(ctx.struct_multinoun());
        }
        if(ctx.Struct_noun().size() > 1) {
            for (TerminalNode a : ctx.Struct_noun()){
                currentSentence.addStructNoun(a.getText());
            }

        }
        else {
            currentSentence.addStructNoun(ctx.Struct_noun().stream().map(TerminalNode::toString).collect(Collectors.joining(",")));
        }
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#multi_flow}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMulti_flow(SysmlParser.Multi_flowContext ctx) {
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#flow}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFlow(SysmlParser.FlowContext ctx) {
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#states}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStates(SysmlParser.StatesContext ctx) {
        return null;
    }
}
