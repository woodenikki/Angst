package parser.tree;

public class WhileStatement extends Node{
	private Node thingtodo;
	private Node cond;
	
	public WhileStatement(Node thing, Node cond) {
		this.thingtodo = thingtodo;
		this.cond = cond;
	}

	public Node getCond() {
		return this.cond;
	}
	
	public Node getStatement() {
		return thingtodo;
	}

}
