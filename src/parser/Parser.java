package parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lexer.Keyword;
import lexer.Lexer;
import lexer.OpType;
import lexer.Token;
import lexer.TokenType;
import parser.tree.*;
//https://github.com/rgwohlbold/interpreter-ice


/***********UNIMPLEMENTED IDEAS***********/
//-Arrays
//-Return statement -> "give"
public class Parser {
	
	private Lexer lexer;
	private Token currentToken;
	private boolean foundEOP;
	ArrayList<Token> currentStmt;
	
	public Parser(Lexer lexer) {
		foundEOP = false;
		this.lexer = lexer;
		currentToken = lexer.getNextToken();
		currentStmt = new ArrayList<Token>();
	}
	
	public void eat(TokenType type) {
		if(type == currentToken.getType()) {
			//System.out.println(" ate \"" + currentToken.getData() + "\""); 
			if(currentToken.getData().equals(Keyword.ENDPROGRAM.getKey())) {
				return;
			}
			currentStmt.add(currentToken);
			currentToken = lexer.getNextToken();
			
			if(currentToken == null) {
				System.err.println("Ran out of tokens. Did you end your program correctly?");
				System.exit(-1);
			}
			
		}else {
			System.err.println("Unexpected token: \"" + currentToken.getData() + "\"");
			System.exit(-1);
		}
	}
	

	
	public Node term() {										//NUMBER, STRINGY, BOOL

		Token token = currentToken;
		
		if(currentToken.getType() == TokenType.NUMBER) {		// number c:
			this.eat(TokenType.NUMBER);
			return new Num(token);
		}
		else if(currentToken.getType() == TokenType.STRINGY) {	// stringy
			this.eat(TokenType.STRINGY);
			return new Stringy(token);
		}
		else if(currentToken.getType() == TokenType.BOOL) {		// bool
			System.out.println();
			this.eat(TokenType.BOOL);
			return new Bool(token);
		}
		else if(currentToken.getType() == TokenType.ID) {
			return variable();
		}
		else if(currentToken.getType() == TokenType.LPAREN) { 	// ( expression )
			this.eat(TokenType.LPAREN);
			Node result = expression();
			this.eat(TokenType.RPAREN);
			return result;
		}else if(currentToken.getType() == TokenType.KEYWORD) {
			if(currentToken.getData().equals(Keyword.RANDOM.getKey())) {
				this.eat(TokenType.KEYWORD);
				this.eat(TokenType.LPAREN);
				int r = Integer.parseInt(currentToken.getData());
				this.eat(TokenType.NUMBER);
				this.eat(TokenType.RPAREN);
				int rand = (int) (r * Math.random());
				
				return new Num(rand);
			}
		}
		
		System.err.println("bad things man. You've found yourself an infinite loop.");
		System.exit(-1);

		return new NoOp();
	}
	
	public Var variable() {

		Var node = new Var(currentToken);
		this.eat(TokenType.ID);
		
		return node;
	}
	
	public Node post() {

		Node result = term();

		while(currentToken.getData().equals(OpType.INCR.getOperation()) || currentToken.getData().equals(OpType.DECR.getOperation())) {
			Token op = currentToken;
																				//Precedence:
			if(op.getData().equals(OpType.INCR.getOperation()) || 		//x++
					op.getData().equals(OpType.DECR.getOperation())){		//x--
				this.eat(TokenType.OP);
			}
			result = new ArithOp(result, op);
			System.out.println("used post");
		}
		
		return result;
	}
	
	public Node multDivMod() {

		Node result = post();
		
		while(currentToken.getType() == TokenType.OP && (currentToken.getData().equals(OpType.MULT.getOperation()) || // MULT, DIV, OR MOD
				currentToken.getData().equals(OpType.DIV.getOperation()) ||currentToken.getData().equals(OpType.MOD.getOperation()))) {
			
			Token op = currentToken;
			
			if(op.getData().equals(OpType.MULT.getOperation()) || 		//mult
					op.getData().equals(OpType.DIV.getOperation()) ||	//div
					op.getData().equals(OpType.MOD.getOperation())) {	//mod
				this.eat(TokenType.OP);
				
			}
			result = new ArithOp(result, op, term());
		}
		
		return result;
	}
	
	private boolean tokenTypeIn(TokenType... types) {
		if (currentToken == null) {
			return false;
		}
		
		for (int i = 0; i < types.length; i++) {
			if (currentToken.getType() == types[i]) {
				return true;
			}
		}
		return false;
	}
	
//	public Node empty() {
//		return new NoOp();
//	}
	
	public Node booleanExpression() {
		Node result = this.term();
		Token token = currentToken;
		while(currentToken.getData().equals(OpType.EQ.getOperation()) || currentToken.getData().equals(OpType.NEQ.getOperation()) || 
				currentToken.getData().equals(OpType.GTEQ.getOperation()) || currentToken.getData().equals(OpType.LTEQ.getOperation()) || 
						currentToken.getData().equals(OpType.GT.getOperation()) || currentToken.getData().equals(OpType.LT.getOperation())) {
			
			Token op = currentToken;
			this.eat(TokenType.OP);
			Node right = this.booleanExpression();
			result =  new Bool(result, op, right);
		}
		return result;
	}
	
