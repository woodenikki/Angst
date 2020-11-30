package parser.tree;
import lexer.Token;

public class Var extends Node{

	private Token token;
	private String name;
	private boolean isprivate;
	
	public Var(Token token) {
		this.token = token;
		this.name = token.getData();
		this.type = type;
	}

	public Token getToken() {
		return token;
	}

	public String getName() {
		return name;
	}
	
	public void setVarType(Datatype t) {
		this.type = t;
	}
	
	public void setPrivate(boolean isprivate) {
		this.isprivate = isprivate;
	}
}
