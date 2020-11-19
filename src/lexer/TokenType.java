package lexer;
public enum TokenType {

		WSPACE		("(\\s+)"),
		COMMENT		("\\b(ugh)\\b(.| \n)*"),					
		LPAREN		("(\\()"),
		RPAREN		("(\\))"),
		LCURLY		("(\\{)"),
		RCURLY		("(\\})"),
		LSQUARE		("(\\[)"),
		RSQUARE		("(\\])"),
		KEYWORD		(getKeywords()),
		BINARY		(getBools()),
		ASSIGN		(getAssignments()),
		STRING		("\"(.*?)\""),	
		OP			("(\\+ | \\- | \\/ | \\*)"),
		NUMBER		("([0-9]+)"),
		ENDSTMT		(getEndOfStatements()),		// maybe ~	[or whatever|i guess|;|but who cares]
		ID			("([a-zA-Z][0-9a-zA-Z]*)"),
		SYMBOL		("[^A-Z^a-z^0-9]"),
		ERROR		("(.)+"),										//2nd last
		EOF			(""),											//always last!!!
		;
		

		public final String pattern;

		
		private TokenType(String pattern) {
			this.pattern = pattern;
		}
		
		public String getPattern() {
			return this.pattern;
		}
		
		public static String getAssignments() {
			String result = String.join("|", 
					"could be",	
					"should be",	
					"="
					);
					
			return result;
		}
		
		public static String getBools() {
			//"(slaps|rad|sick)|(eh|meh|bleh)"
			String result = String.join("|", 
					"cool",		//true
					"rad",		//true
					"sick",		//true
					"eh",		//false
					"meh",		//false
					"bleh"		//false
					);
					
			return result;
					
		}
		
		public static String getEndOfStatements() {
			String result = String.join("|",
					"(;)",
					
					"or whatever",
					"i guess",
					"but who cares"

					);
			
			
			return result;
		}
		
		public static String getKeywords() {
			String result = String.join("|",
					
					new Keyword("buncha").getKey(),
					new Keyword("as if").getKey(),
					new Keyword("unless..").getKey(),
					new Keyword("but whatever").getKey(),
					new Keyword("do i hafta").getKey(),
					new Keyword("yech, still").getKey(),
					new Keyword("mumble").getKey(),
					new Keyword("NO!").getKey(),
					new Keyword("anxious").getKey(),
					new Keyword("my").getKey(),
					new Keyword("anybodies").getKey(),
					new Keyword("maybe").getKey(),
					new Keyword("or not.").getKey(),
					new Keyword("throws shade").getKey(),
					new Keyword("string").getKey(),
					new Keyword("number").getKey(),
					new Keyword("jack all").getKey(),
					new Keyword("bool").getKey(),
					new Keyword("hear me out..").getKey(),
					new Keyword("thats deep.").getKey()
					
					//new Keyword("").getKey(),
				
					);
						
			return result;
		}
		
		/*
		public static String getOperators() {
			String result = String.join("|", 
					
					//new OP("==").getPattern(),
					//new OP("NOT!").getPattern()
					
					
					
					);
					
					
		}
		*/
				
	}