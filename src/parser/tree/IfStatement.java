package parser.tree;

public class IfStatement extends Node{
	private Node cond;
	private Node then;
	private int count;
	
	public IfStatement(Node cond, Node then, int count) {
		this.cond = cond;
		this.then = then;
		this.count = count;
	}

	public Node getCond() {
		return this.cond;
	}

	public Node getThen() {
		return this.then;
	}	
	
	public int getOther() {
		return this.count;
	}
}
