package main;
import lexer.*;
import parser.*;
//import interpreter

public class Main {
	
	public static final String sourceCode = "sampleAngstCode.txt";
	
	public static void main(String[] args) {
		runLexer();
	}
	
	public static void runLexer() {
		Lexer myLex = new Lexer(sourceCode);

		System.out.println("________________\n");
		for (Token t : myLex.getTokens()) {
			System.out.println("Lexeme: " + t.type + "\t \t \t Token: " + t.data);
			if (t.type == TokenType.KEYWORD) {
				System.out.println("\t\t"+ new Keyword(t.data).getDescript());
			}

		}
	}
}
	

