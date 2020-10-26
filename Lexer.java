import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//1. https://stackoverflow.com/questions/17848207/making-a-lexical-analyzer
//2. http://giocc.com/writing-a-lexer-in-java-1-7-using-regex-named-capturing-groups.html
//3. https://www.w3schools.com/java/java_regex.asp
//4. https://gist.github.com/salavert/4636374
//5. https://www.codexpedia.com/regex/regex-symbol-list-and-regex-examples/
//6. https://stackoverflow.com/questions/61904326/is-there-a-way-to-identify-tokens-in-a-string-while-also-going-by-longest-substr
//7. https://stackoverflow.com/questions/18627719/returning-java-regex-words-spaces-special-characters-double-quotes


public class Lexer {
	//https://gist.github.com/salavert/4636374
	
    public static int _WHITESPACE 	= 0;
    public static int _COMMENT 		= 1;
    public static int _LPAREN		= 2;
    public static int _RPAREN		= 3;
    public static int _LCURLY		= 4;
    public static int _RCURLY		= 5;
    public static int _LSQUARE		= 6;
    public static int _RSQUARE		= 7;
    public static int _KEYWORD		= 8;
    public static int _STRING		= 9;
    public static int _NUMBER		= 10;
    public static int _OPERATOR		= 11;
    public static int _IDENTIFIER	= 12;
    public static int _SYMBOL		= 13;
    public static int _ENDOFSTMT	= 14;
    public static int _ERROR		= 15;
    public static int _EOF			= 16;
    
    
    Matcher matcher;
    String text;
    boolean skipError;

	public static enum Type {

		WHITESPACE	(0),
		COMMENT		(1),
		LPAREN		(2),
		RPAREN		(3),
		LCURLY		(4),
		RCURLY		(5),
		LSQUARE		(6),
		RSQUARE		(7),
		KEYWORD		(8),
		STRING		(9),		
		NUMBER		(10),
		OPERATOR	(11),
		IDENTIFIER	(12),
		SYMBOL		(13),
		ENDOFSTMT	(14),
		ERROR		(15),				//2nd last
		EOF			(16),				//always last!!!
		;
		
		
		public int typenum;

		//public final Pattern pattern;
		
		private Type(int x) {
			this.typenum = x;
			
		}
				
	}
	
	public static class Token {
		
        public int tokenNumber;
        public String tokenValue;
        public Type tokenType;
		
        public Token(int tokenNumber, String tokenValue)
        {
            this.tokenNumber = tokenNumber;
            this.tokenValue = tokenValue;
            tokenType = getTypeName(tokenNumber);
        }
        
		private Type getTypeName(int typenum) {
			
			switch(typenum) {
			case 0:
				return Type.WHITESPACE;
			case 1:
				return Type.COMMENT;
			case 2:
				return Type.LPAREN;
			case 3:
				return Type.RPAREN;
			case 4:
				return Type.LCURLY;
			case 5:
				return Type.RCURLY;
			case 6:
				return Type.LSQUARE;
			case 7:
				return Type.RSQUARE;
			case 8:
				return Type.KEYWORD;
			case 9:
				return Type.STRING;
			case 10:
				return Type.NUMBER;
			case 11:
				return Type.OPERATOR;
			case 12:
				return Type.IDENTIFIER;
			case 13:
				return Type.SYMBOL;
			case 14:
				return Type.ENDOFSTMT;
			case 15:
				return Type.ERROR;
			case 16:
				return Type.EOF;
			default:
				return Type.ERROR;
			}
		}
   
	} 
	// WHITESPACE COMMENT LPAREN RPAREN LCURLY RCURLY LSQUARE RSQUARE KEYWORD STRING NUMBER OPERATOR IDENTIFIER ENDOFSTMT EOF ERROR
	public static List<Token> lex(String input){
		List<Token> tokens = new ArrayList<Token>();
		
		/******************* FIND MATCHES ************************/
		
		
		return tokens;
	}
	
    public Lexer(String text)
    {

        String whitespace 	= "(\\s+)";
        String comment 		="([ugh][\\s][.]+)";
        String lparen 		= "(\\()";
        String rparen 		= "(\\))";
        String lcurly		= "(\\{)";
        String rcurly 		= "(\\})";
        String lsquare	 	= "(\\[)";
        String rsquare		= "(\\])";
        String keyword 		= "(if|then|else|endif|while|do|endwhile|skip|break|return)";        
        String string 		= "([\"][a-zA-Z0-9])*[\"]"; 		// link 7
        String number 		= "([0-9]+)";
        String operator 	= "(\\+ | \\- | \\/ | \\*)";
        String id 			= "([a-zA-Z][0-9a-zA-Z]*)";
        String symbol		= "[^A-Z^a-z^0-9]";					//????? g matching symbol? maybe matching last possible
        String endofstmt 	= "(;)";							// maybe ~
        String error 		= "(.)+";							// must be last and able to capture one character
				
        //String regex = symbol;
        String regex = String.join("|", whitespace, comment, lparen, rparen, lcurly, rcurly, lsquare, rsquare, keyword, string, number, operator, id, symbol, endofstmt, error);

        Pattern p = Pattern.compile(regex);
        this.text = text;
        matcher = p.matcher(this.text);
        skipError = false;
    }

    public Token next()
    {
        Token token = null;
        for(;;) {
            if (!matcher.find())
                return new Token(_EOF, "<EOF>");
            for (int tokenNumber = 1; tokenNumber <= 13; tokenNumber++) {
                String tokenValue = matcher.group(tokenNumber);
                if (tokenValue != null) {
                    token = new Token(tokenNumber, tokenValue);
                    break;
                }
            }
            if (token.tokenNumber == _ERROR) {
                if (!skipError) {
                    skipError = true; // we don't want successive errors
                    return token;
                }
            }
            else {
                skipError = false;
                if (token.tokenNumber != _WHITESPACE)
                    return token;
            }
        }
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
    	System.out.println("________________\n");
        Lexer lexer = new Lexer(code); 
        for(;;) {
            Token token = lexer.next();
            System.out.println("Lexeme: " + token.tokenValue + "\t Token: "+ token.getTypeName(token.tokenNumber));
            if (token.tokenNumber == _EOF)
                break;
        }
        /*
        List<Token> tokens = lex(code);
        
        for(Token t: tokens) {
			System.out.println("Lexeme: " + t.data + "\t Token: "+ t.type);
		}      
        
        */
    }
}