package parser.tree;

import lexer.Token;

public class Num extends Node{
	private int value;
	//private Token token;
	
	public Num(Token token) {
		this.type = Datatype.NUMBER;
		this.value = Integer.parseInt(token.getData());
	}
	
	public Num(int v) {
		this.type = Datatype.NUMBER;
		this.value = v;
	}

	public int getValue() {
		return value;
	}

}
