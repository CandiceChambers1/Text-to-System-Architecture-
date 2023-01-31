// Generated from .\Sysml.g4 by ANTLR 4.9.2

    package lib;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SysmlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SysmlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SysmlParser#nlparch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNlparch(SysmlParser.NlparchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#sentences}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentences(SysmlParser.SentencesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(SysmlParser.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#test_stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_stmts(SysmlParser.Test_stmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#structural_stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructural_stmts(SysmlParser.Structural_stmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#structural_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructural_stmt(SysmlParser.Structural_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#connection_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnection_stmt(SysmlParser.Connection_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#functional_stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctional_stmts(SysmlParser.Functional_stmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#functional_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctional_stmt(SysmlParser.Functional_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#struct_multinoun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct_multinoun(SysmlParser.Struct_multinounContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#multi_flow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulti_flow(SysmlParser.Multi_flowContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#flow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow(SysmlParser.FlowContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysmlParser#states}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStates(SysmlParser.StatesContext ctx);
}