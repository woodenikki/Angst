package parser.tree;

import lexer.Token;

public class Stringy extends Node{
	private String value;
	private Token token;
	
	public Stringy(Token token) {
		this.type = Datatype.STRINGY;
		this.token = token;
		this.value = token.getData();
	}

	public String getValue() {
		return value;
	}

	public Token getToken() {
		return token;
	}	
}
