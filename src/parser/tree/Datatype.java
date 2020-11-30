package parser.tree;

public enum Datatype {
	
	NUMBER("number"),
	STRINGY("stringy"),
	BOOL("bool")
	//,VOID("void")
	;
	

	public final String type;

	
	private Datatype(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
