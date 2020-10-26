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
	
    public static int _WHITESPACE 	= 1;
    public static int _ERROR 		= 2;
    public static int _IDENTIFIER 	= 3;
    public static int _NUMBER 		= 4;
    public static int _OPERATOR 	= 5;
    public static int _STRING	 	= 6;
    public static int _KEYWORD		= 7;	
    public static int _ENDOFSTMT	= 8;
    public static int _EOF			= 9;
    public static int _COMMENT		= 10;
    public static int _LPAREN 		= 11;
    public static int _RPAREN 		= 12;
    public static int _LCURLY		= 13;
    public static int _RCURLY		= 14;
    
    
    Matcher matcher;
    String text;
    boolean skipError;

	public static enum Type {
		ERROR		(0),
		WHITESPACE	(1),
		IDENTIFIER	(2),
		NUMBER		(3),
		OPERATOR	(4),
		STRING		(5),
		KEYWORD		(6),
		ENDOFSTMT	(7),
		EOF			(8),
		COMMENT		(9),
		LPAREN		(10),
		RPAREN		(11),
		LCURLY		(12),
		RCURLY		(13)
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
			case 1:
				return Type.WHITESPACE;
			case 2:
				return Type.IDENTIFIER;
			case 3:
				return Type.NUMBER;
			case 4:
				return Type.OPERATOR;
			case 5:
				return Type.STRING;
			case 6:
				return Type.KEYWORD;
			case 7:
				return Type.ENDOFSTMT;
			case 8:
				return Type.EOF;
			case 9:
				return Type.COMMENT;
			case 10:
				return Type.LPAREN;
			case 11:
				return Type.RPAREN;
			case 12:
				return Type.LCURLY;
			case 13:
				return Type.RCURLY;
			default:
				return Type.ERROR;
			}
		}
   
	}

	public static List<Token> lex(String input){
		List<Token> tokens = new ArrayList<Token>();
		
		/******************* FIND MATCHES ************************/
		
		
		return tokens;
	}
	
    public Lexer(String text)
    {
    	// WHITESPACE ERROR IDENTIFIER NUMBER OPERATOR STRING KEYWORD ENDOFSTMT EOF COMMENT LPAREN RPAREN LCURLY RCURLY OTHER
        String whitespace 	= "(\\s+)";
        String error 		= "(.)+";							// must be last and able to capture one character
        String id 			= "([a-zA-Z][0-9a-zA-Z]*)";
        String num 			= "([0-9]+)";
        String operator 	= "(\\+ | \\- | \\/ | \\*)";
        String string 		= "([\"][a-zA-Z0-9])*[\"]"; 		// link 7
        String keyword 		= "(if|then|else|endif|while|do|endwhile|skip|break|return)";
        String endofstmt 	= "(;)";							// maybe ~
        String comment 		="([ugh][\\s][.]+)";
        String lparen 		= "(\\()";
        String rparen 		= "(\\))";
        String lcurly		= "\\{";
        String rcurly 		= "\\}";
        String symbol		= "[^A-Za-z0-9]";
				
        //String regex = symbol;
        String regex = String.join("|", whitespace, error, id, num, operator, string, keyword, endofstmt, comment, lparen, rparen, lcurly, rcurly, symbol);

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