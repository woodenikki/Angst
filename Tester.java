import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {

    public static class Token
    {
        public int tokenNumber;
        public String tokenValue;

        public Token(int tokenNumber, String tokenValue)
        {
            this.tokenNumber = tokenNumber;
            this.tokenValue = tokenValue;
        }
    }

    public static int WHITESPACE = 1; // group 1
    public static int PUNCTUATION = 2; // group 2 etc.
    public static int LPAREN = 3;
    public static int RPAREN = 4;
    public static int KEYWORD = 5;
    public static int IDENTIFIER = 6;
    public static int NUMBER = 7;
    public static int SEMICOLON = 8;
    public static int ERROR = 9;
    public static int EOF = 10;

    Matcher m;
    String text;
    boolean skipError;


    public static void main(String[] args) {
        Tester Tester = new Tester("tcu else i34 !!!! 2983 ( + +eqdQ!!!!"); // With some error characters "!" thrown in the middle and at the end
        for(;;) {
            Token token = Tester.next();
            System.out.println(token.tokenNumber + ": " + token.tokenValue);
            if (token.tokenNumber == EOF)
                break;
        }
    }

    public Tester(String text)
    {

        String _WHITESPACE = "(\\s+)";
        String _PUNCTUATION = "((?:[+*/-]|:=))";
        String _LPAREN = "(\\()";
        String _RPAREN = "(\\))";
        String _KEYWORD = "(if|then|else|endif|while|do|endwhile|skip)";
        String _IDENTIFIER = "([a-zA-Z][0-9a-zA-Z]*)";
        String _NUMBER = "([0-9)]+)";
        String _SEMICOLON = "(;)";
        String _ERROR = "(.)"; // must be last and able to capture one character

        String regex = String.join("|", _WHITESPACE, _PUNCTUATION, _LPAREN, _RPAREN, _KEYWORD, _IDENTIFIER, _NUMBER, _SEMICOLON, _ERROR);

        Pattern p = Pattern.compile(regex);
        this.text = text;
        m = p.matcher(this.text);
        skipError = false;
    }

    public Token next()
    {
        Token token = null;
        for(;;) {
            if (!m.find())
                return new Token(EOF, "<EOF>");
            for (int tokenNumber = 1; tokenNumber <= 9; tokenNumber++) {
                String tokenValue = m.group(tokenNumber);
                if (tokenValue != null) {
                    token = new Token(tokenNumber, tokenValue);
                    break;
                }
            }
            if (token.tokenNumber == ERROR) {
                if (!skipError) {
                    skipError = true; // we don't want successive errors
                    return token;
                }
            }
            else {
                skipError = false;
                if (token.tokenNumber != WHITESPACE)
                    return token;
            }
        }
    }

}