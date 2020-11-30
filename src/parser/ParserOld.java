package parser;
import lexer.*;
import parser.tree.*;
//https://github.com/rgwohlbold/interpreter-ice

public class ParserOld {
	
	private Lexer lexer;
	private Token currentToken;
	
	public ParserOld(Lexer lexer) {
		this.lexer = lexer;
		currentToken = lexer.getNextToken();
	}
	

	
	public Node statement() {
		if(this.currentToken.getType() == TokenType.ID) {
			return this.assignmentStatement();
		}
		else if (this.currentToken.getType() == TokenType.ENDSTMT) {
			return this.statement();
		}
		throw new RuntimeException("Parser Error in Statement");
	}
	
	public Node statementList() {
		Compound rootNode = new Compound();
		while (currentToken.getType() == TokenType.ID) {
			Node node = this.statement();
			this.eat(TokenType.ENDSTMT);
			rootNode.getChildren().add(node);
		}
		return rootNode;
	}
	
	public Node program() {
		Node result = statementList();
		while (currentToken.getType() == TokenType.ENDSTMT) {
			this.eat(TokenType.ENDSTMT);
		}
		this.eat(TokenType.EOF);
		return result;
	}
	
	

	
	public Node parse() {
		Node root = this.program();
		return root;
	}
	

}
