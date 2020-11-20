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
	
	public Node factor() {
		Token token = currentToken;
		
		if(currentToken.getType() == TokenType.NUMBER) {		// number c:
			this.eat(TokenType.NUMBER);
			return new parser.tree.Num(token);
		}
		else if(currentToken.getType() == TokenType.LPAREN) { 	// ( expression )
			this.eat(TokenType.LPAREN);
			//Node result = expr();
			this.eat(TokenType.RPAREN);
			//return result;
		}
		return null;
	}
	
	public Node term() {
		// a term is the result of a */ // operation of 1+ factors
		
		Token op = currentToken;
		
		//if(op.getType() == TokenType.OP)
		return null;
	}

	
	public Node variable() {
		Node node = new Var(currentToken);
		
		this.eat(TokenType.ID);
		return node;
	}
	

}
