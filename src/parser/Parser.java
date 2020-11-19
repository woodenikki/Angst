package parser;
import lexer.*;
import parser.tree.*;

public class Parser {
	
	private Lexer lexer;
	private Token currentToken;
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
		currentToken = lexer.getNextToken();
	}
	
	public void eat(TokenType type) {
		
	}
}
