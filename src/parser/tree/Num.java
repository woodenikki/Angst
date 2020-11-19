package parser.tree;

import lexer.Token;

public class Num extends Node{
	private int value;
	private Token token;
	
	public Num(Token token) {
		this.token = token;
		this.value = Integer.parseInt(token.getData());
	}

	public int getValue() {
		return value;
	}

	public Token getToken() {
		return token;
	}	
}
