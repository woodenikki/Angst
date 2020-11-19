package parser.tree;

public class Program extends Node{
	private Node statementList;
	
	public Program(Node statementList) {
		this.statementList = statementList;
	}
	
	public Node getStatementList() {
		return statementList;
	}
}
