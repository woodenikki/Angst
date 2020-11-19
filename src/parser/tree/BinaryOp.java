package parser.tree;
import lexer.Token;

public class BinaryOp {

	private Node left, right;
	private Token op;
	
	public BinaryOp(Node left, Token op, Node right) {
		this.left = left;
		this.op = op;
		this.right = right;
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
