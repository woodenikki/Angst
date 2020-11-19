package parser;
import lexer.*;
import parser.tree.*;
//https://github.com/rgwohlbold/interpreter-ice

public class Parser {
	
	private Lexer lexer;
	private Token currentToken;
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
		currentToken = lexer.getNextToken();
	}
	
	public void eat(TokenType type) {
		if(type == currentToken.getType()) {
			currentToken = lexer.getNextToken();
		}else {
			System.err.println("Unexpected token: \"" + currentToken.getData() + "\"");
			System.exit(-1);
		}
	}
	

	
	public Node variable() {
		Node node = new Var(currentToken);
		
		this.eat(TokenType.ID);
		return node;
	}
	

}