	/************METHODS************/
	public Node printStmt() {	//mumble("hello")
		this.eat(TokenType.KEYWORD);
		this.eat(TokenType.LPAREN);

		if(currentToken.getType().equals(TokenType.STRINGY)) {
			this.eat(TokenType.STRINGY);
		}
		else if(currentToken.getType().equals(TokenType.NUMBER)) {
			this.eat(TokenType.NUMBER);
		}
		else if(currentToken.getType().equals(TokenType.BOOL)) {
			this.eat(TokenType.BOOL);
		}
		
		this.eat(TokenType.RPAREN);
		
		return new NoOp();
	}
	
	
	@SuppressWarnings("finally")
	public Node throwExceptionStmt() { //throws shade("ya done goofed")

		this.eat(TokenType.KEYWORD); 
		this.eat(TokenType.LPAREN);
		String exception = currentToken.getData();
		this.eat(TokenType.STRINGY);
		this.eat(TokenType.RPAREN);
		try {
			
		}
		catch(Exception e) {
			throw new AngstyException(exception);
		}
		finally {
			return new NoOp();
		}
		
	}

	/*********POSSIBLE STATEMENTS*********/
	public Node expression() { //any Arithmetic statement... numbers only

		Node result = multDivMod();
		//System.out.println(currentToken.getData() +" "+currentToken.getType());

		while (currentToken.getType() == TokenType.OP && 
				(currentToken.getData().equals(OpType.PLUS.getOperation()) || currentToken.getData().equals(OpType.MINUS.getOperation()))) {
			Token op = currentToken;

			if(op.getData().equals(OpType.PLUS.getOperation()) || 		//add
					op.getData().equals(OpType.MINUS.getOperation())) {	//sub
				
				this.eat(TokenType.OP);
			}
			result = new ArithOp(result, op, term());		//result -> result (+ term)
		}

		return result;		
	}
	
	public Node declarationStmt() { //my stringy x should be "hello"

		boolean scopeIsPrivate = false;
		Datatype datatype = null;
		
		if(currentToken.getData().equals(Keyword.PRIVATE.getKey())) {
			scopeIsPrivate = true;
			this.eat(TokenType.KEYWORD);
		}else if(currentToken.getData().equals(Keyword.PUBLIC.getKey())) {
			scopeIsPrivate = false;
			this.eat(TokenType.KEYWORD);
		}else {
			System.err.println("Expecting scope: my or anybodies");
			System.exit(-1);
		}
		if(currentToken.getType().equals(TokenType.KEYWORD)) {
			if(currentToken.getData().equals(Keyword.NUMBER.getKey())){
				datatype = Datatype.NUMBER;
				this.eat(TokenType.KEYWORD);
			}
			else if(currentToken.getData().equals(Keyword.BOOL.getKey())) {
				datatype = Datatype.BOOL;
				this.eat(TokenType.KEYWORD);
			}
			else if(currentToken.getData().equals(Keyword.STRINGY.getKey())) {
				datatype = Datatype.STRINGY;
				this.eat(TokenType.KEYWORD);
			}else {
				System.err.println("Expecting datatype"); //TODO: add line number to errors?
				System.exit(-1);
			}
		}

		Var var = this.variable();	//x
		Token token = currentToken;	//=
		this.eat(TokenType.ASSIGN); 
		
		Node right = this.expression();
		
		var.setPrivate(scopeIsPrivate);
		var.setVarType(datatype);
		
		return new Assign(var, token, right);

	}
	
	public Node assignmentStmt() { // y is 7

		Var left = this.variable();
		Token token = currentToken;
		
		if(token.getType()==TokenType.ASSIGN) {
			this.eat(TokenType.ASSIGN);
		}
		else {
			//System.out.println("\t\tpost during assignment");
			this.eat(TokenType.OP);
			return new Assign(left, token, new ArithOp(left, token));
		}
			
		Node right = this.expression();
		
		//left.setVarType(right.getType());
		
		return new Assign(left, token, right);
	}
	
