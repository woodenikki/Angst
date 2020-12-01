package lexer;
//https://data-flair.training/blogs/operators-in-c-and-cpp/
public enum OpType {
	/***Arithmetic***/
	PLUS	("\\+"),
	MINUS	("\\-"),
	MULT	("\\*"),
	DIV		("\\/"),
	MOD		("%"),
	INCR	("\\+\\+"),
	DECR	("\\-\\-"),
	//TODO: exponent?
	/***Relational***/
	EQ		("[=][=]"),
	NEQ		("NOT![=]"),
	GTEQ	(">="),
	LTEQ	("<="),
	GT		(">"),
	LT		("<"),
	/***Logical***/
	AND		("&&"),
	OR		("\\|\\|"),
	NOT		("NOT!")
	;
	

	public final String pattern;
	public final String operation;

	
	private OpType(String pattern) {
		this.pattern = pattern;
		operation = pattern.replaceAll("\\\\", "");
	}
	
	public String getPattern() {
		return pattern;
	}
	
	public String getOperation() {
		return operation;
	}
}
