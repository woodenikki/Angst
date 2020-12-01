package lexer;

import java.util.List;

public enum Keyword {
	ARRAY("buncha"),
	IF("as if"),
	ELSEIF("unless.."),
	ELSE("but whatever"),
	DO("do i hafta"),
	WHILE("even if"),
	ENDLOOP("yech"),
	PRINT("mumble"),
	NOT("NOT!"),
	RANDOM("anxious"),
	PRIVATE("my"),
	PUBLIC("anybodies"),
	TRY("maybe"),
	CATCH("or not."),
	THROWEXCEPTION("throw shade"),
	STRINGY("stringy"),
	NUMBER("number"),
	BOOL("bool"),
	NULL("nada"),
	STARTPROGRAM("hear me out.."),
	ENDPROGRAM("thats deep.")
	
	;
	public final String descript;
	public final String key;

	
	private Keyword(String key) {
		this.key = key;
		descript = setDescript(key);
		
	}
	
	public String getDescription() {
		return descript;
	}
	
	public String getKey() {
		return key;
	}

	
	public String setDescript(String k) {
		switch(k){
			case "is":
				return "";
				case "buncha":
				return "Array";
			case "as if":
				return "If";
			case "unless..":
				return "Else if";
			case "but whatever":
				return "Else statement";
			case "do i hafta":
				return "Do-while loop";
			case "yech, still":
				return "while loop";
			case "mumble":
				return "print statement";
			case "NOT!":
				return "'not' operator (!)";
			case "anxious":
				return "Random number";
			case "my":
				return "'private'";
			case "anybodies":
				return "'public'";
			case "maybe":
				return "try";
			case "or not.":
				return "catch";
			case "throw shade":
				return "Throws exception";
			case "stringy":
				return "String";
			case "number":
				return "pos/neg integer";
			case "should be":
				return "assignment (=)";
			case "null":
				return "NULL or 'nothing'";
			case "bool":
				return "boolean";
			case "thats deep.":
				return "end program";
			case "hear me out..":
				return "start program";
			default:
					return "";
		}
	}
}
