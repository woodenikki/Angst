package parser.tree;

import lexer.TokenType;

public abstract class Node {
	public Datatype type;
	
	public Datatype getType() {
		return type;
	}
}
