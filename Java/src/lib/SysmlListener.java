// Generated from .\Sysml.g4 by ANTLR 4.9.2

    package lib;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SysmlParser}.
 */
public interface SysmlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SysmlParser#nlparch}.
	 * @param ctx the parse tree
	 */
	void enterNlparch(SysmlParser.NlparchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#nlparch}.
	 * @param ctx the parse tree
	 */
	void exitNlparch(SysmlParser.NlparchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#sentences}.
	 * @param ctx the parse tree
	 */
	void enterSentences(SysmlParser.SentencesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#sentences}.
	 * @param ctx the parse tree
	 */
	void exitSentences(SysmlParser.SentencesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(SysmlParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(SysmlParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#test_stmts}.
	 * @param ctx the parse tree
	 */
	void enterTest_stmts(SysmlParser.Test_stmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#test_stmts}.
	 * @param ctx the parse tree
	 */
	void exitTest_stmts(SysmlParser.Test_stmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#structural_stmts}.
	 * @param ctx the parse tree
	 */
	void enterStructural_stmts(SysmlParser.Structural_stmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#structural_stmts}.
	 * @param ctx the parse tree
	 */
	void exitStructural_stmts(SysmlParser.Structural_stmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#structural_stmt}.
	 * @param ctx the parse tree
	 */
	void enterStructural_stmt(SysmlParser.Structural_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#structural_stmt}.
	 * @param ctx the parse tree
	 */
	void exitStructural_stmt(SysmlParser.Structural_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#connection_stmt}.
	 * @param ctx the parse tree
	 */
	void enterConnection_stmt(SysmlParser.Connection_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#connection_stmt}.
	 * @param ctx the parse tree
	 */
	void exitConnection_stmt(SysmlParser.Connection_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#functional_stmts}.
	 * @param ctx the parse tree
	 */
	void enterFunctional_stmts(SysmlParser.Functional_stmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#functional_stmts}.
	 * @param ctx the parse tree
	 */
	void exitFunctional_stmts(SysmlParser.Functional_stmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#functional_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFunctional_stmt(SysmlParser.Functional_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#functional_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFunctional_stmt(SysmlParser.Functional_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#struct_multinoun}.
	 * @param ctx the parse tree
	 */
	void enterStruct_multinoun(SysmlParser.Struct_multinounContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#struct_multinoun}.
	 * @param ctx the parse tree
	 */
	void exitStruct_multinoun(SysmlParser.Struct_multinounContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#multi_flow}.
	 * @param ctx the parse tree
	 */
	void enterMulti_flow(SysmlParser.Multi_flowContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#multi_flow}.
	 * @param ctx the parse tree
	 */
	void exitMulti_flow(SysmlParser.Multi_flowContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#flow}.
	 * @param ctx the parse tree
	 */
	void enterFlow(SysmlParser.FlowContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#flow}.
	 * @param ctx the parse tree
	 */
	void exitFlow(SysmlParser.FlowContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysmlParser#states}.
	 * @param ctx the parse tree
	 */
	void enterStates(SysmlParser.StatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysmlParser#states}.
	 * @param ctx the parse tree
	 */
	void exitStates(SysmlParser.StatesContext ctx);
}