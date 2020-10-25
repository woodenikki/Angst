import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://stackoverflow.com/questions/17848207/making-a-lexical-analyzer
//http://giocc.com/writing-a-lexer-in-java-1-7-using-regex-named-capturing-groups.html

public class Lexer {

	public static enum Type {
		LPAREN("("),
		RPAREN(")"),
		LCURLY("{"),
		RCURLY("}"),
		LSQUARE("["),
		RSQUARE("]"),
		SPACE(" "),
		SYMB("! | @"),
		LETTER("a | b | c"),
		ZERO(""),
		NONZERO(""),
		DIG(""),
		NUM("-?[0-9]+"),
		ID(""),
		OP(""),
		STR(""),
		END_STMT(""),
		ASSIGN(""),
		COMMENT(""),
		OTHER("")
		;
		
		public final String pattern;
		
		private Type(String pattern) {
			this.pattern = pattern;
		}
	}
	
	public static class Token {
		public final Type t;
		public final String c;
		
		public Token(Type t, String c) {
			this.t = t;
			this.c = c;
		}
		
		public String toString() {
			if(t == Type.OTHER) {
				return "OTHER<" + c + ">";
			}
			return t.toString();
		}
		public Type getType() {
			return t;
		}
		
		public String getLexeme() {
			return c;
		}
	}
	
	public static String getAtom(String s, int i) {
		int j = i;
		for(; j < s.length(); ) {
			if(Character.isLetterOrDigit(s.charAt(j))) {
				j++;
			} else {
				return s.substring(i, j);
			}
		}
		return s.substring(i, j);
	}
	
	public static List<Token> lex(String input){
		List<Token> result = new ArrayList<Token>();
				
		for(int i = 0; i < input.length(); ) {
			switch(input.charAt(i)) {
			case '(':
				result.add(new Token(Type.LPAREN, "("));
				i++;
				break;
			case ')':
				result.add(new Token(Type.RPAREN, ")"));
				i++;
				break;
			case '{':
				result.add(new Token(Type.LCURLY, "{"));
				i++;
				break;
			case '}':
				result.add(new Token(Type.RCURLY, "}"));
				i++;
				break;
			case ' ':
				result.add(new Token(Type.SPACE, " "));
				i++;
				break;
				/*
			case :
				result.add(new Token(, ""));
				i++;
				break;
				/*
			case '':
				result.add(new Token(, ""));
				i++;
				break;
			case '':
				result.add(new Token(, ""));
				i++;
				break;
			case '':
				result.add(new Token(, ""));
				i++;
				break;
			case '':
				result.add(new Token(, ""));
				i++;
				break;
				*/
			default:
				if(Character.isWhitespace(input.charAt(i))) {
					i++;
				}else {
					String atom = getAtom(input, i);
					i += atom.length();
					result.add(new Token(Type.OTHER, atom));
				}
				
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String code = "";
		try {
			Scanner reader = new Scanner(new File("sampleAngstCode.txt"));
			System.out.println("read file");
			while(reader.hasNext()) {
				code += reader.next();
				
			}
			System.out.println(code);
			
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't read file");
			e.printStackTrace();
			System.exit(3);
		}
		List<Token> tokens = lex(code);
		for(Token t: tokens) {
			System.out.println("Lexeme: " + t.getLexeme() + "\t Token: "+ t.getType());
		}
	}
}