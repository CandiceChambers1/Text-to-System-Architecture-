// Generated from .\Sysml.g4 by ANTLR 4.9.2

    package lib;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SysmlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		With=1, Comma=2, And=3, Form=4, End=5, Colon=6, It=7, To=8, Them=9, From=10, 
		Struct_verb=11, Connect_verb=12, Instantiates=13, Internal=14, Port=15, 
		Func_verb=16, Energy=17, Adj_value=18, Struct_noun=19, State=20, Signal=21, 
		WS=22;
	public static final int
		RULE_nlparch = 0, RULE_sentences = 1, RULE_sentence = 2, RULE_test_stmts = 3, 
		RULE_structural_stmts = 4, RULE_structural_stmt = 5, RULE_connection_stmt = 6, 
		RULE_instantitation_stmt = 7, RULE_functional_stmts = 8, RULE_functional_stmt = 9, 
		RULE_struct_multinoun = 10, RULE_multi_flow = 11, RULE_flow = 12, RULE_states = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"nlparch", "sentences", "sentence", "test_stmts", "structural_stmts", 
			"structural_stmt", "connection_stmt", "instantitation_stmt", "functional_stmts", 
			"functional_stmt", "struct_multinoun", "multi_flow", "flow", "states"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'with'", "','", "'and'", "'form'", "'.'", "':'", "'it'", "'to'", 
			"'them'", "'from'", "'consists'", "'connected'", "'instantiates'", "'internal_components'", 
			"'port_components'", null, "'energy'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "With", "Comma", "And", "Form", "End", "Colon", "It", "To", "Them", 
			"From", "Struct_verb", "Connect_verb", "Instantiates", "Internal", "Port", 
			"Func_verb", "Energy", "Adj_value", "Struct_noun", "State", "Signal", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Sysml.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SysmlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class NlparchContext extends ParserRuleContext {
		public List<SentencesContext> sentences() {
			return getRuleContexts(SentencesContext.class);
		}
		public SentencesContext sentences(int i) {
			return getRuleContext(SentencesContext.class,i);
		}
		public NlparchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nlparch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterNlparch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitNlparch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitNlparch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NlparchContext nlparch() throws RecognitionException {
		NlparchContext _localctx = new NlparchContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_nlparch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				sentences();
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==End || _la==Struct_noun );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SentencesContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public SentencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentences; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterSentences(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitSentences(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitSentences(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentencesContext sentences() throws RecognitionException {
		SentencesContext _localctx = new SentencesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentences);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(34); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(33);
					sentence();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(36); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SentenceContext extends ParserRuleContext {
		public Structural_stmtsContext structural_stmts() {
			return getRuleContext(Structural_stmtsContext.class,0);
		}
		public Functional_stmtsContext functional_stmts() {
			return getRuleContext(Functional_stmtsContext.class,0);
		}
		public Test_stmtsContext test_stmts() {
			return getRuleContext(Test_stmtsContext.class,0);
		}
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				{
				setState(38);
				structural_stmts();
				}
				}
				break;
			case 2:
				{
				{
				setState(39);
				functional_stmts();
				}
				}
				break;
			case 3:
				{
				{
				setState(40);
				test_stmts();
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Test_stmtsContext extends ParserRuleContext {
		public TerminalNode End() { return getToken(SysmlParser.End, 0); }
		public Test_stmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterTest_stmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitTest_stmts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitTest_stmts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Test_stmtsContext test_stmts() throws RecognitionException {
		Test_stmtsContext _localctx = new Test_stmtsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_test_stmts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(End);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Structural_stmtsContext extends ParserRuleContext {
		public TerminalNode End() { return getToken(SysmlParser.End, 0); }
		public Structural_stmtContext structural_stmt() {
			return getRuleContext(Structural_stmtContext.class,0);
		}
		public Connection_stmtContext connection_stmt() {
			return getRuleContext(Connection_stmtContext.class,0);
		}
		public Instantitation_stmtContext instantitation_stmt() {
			return getRuleContext(Instantitation_stmtContext.class,0);
		}
		public Structural_stmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structural_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterStructural_stmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitStructural_stmts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitStructural_stmts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Structural_stmtsContext structural_stmts() throws RecognitionException {
		Structural_stmtsContext _localctx = new Structural_stmtsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_structural_stmts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				{
				setState(45);
				structural_stmt();
				}
				}
				break;
			case 2:
				{
				{
				setState(46);
				connection_stmt();
				}
				}
				break;
			case 3:
				{
				{
				setState(47);
				instantitation_stmt();
				}
				}
				break;
			}
			setState(50);
			match(End);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Structural_stmtContext extends ParserRuleContext {
		public TerminalNode Struct_noun() { return getToken(SysmlParser.Struct_noun, 0); }
		public TerminalNode Struct_verb() { return getToken(SysmlParser.Struct_verb, 0); }
		public Struct_multinounContext struct_multinoun() {
			return getRuleContext(Struct_multinounContext.class,0);
		}
		public List<TerminalNode> Internal() { return getTokens(SysmlParser.Internal); }
		public TerminalNode Internal(int i) {
			return getToken(SysmlParser.Internal, i);
		}
		public List<TerminalNode> Port() { return getTokens(SysmlParser.Port); }
		public TerminalNode Port(int i) {
			return getToken(SysmlParser.Port, i);
		}
		public List<TerminalNode> Colon() { return getTokens(SysmlParser.Colon); }
		public TerminalNode Colon(int i) {
			return getToken(SysmlParser.Colon, i);
		}
		public Structural_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structural_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterStructural_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitStructural_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitStructural_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Structural_stmtContext structural_stmt() throws RecognitionException {
		Structural_stmtContext _localctx = new Structural_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_structural_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(Struct_noun);
			setState(53);
			match(Struct_verb);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Internal || _la==Port) {
				{
				{
				setState(54);
				_la = _input.LA(1);
				if ( !(_la==Internal || _la==Port) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Colon) {
					{
					{
					setState(55);
					match(Colon);
					}
					}
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			struct_multinoun();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Connection_stmtContext extends ParserRuleContext {
		public List<TerminalNode> Struct_noun() { return getTokens(SysmlParser.Struct_noun); }
		public TerminalNode Struct_noun(int i) {
			return getToken(SysmlParser.Struct_noun, i);
		}
		public TerminalNode Connect_verb() { return getToken(SysmlParser.Connect_verb, 0); }
		public TerminalNode To() { return getToken(SysmlParser.To, 0); }
		public TerminalNode Port() { return getToken(SysmlParser.Port, 0); }
		public TerminalNode And() { return getToken(SysmlParser.And, 0); }
		public Connection_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connection_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterConnection_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitConnection_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitConnection_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Connection_stmtContext connection_stmt() throws RecognitionException {
		Connection_stmtContext _localctx = new Connection_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_connection_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(Struct_noun);
			setState(69);
			match(Connect_verb);
			setState(70);
			match(To);
			setState(71);
			match(Struct_noun);
			{
			setState(72);
			match(Port);
			setState(73);
			match(Struct_noun);
			setState(74);
			match(And);
			setState(75);
			match(Struct_noun);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Instantitation_stmtContext extends ParserRuleContext {
		public List<TerminalNode> Struct_noun() { return getTokens(SysmlParser.Struct_noun); }
		public TerminalNode Struct_noun(int i) {
			return getToken(SysmlParser.Struct_noun, i);
		}
		public TerminalNode Instantiates() { return getToken(SysmlParser.Instantiates, 0); }
		public Instantitation_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instantitation_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterInstantitation_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitInstantitation_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitInstantitation_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instantitation_stmtContext instantitation_stmt() throws RecognitionException {
		Instantitation_stmtContext _localctx = new Instantitation_stmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_instantitation_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(Struct_noun);
			setState(78);
			match(Instantiates);
			setState(79);
			match(Struct_noun);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Functional_stmtsContext extends ParserRuleContext {
		public TerminalNode End() { return getToken(SysmlParser.End, 0); }
		public Functional_stmtContext functional_stmt() {
			return getRuleContext(Functional_stmtContext.class,0);
		}
		public Functional_stmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functional_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterFunctional_stmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitFunctional_stmts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitFunctional_stmts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Functional_stmtsContext functional_stmts() throws RecognitionException {
		Functional_stmtsContext _localctx = new Functional_stmtsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functional_stmts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			{
			setState(81);
			functional_stmt();
			}
			setState(82);
			match(End);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Functional_stmtContext extends ParserRuleContext {
		public List<TerminalNode> Struct_noun() { return getTokens(SysmlParser.Struct_noun); }
		public TerminalNode Struct_noun(int i) {
			return getToken(SysmlParser.Struct_noun, i);
		}
		public TerminalNode Func_verb() { return getToken(SysmlParser.Func_verb, 0); }
		public List<Multi_flowContext> multi_flow() {
			return getRuleContexts(Multi_flowContext.class);
		}
		public Multi_flowContext multi_flow(int i) {
			return getRuleContext(Multi_flowContext.class,i);
		}
		public TerminalNode From() { return getToken(SysmlParser.From, 0); }
		public TerminalNode To() { return getToken(SysmlParser.To, 0); }
		public Struct_multinounContext struct_multinoun() {
			return getRuleContext(Struct_multinounContext.class,0);
		}
		public List<FlowContext> flow() {
			return getRuleContexts(FlowContext.class);
		}
		public FlowContext flow(int i) {
			return getRuleContext(FlowContext.class,i);
		}
		public TerminalNode With() { return getToken(SysmlParser.With, 0); }
		public TerminalNode Form() { return getToken(SysmlParser.Form, 0); }
		public Functional_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functional_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterFunctional_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitFunctional_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitFunctional_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Functional_stmtContext functional_stmt() throws RecognitionException {
		Functional_stmtContext _localctx = new Functional_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_functional_stmt);
		int _la;
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				{
				setState(84);
				match(Struct_noun);
				setState(85);
				match(Func_verb);
				setState(86);
				multi_flow();
				}
				setState(96);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					{
					setState(88);
					match(From);
					setState(89);
					match(Struct_noun);
					setState(90);
					match(To);
					setState(91);
					match(Struct_noun);
					}
					}
					break;
				case 2:
					{
					{
					setState(92);
					_la = _input.LA(1);
					if ( !(_la==To || _la==From) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(93);
					struct_multinoun();
					}
					}
					break;
				case 3:
					{
					{
					setState(94);
					match(To);
					setState(95);
					multi_flow();
					}
					}
					break;
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(98);
				match(Struct_noun);
				setState(99);
				match(Func_verb);
				}
				setState(113);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					{
					setState(101);
					flow();
					setState(102);
					match(With);
					setState(103);
					flow();
					setState(104);
					match(To);
					setState(105);
					match(Form);
					setState(106);
					flow();
					}
					}
					break;
				case 2:
					{
					{
					setState(108);
					flow();
					setState(109);
					match(To);
					setState(110);
					match(Form);
					setState(111);
					multi_flow();
					}
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Struct_multinounContext extends ParserRuleContext {
		public List<TerminalNode> Struct_noun() { return getTokens(SysmlParser.Struct_noun); }
		public TerminalNode Struct_noun(int i) {
			return getToken(SysmlParser.Struct_noun, i);
		}
		public TerminalNode Comma() { return getToken(SysmlParser.Comma, 0); }
		public Struct_multinounContext struct_multinoun() {
			return getRuleContext(Struct_multinounContext.class,0);
		}
		public TerminalNode And() { return getToken(SysmlParser.And, 0); }
		public Struct_multinounContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_multinoun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterStruct_multinoun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitStruct_multinoun(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitStruct_multinoun(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Struct_multinounContext struct_multinoun() throws RecognitionException {
		Struct_multinounContext _localctx = new Struct_multinounContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_struct_multinoun);
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(117);
				match(Struct_noun);
				setState(118);
				match(Comma);
				setState(119);
				struct_multinoun();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(120);
				match(Struct_noun);
				setState(121);
				match(And);
				setState(122);
				match(Struct_noun);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(123);
				match(Struct_noun);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multi_flowContext extends ParserRuleContext {
		public List<FlowContext> flow() {
			return getRuleContexts(FlowContext.class);
		}
		public FlowContext flow(int i) {
			return getRuleContext(FlowContext.class,i);
		}
		public TerminalNode And() { return getToken(SysmlParser.And, 0); }
		public TerminalNode Comma() { return getToken(SysmlParser.Comma, 0); }
		public Multi_flowContext multi_flow() {
			return getRuleContext(Multi_flowContext.class,0);
		}
		public Multi_flowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi_flow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterMulti_flow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitMulti_flow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitMulti_flow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multi_flowContext multi_flow() throws RecognitionException {
		Multi_flowContext _localctx = new Multi_flowContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_multi_flow);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				flow();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(127);
				flow();
				setState(128);
				match(And);
				setState(129);
				flow();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(131);
				flow();
				setState(132);
				match(Comma);
				setState(133);
				multi_flow();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlowContext extends ParserRuleContext {
		public TerminalNode Adj_value() { return getToken(SysmlParser.Adj_value, 0); }
		public TerminalNode Energy() { return getToken(SysmlParser.Energy, 0); }
		public StatesContext states() {
			return getRuleContext(StatesContext.class,0);
		}
		public TerminalNode Signal() { return getToken(SysmlParser.Signal, 0); }
		public FlowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterFlow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitFlow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitFlow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlowContext flow() throws RecognitionException {
		FlowContext _localctx = new FlowContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_flow);
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(137);
				match(Adj_value);
				setState(138);
				match(Energy);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				states();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				match(Signal);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatesContext extends ParserRuleContext {
		public TerminalNode State() { return getToken(SysmlParser.State, 0); }
		public List<TerminalNode> Adj_value() { return getTokens(SysmlParser.Adj_value); }
		public TerminalNode Adj_value(int i) {
			return getToken(SysmlParser.Adj_value, i);
		}
		public StatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_states; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).enterStates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SysmlListener ) ((SysmlListener)listener).exitStates(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SysmlVisitor ) return ((SysmlVisitor<? extends T>)visitor).visitStates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatesContext states() throws RecognitionException {
		StatesContext _localctx = new StatesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_states);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Adj_value) {
				{
				{
				setState(143);
				match(Adj_value);
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(149);
			match(State);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30\u009a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\6\2 \n\2\r\2\16\2!\3\3\6"+
		"\3%\n\3\r\3\16\3&\3\4\3\4\3\4\5\4,\n\4\3\5\3\5\3\6\3\6\3\6\5\6\63\n\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\7\7;\n\7\f\7\16\7>\13\7\7\7@\n\7\f\7\16\7C\13"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"c\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13t\n\13\5\13v\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\177\n"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u008a\n\r\3\16\3\16\3\16\3"+
		"\16\5\16\u0090\n\16\3\17\7\17\u0093\n\17\f\17\16\17\u0096\13\17\3\17\3"+
		"\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\4\3\2\20\21\4\2"+
		"\n\n\f\f\2\u009f\2\37\3\2\2\2\4$\3\2\2\2\6+\3\2\2\2\b-\3\2\2\2\n\62\3"+
		"\2\2\2\f\66\3\2\2\2\16F\3\2\2\2\20O\3\2\2\2\22S\3\2\2\2\24u\3\2\2\2\26"+
		"~\3\2\2\2\30\u0089\3\2\2\2\32\u008f\3\2\2\2\34\u0094\3\2\2\2\36 \5\4\3"+
		"\2\37\36\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\3\3\2\2\2#%\5\6\4"+
		"\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\5\3\2\2\2(,\5\n\6\2),\5"+
		"\22\n\2*,\5\b\5\2+(\3\2\2\2+)\3\2\2\2+*\3\2\2\2,\7\3\2\2\2-.\7\7\2\2."+
		"\t\3\2\2\2/\63\5\f\7\2\60\63\5\16\b\2\61\63\5\20\t\2\62/\3\2\2\2\62\60"+
		"\3\2\2\2\62\61\3\2\2\2\63\64\3\2\2\2\64\65\7\7\2\2\65\13\3\2\2\2\66\67"+
		"\7\25\2\2\67A\7\r\2\28<\t\2\2\29;\7\b\2\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2"+
		"\2<=\3\2\2\2=@\3\2\2\2><\3\2\2\2?8\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2"+
		"\2BD\3\2\2\2CA\3\2\2\2DE\5\26\f\2E\r\3\2\2\2FG\7\25\2\2GH\7\16\2\2HI\7"+
		"\n\2\2IJ\7\25\2\2JK\7\21\2\2KL\7\25\2\2LM\7\5\2\2MN\7\25\2\2N\17\3\2\2"+
		"\2OP\7\25\2\2PQ\7\17\2\2QR\7\25\2\2R\21\3\2\2\2ST\5\24\13\2TU\7\7\2\2"+
		"U\23\3\2\2\2VW\7\25\2\2WX\7\22\2\2XY\5\30\r\2Yb\3\2\2\2Z[\7\f\2\2[\\\7"+
		"\25\2\2\\]\7\n\2\2]c\7\25\2\2^_\t\3\2\2_c\5\26\f\2`a\7\n\2\2ac\5\30\r"+
		"\2bZ\3\2\2\2b^\3\2\2\2b`\3\2\2\2bc\3\2\2\2cv\3\2\2\2de\7\25\2\2ef\7\22"+
		"\2\2fs\3\2\2\2gh\5\32\16\2hi\7\3\2\2ij\5\32\16\2jk\7\n\2\2kl\7\6\2\2l"+
		"m\5\32\16\2mt\3\2\2\2no\5\32\16\2op\7\n\2\2pq\7\6\2\2qr\5\30\r\2rt\3\2"+
		"\2\2sg\3\2\2\2sn\3\2\2\2tv\3\2\2\2uV\3\2\2\2ud\3\2\2\2v\25\3\2\2\2wx\7"+
		"\25\2\2xy\7\4\2\2y\177\5\26\f\2z{\7\25\2\2{|\7\5\2\2|\177\7\25\2\2}\177"+
		"\7\25\2\2~w\3\2\2\2~z\3\2\2\2~}\3\2\2\2\177\27\3\2\2\2\u0080\u008a\5\32"+
		"\16\2\u0081\u0082\5\32\16\2\u0082\u0083\7\5\2\2\u0083\u0084\5\32\16\2"+
		"\u0084\u008a\3\2\2\2\u0085\u0086\5\32\16\2\u0086\u0087\7\4\2\2\u0087\u0088"+
		"\5\30\r\2\u0088\u008a\3\2\2\2\u0089\u0080\3\2\2\2\u0089\u0081\3\2\2\2"+
		"\u0089\u0085\3\2\2\2\u008a\31\3\2\2\2\u008b\u008c\7\24\2\2\u008c\u0090"+
		"\7\23\2\2\u008d\u0090\5\34\17\2\u008e\u0090\7\27\2\2\u008f\u008b\3\2\2"+
		"\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090\33\3\2\2\2\u0091\u0093"+
		"\7\24\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2"+
		"\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098"+
		"\7\26\2\2\u0098\35\3\2\2\2\17!&+\62<Absu~\u0089\u008f\u0094";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}