package main;
import lexer.*;
import parser.*;
import interpreter.*;
//import interpreter

public class Main {
	
	public static final String sourceCode = "sourceCode.angy";
	public final static Keyword[] allKeys = {Keyword.ARRAY, Keyword.IF, Keyword.ELSEIF, Keyword.ELSE, Keyword.DO, Keyword.WHILE,
			Keyword.ENDLOOP, Keyword.PRINT, Keyword.NOT, Keyword.RANDOM, Keyword.PRIVATE, Keyword.PUBLIC, Keyword.TRY, Keyword.CATCH, 
			Keyword.THROWEXCEPTION, Keyword.STRINGY, Keyword.NUMBER, Keyword.BOOL, Keyword.NULL, Keyword.STARTPROGRAM, Keyword.ENDPROGRAM};
	
	public static void main(String[] args) {
		Lexer lexer = new Lexer(sourceCode);
		System.out.println("_______________________________");
		//printTokens(lexer);
		//printLexer(lexer);
		
		Parser parser = new Parser(lexer);
		parser.parse();
		//Interpreter interpreter = new Interpreter(parser);
		//interpreter.interpret();
		//System.out.println(interpreter.variables);
	}

	
	public static void printLexer(Lexer myLex) {

		System.out.println("________________\n");
		for (Token t : myLex.getTokens()) {
			System.out.println("Lexeme: " + t.type + "\t \t \t Token: " + t.data);
			if (t.type == TokenType.KEYWORD) {
				for(int i = 0; i < allKeys.length; i++) {
					if(t.data.equals(allKeys[i].getKey())) {
						System.out.println("\t\t"+allKeys[i].getDescription());
					}
				}

			}

		}
	}
	
	public static void printTokens(Lexer lexer) {
		System.out.println();
		for(int i = 0; i < lexer.getTokens().size(); i++) {
			System.out.print(lexer.getTokens().get(i).getData()+" ");
		}
		System.out.println();
	}
}
	

