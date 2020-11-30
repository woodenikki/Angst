package parser.tree;

import lexer.Token;

public class Assign extends Node {

	private Node left, right;
	private Token op;
	
	public Assign(Node left, Token op, Node right) {
		//this.type = Datatype.VOID;
		this.left = left;
		this.right = right;
		this.op = op;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public Token getOp() {
		return op;
	}
	
}