package lexer;
//https://data-flair.training/blogs/operators-in-c-and-cpp/
public enum OpType {
	/***Arithmetic***/
	ADD		("\\+"),
	SUB		("\\-"),
	MULT	("\\*"),
	DIV		("\\/"),
	MOD		("%"),
	INCR	("\\+\\+"),
	DECR	("\\-\\-"),
	/***Relational***/
	EQ		("[=][=]"),
	NEQ		("NOT![=][=]"),
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

	
	private OpType(String pattern) {
		this.pattern = pattern;
	}
	
	String getPattern() {
		return pattern;
	}
}
