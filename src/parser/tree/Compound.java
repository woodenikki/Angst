package parser.tree;

import java.util.List;
import java.util.ArrayList;


public class Compound extends Node {

	private List<Node> children;
	
	public Compound() {
		this.children = new ArrayList<Node>();
	}
	
	public List<Node> getChildren() {
		return this.children;
	}
}