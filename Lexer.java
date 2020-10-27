import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//http://giocc.com/writing-a-lexer-in-java-1-7-using-regex-named-capturing-groups.html
//https://www.w3schools.com/java/java_regex.asp
//https://www.codexpedia.com/regex/regex-symbol-list-and-regex-examples/

public class Lexer {

	Matcher matcher;
	String text;
	boolean skipError;

	public static class Token {

		public int tokenNumber;
		public String data;
		public Type type;

		public Token(Type type, String data) {
			this.data = data;
			this.type = type;
		}

	}

	// WHITESPACE COMMENT LPAREN RPAREN LCURLY RCURLY LSQUARE RSQUARE KEYWORD STRING
	// NUMBER OPERATOR IDENTIFIER ENDOFSTMT EOF ERROR
	public static List<Token> lex(String input) {
		List<Token> tokens = new ArrayList<Token>();

		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for (Type tokenType : Type.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

		Matcher match = tokenPatterns.matcher(input);
		while (match.find()) {

			// probably ignore

			if (match.group(Type.COMMENT.name()) != null) {
				tokens.add(new Token(Type.COMMENT, match.group(Type.COMMENT.name())));
				continue;
			}
			if (match.group(Type.LPAREN.name()) != null) {
				tokens.add(new Token(Type.LPAREN, match.group(Type.LPAREN.name())));
				continue;
			}
			if (match.group(Type.RPAREN.name()) != null) {
				tokens.add(new Token(Type.RPAREN, match.group(Type.RPAREN.name())));
				continue;
			}
			if (match.group(Type.LCURLY.name()) != null) {
				tokens.add(new Token(Type.LCURLY, match.group(Type.LCURLY.name())));
				continue;
			}
			if (match.group(Type.RCURLY.name()) != null) {
				tokens.add(new Token(Type.RCURLY, match.group(Type.RCURLY.name())));
				continue;
			}
			if (match.group(Type.LSQUARE.name()) != null) {
				tokens.add(new Token(Type.LSQUARE, match.group(Type.LSQUARE.name())));
				continue;
			}
			if (match.group(Type.RSQUARE.name()) != null) {
				tokens.add(new Token(Type.RSQUARE, match.group(Type.RSQUARE.name())));
				continue;
			}
			if (match.group(Type.KEYWORD.name()) != null) {
				tokens.add(new Token(Type.KEYWORD, match.group(Type.KEYWORD.name())));
				continue;
			}
			if (match.group(Type.BINARY.name()) != null) {
				tokens.add(new Token(Type.BINARY, match.group(Type.BINARY.name())));
				continue;
			}
			if (match.group(Type.STRING.name()) != null) {
				tokens.add(new Token(Type.STRING, match.group(Type.STRING.name())));
				continue;
			}
			if (match.group(Type.OP.name()) != null) {
				tokens.add(new Token(Type.OP, match.group(Type.OP.name())));
				continue;
			}
			if (match.group(Type.NUMBER.name()) != null) {
				tokens.add(new Token(Type.NUMBER, match.group(Type.NUMBER.name())));
				continue;
			}
			if (match.group(Type.ENDSTMT.name()) != null) {
				tokens.add(new Token(Type.ENDSTMT, match.group(Type.ENDSTMT.name())));
				continue;
			}
			if (match.group(Type.ID.name()) != null) {
				tokens.add(new Token(Type.ID, match.group(Type.ID.name())));
				continue;
			}
			if (match.group(Type.SYMBOL.name()) != null) {
				tokens.add(new Token(Type.SYMBOL, match.group(Type.SYMBOL.name())));
				continue;
			}
			if (match.group(Type.ERROR.name()) != null) {
				tokens.add(new Token(Type.ERROR, match.group(Type.ERROR.name())));
				continue;
			}
			if (match.group(Type.WSPACE.name()) != null) {
				// tokens.add(new Token(Type.WSPACE, match.group(Type.WSPACE.name())));
				continue;
			}
		}
		return tokens;

	}

	public static void main(String[] args) {
		String code = "";
		try {

			/************* READ IN FILE: this one allows spaces! c: **************/
			FileReader fr = new FileReader(new File("sampleAngstCode.txt"));
			BufferedReader br = new BufferedReader(fr);
			int ch;
			while ((ch = br.read()) != -1) {
				// chars.add(ch);
				code += (char) ch;
			}

			System.out.println(code);

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't read file");
			e.printStackTrace();
			System.exit(3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("________________\n");

		List<Token> tokens = lex(code);

		for (Token t : tokens) {
			System.out.println("Lexeme: " + t.type + "\t \t \t Token: " + t.data);
			if (t.type == Type.KEYWORD) {
				System.out.println("\t\t"+ new Keyword(t.data).getDescript());
			}

		}

	}
}