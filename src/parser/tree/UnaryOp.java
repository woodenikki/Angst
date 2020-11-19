package parser.tree;
import lexer.Token;
//increment ++	decrement  -- 	not NOT!	minus -	.. -x

public class UnaryOp extends Node{
	private Token op;
	private Node factor;
	
	public UnaryOp(Token op, Node expr) {
		this.op = op;
		this.factor = expr;
	}

	public Token getOp() {
		return op;
	}

	public Node getFactor() {
		return factor;
	}
}
