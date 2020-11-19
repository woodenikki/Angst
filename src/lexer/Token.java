package lexer;

public class Token {

		public int tokenNumber;
		public String data;
		public TokenType type;

		public Token(TokenType type, String data) {
			this.data = data;
			this.type = type;
		}

		public TokenType getType() {
			return type;
		}
		
		public String getData() {
			return data;
		}
	}