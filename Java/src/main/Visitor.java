package main;
import lib.SysmlParser;
import lib.SysmlVisitor;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

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
     * Visit a parse tree produced by {@link SysmlParser#test_stmts}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitTest_stmts(SysmlParser.Test_stmtsContext ctx) {
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

        // Input: name, xmi.id

        /* <UML:Class name="Side" xmi.id="EAID_32000000_0000_0000_0000_000000000000" namespace="EAPK_FEA10000_0000_0000_0000_000000000000">
            <UML:ModelElement.stereotype>
                <UML:Stereotype name="block"/>
            </UML:ModelElement.stereotype>
            <UML:ModelElement.taggedValue>
                <UML:TaggedValue tag="ea_stype" value="Class"/>
                <UML:TaggedValue tag="package" value="EAPK_FEA10000_0000_0000_0000_000000000000"/>
                <UML:TaggedValue tag="package_name" value="One Level Block Hierarchy"/>
                <UML:TaggedValue tag="stereotype" value="block"/>
            </UML:ModelElement.taggedValue>
        </UML:Class> */
        String sentence = ctx.getText();
        boolean internal = false, port=false;
        if(sentence.contains("internal_components")) {
            internal = true;
        } if (sentence.contains("port_components")) {
            port=true;
        }
        String structN = ctx.Struct_noun().getText();
        sentences.createNewSentence("Structural", structN );
        currentSentence = sentences.getSentenceByStructNoun(structN);
        currentSentence.isInternal = internal;
        currentSentence.isPort = port;

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
        return null;
    }

    /**
     * Visit a parse tree produced by {@link SysmlParser#functional_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFunctional_stmt(SysmlParser.Functional_stmtContext ctx) {
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