	public Node ifStmt() {
		int counter = 1;
		this.eat(TokenType.KEYWORD); //if
		this.eat(TokenType.LPAREN);
		Node cond = booleanExpression();
		this.eat(TokenType.RPAREN);
		this.eat(TokenType.LCURLY);
		Node stmt = null;
		while(currentToken.getType() != TokenType.RCURLY) {							//statement in origional if
			stmt = this.statement(); 					
			if(currentToken.getData().equals(Keyword.ENDLOOP.getKey())){
				this.eat(TokenType.KEYWORD);
			}else { this.eat(TokenType.ENDSTMT); }
			stmt = new IfStatement(cond, stmt, counter);
		}
		this.eat(TokenType.RCURLY);

		if(stmt == null) {
			System.err.println("Bad if statement");
			System.exit(-1);
		}
		Node other = null;		
		
		while(currentToken.getData().equals(Keyword.ELSEIF.getKey())){						//can have any number of else ifs, unfortunately.
			counter++;
			this.eat(TokenType.KEYWORD); //elseif
			this.eat(TokenType.LPAREN);
			Node elsifCond = booleanExpression();
			this.eat(TokenType.RPAREN);
			this.eat(TokenType.LCURLY);
			
			while(currentToken.getType() != TokenType.RCURLY) {
					
				stmt = this.statement(); 											//statement in elsif
				if(currentToken.getData().equals(Keyword.ENDLOOP.getKey())){
					this.eat(TokenType.KEYWORD);
				}else { this.eat(TokenType.ENDSTMT); }
				stmt = new IfStatement(elsifCond, stmt, counter);
			}
			this.eat(TokenType.RCURLY);
		}
		
		if(currentToken.getData().equals(Keyword.ELSE.getKey())) {
			counter++;
			this.eat(TokenType.KEYWORD); //else
			this.eat(TokenType.LCURLY);
			
			while(currentToken.getType() != TokenType.RCURLY) {
				
				stmt = this.statement(); 									//statement in elsif
				if(currentToken.getData().equals(Keyword.ENDLOOP.getKey())){
					this.eat(TokenType.KEYWORD);
				}else { this.eat(TokenType.ENDSTMT); }
				stmt = new IfStatement(new NoOp(), stmt, counter);
			}
			this.eat(TokenType.RCURLY);
		}
		
		return stmt;
	}
	
	public Node dowhileStmt(){
		return null;
	}
	
	public void printCurrentStatement() {

		for(int i = 0; i < currentStmt.size(); i++) {
			System.out.print(currentStmt.get(i).getData()+" ");
		}
		currentStmt.clear();
		System.out.println();
	}
	
	public Node statement() {
		Node stmt = null;

		if(currentToken.getType() == TokenType.KEYWORD) {
			
			
			if(currentToken.getData().equals(Keyword.PRINT.getKey())) { //mumble(x)
				stmt = this.printStmt();
				System.out.print("Print Stmt: \t");
			}
			else if(currentToken.getData().equals(Keyword.THROWEXCEPTION.getKey())) {
				stmt = this.throwExceptionStmt();
				System.out.print("Exception: \t");
			}
			else if(currentToken.getData().equals(Keyword.PUBLIC.getKey()) || currentToken.getData().equals(Keyword.PRIVATE.getKey())) {
				stmt = this.declarationStmt();
				System.out.print("Declaration: \t");
			}
			else if(currentToken.getData().equals(Keyword.IF.getKey())) {
				stmt = this.ifStmt();
				
				System.out.print("If statement: \t");
			}
			else if(currentToken.getData().equals(Keyword.DOWHILE.getKey())) {
				stmt = this.dowhileStmt();
				System.out.print("While Loop: \t");
			}
			
			/*
			else if(currentToken.getData().equals(Keyword.WHILE)) {
				stmt = this.whileStmt();
			}
			 */
		}

		else if(currentToken.getType() == TokenType.ID) {
			try {
				stmt = this.assignmentStmt();
				System.out.print("Assignment: \t");
			}catch(Exception e) {
				System.out.println("exception... running expression?");
				stmt = expression();
				System.out.print("Expression: \t");
			}

		}
		return stmt;
	}
	
	public Node statementList(){
		Compound rootNode = new Compound();
		while (tokenTypeIn(TokenType.KEYWORD, TokenType.ID, TokenType.BOOL, TokenType.NUMBER, TokenType.STRINGY, TokenType.LPAREN) && !foundEOP) { //not sure what else..
			Node node = this.statement();
			
			if(currentToken.getData().equals(Keyword.ENDLOOP.getKey())){
				this.eat(TokenType.KEYWORD);
			}else { this.eat(TokenType.ENDSTMT); }
			
			printCurrentStatement();
			rootNode.getChildren().add(node);
			
			if(currentToken.getType() == TokenType.KEYWORD && currentToken.getData().equals(Keyword.ENDPROGRAM.getKey())) {
				this.eat(TokenType.KEYWORD);
				foundEOP = true;
				System.out.print("End Program: \tthats deep.");
				System.out.println();
			}

		}
		return rootNode;
	}
	
	public Node program() {
		if(!currentToken.getData().equals(Keyword.STARTPROGRAM.getKey())) {
			System.err.println("Sorry, I wasn't listening. Expecting 'hear me out..'"); //TODO: add line number to errors?
			System.exit(-1);
		}
		this.eat(TokenType.KEYWORD);
		System.out.print("Begin Program: \t");
		printCurrentStatement();
		//System.out.println("p: I'm listening.");
		Node result = statementList();
		
		return result;
	}
	
	public Node parse() {
		Node root = this.program();
		System.out.println("Parsed successfully!");
		return root;
	}
}
