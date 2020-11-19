package parser.tree;
import lexer.Token;

public class Var extends Node{

	private Token token;
	private String name;
	
	public Var(Token token) {
		this.token = token;
		this.name = token.getData();
	}

	public Token getToken() {
		return token;
	}

	public String getName() {
		return name;
	}
}
