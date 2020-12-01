package lexer;
public enum TokenType {

		WSPACE		("(\\s+)"),
		COMMENT		("\\b(ugh)\\b(.| \n)*"),					
		LPAREN		("(\\()"),
		RPAREN		("(\\))"),
		LSQUARE		("(\\[)"),
		RSQUARE		("(\\])"),
		LCURLY		("(\\{)"),
		RCURLY		("(\\})"),
		OP			(getOps()),						//"(\\+ | \\- | \\/ | \\*)"
		KEYWORD		(getKeywords()),
		BOOL		(getBools()),
		ASSIGN		(getAssignments()),
		STRINGY		("\"(.*?)\""),	
		NUMBER		("([0-9]+)"),
		ENDSTMT		(getEndOfStatements()),		
		ID			("([a-zA-Z][0-9a-zA-Z]*)"),
		ERROR		("(.)+"),						//2nd last
		EOF			(""),							//always last!!!
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
					"=",
					"is"
					);
					
			return result;
		}
		
		public static String getBools() {
			//"(slaps|rad|sick)|(eh|meh|bleh)"
			String result = String.join("|", 
					"cool",		//true
					"fire",		//true
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
		
		public static String getOps() {
			String result = String.join("|", 
					OpType.INCR.getPattern(),
					OpType.DECR.getPattern(),
					OpType.PLUS.getPattern(),
					OpType.MINUS.getPattern(),
					OpType.MULT.getPattern(),
					OpType.DIV.getPattern(),
					OpType.MOD.getPattern(),
					OpType.GTEQ.getPattern(),
					OpType.LTEQ.getPattern(),
					OpType.EQ.getPattern(),
					OpType.NEQ.getPattern(),
					OpType.GT.getPattern(),
					OpType.LT.getPattern(),
					OpType.AND.getPattern(),
					OpType.OR.getPattern(),
					OpType.NOT.getPattern()
										
					);
			
			return result;					
		}
		
		public static String getKeywords() {
			String result = String.join("|",
					Keyword.ARRAY.getKey(),
					Keyword.IF.getKey(),
					Keyword.ELSEIF.getKey(),
					Keyword.ELSE.getKey(),
					Keyword.DO.getKey(),
					Keyword.WHILE.getKey(),
					Keyword.ENDLOOP.getKey(),
					Keyword.PRINT.getKey(),
					Keyword.NOT.getKey(),
					Keyword.RANDOM.getKey(),
					Keyword.PRIVATE.getKey(),
					Keyword.PRIVATE.getKey(),
					Keyword.PUBLIC.getKey(),
					Keyword.TRY.getKey(),
					Keyword.CATCH.getKey(),
					Keyword.THROWEXCEPTION.getKey(),
					Keyword.STRINGY.getKey(),
					Keyword.NUMBER.getKey(),
					Keyword.BOOL.getKey(),
					Keyword.NULL.getKey(),
					Keyword.STARTPROGRAM.getKey(),
					Keyword.ENDPROGRAM.getKey()
									
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