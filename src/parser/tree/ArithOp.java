package parser.tree;
import lexer.Token;

public class ArithOp extends Node{
/*
	PLUS	("\\+"),
	MINUS	("\\-"),
	MULT	("\\*"),
	DIV		("\\/"),
	MOD		("%"),
	INCR	("\\+\\+"),
	DECR	("\\-\\-"),
 */
	private Node left, right;
	private Token op;
	
	public ArithOp(Node left, Token op, Node right) { // *, /, %, +, -
		this.type = Datatype.NUMBER; //TODO: strings? maaaybe bools if feelin frisky
		this.left = left;
		this.op = op;
		this.right = right;
	}
	
	public ArithOp(Node left, Token op) { //INCR and DECR
		this.left = left;
		this.op = op;
		this.right = null;
	}

	public Node getLeft() {
		return left;
	}

	public Token getOp() {
		return op;
	}

	public Node getRight() {
		return right;
	}	
}
