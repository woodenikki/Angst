package lexer;
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
//https://rubular.com/r/Db8fv6RQht

public class Lexer {

	Matcher matcher;
	String text;
	boolean skipError;
	static List<Token> tokens;
	int pos;

	
	public Lexer(String filename) {
		pos = 0;
		String code = "";
		try {

			/************* READ IN FILE: this one allows spaces! c: **************/
			FileReader fr = new FileReader(new File(filename));
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
		
		tokens = lex(code);
	}
	
	public List<Token> getTokens(){
		return tokens;
	}
	
	public int getPos() {
		return this.pos;
	}
	
	public Token getNextToken() {
		pos++;
		return tokens.get(pos);
	}

	// WHITESPACE COMMENT LPAREN RPAREN LCURLY RCURLY LSQUARE RSQUARE KEYWORD STRING
	// NUMBER OPERATOR IDENTIFIER ENDOFSTMT EOF ERROR
	public static List<Token> lex(String input) {
		List<Token> tokens = new ArrayList<Token>();

		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for (TokenType tokenTokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenTokenType.name(), tokenTokenType.pattern));
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

		Matcher match = tokenPatterns.matcher(input);
		while (match.find()) {

			// probably ignore

			if (match.group(TokenType.COMMENT.name()) != null) {
				tokens.add(new Token(TokenType.COMMENT, match.group(TokenType.COMMENT.name())));
				continue;
			}
			if (match.group(TokenType.LPAREN.name()) != null) {
				tokens.add(new Token(TokenType.LPAREN, match.group(TokenType.LPAREN.name())));
				continue;
			}
			if (match.group(TokenType.RPAREN.name()) != null) {
				tokens.add(new Token(TokenType.RPAREN, match.group(TokenType.RPAREN.name())));
				continue;
			}
			if (match.group(TokenType.LCURLY.name()) != null) {
				tokens.add(new Token(TokenType.LCURLY, match.group(TokenType.LCURLY.name())));
				continue;
			}
			if (match.group(TokenType.RCURLY.name()) != null) {
				tokens.add(new Token(TokenType.RCURLY, match.group(TokenType.RCURLY.name())));
				continue;
			}
			if (match.group(TokenType.LSQUARE.name()) != null) {
				tokens.add(new Token(TokenType.LSQUARE, match.group(TokenType.LSQUARE.name())));
				continue;
			}
			if (match.group(TokenType.RSQUARE.name()) != null) {
				tokens.add(new Token(TokenType.RSQUARE, match.group(TokenType.RSQUARE.name())));
				continue;
			}
			if (match.group(TokenType.OP.name()) != null) {
				tokens.add(new Token(TokenType.OP, match.group(TokenType.OP.name())));
				continue;
			}
			if (match.group(TokenType.KEYWORD.name()) != null) {
				tokens.add(new Token(TokenType.KEYWORD, match.group(TokenType.KEYWORD.name())));
				continue;
			}
			if (match.group(TokenType.BINARY.name()) != null) {
				tokens.add(new Token(TokenType.BINARY, match.group(TokenType.BINARY.name())));
				continue;
			}
			if (match.group(TokenType.ASSIGN.name()) != null) {
				tokens.add(new Token(TokenType.ASSIGN, match.group(TokenType.ASSIGN.name())));
				continue;
			}
			if (match.group(TokenType.STRING.name()) != null) {
				tokens.add(new Token(TokenType.STRING, match.group(TokenType.STRING.name())));
				continue;
			}
			if (match.group(TokenType.NUMBER.name()) != null) {
				tokens.add(new Token(TokenType.NUMBER, match.group(TokenType.NUMBER.name())));
				continue;
			}
			if (match.group(TokenType.ENDSTMT.name()) != null) {
				tokens.add(new Token(TokenType.ENDSTMT, match.group(TokenType.ENDSTMT.name())));
				continue;
			}
			if (match.group(TokenType.ID.name()) != null) {
				tokens.add(new Token(TokenType.ID, match.group(TokenType.ID.name())));
				continue;
			}
			if (match.group(TokenType.SYMBOL.name()) != null) {
				tokens.add(new Token(TokenType.SYMBOL, match.group(TokenType.SYMBOL.name())));
				continue;
			}
			if (match.group(TokenType.ERROR.name()) != null) {
				tokens.add(new Token(TokenType.ERROR, match.group(TokenType.ERROR.name())));
				continue;
			}
			if (match.group(TokenType.WSPACE.name()) != null) {
				// tokens.add(new Token(TokenType.WSPACE, match.group(TokenType.WSPACE.name())));
				continue;
			}
		}
		return tokens;

	}


}