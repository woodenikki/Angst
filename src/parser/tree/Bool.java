package parser.tree;

import lexer.OpType;
import lexer.Token;

public class Bool extends Node{
	private boolean value;
	
	public Bool(Token token) {
		this.type = Datatype.BOOL;
		if(	token.getData().equals("cool") || 
				token.getData().equals("rad") || 
				token.getData().equals("sick")) {
			value = true;
		} else if(token.getData().equals("eh") ||
				token.getData().equals("meh") ||
				token.getData().equals("bleh")) {
			value = false;
		} else {
			System.err.println("Unaccepted boolean value: \"" + token.getData() + "\"");
			System.exit(-1);
		}
	}
	/*
	EQ		("[=][=]"),
	NEQ		("NOT![=][=]"),
	GTEQ	(">="),
	LTEQ	("<="),
	GT		(">"),
	LT		("<"),
	 */
	
	public Bool(Node left, Token op, Node right) {
		if(left.getType() == Datatype.NUMBER) {
			System.out.println("trying to cast from Node to Num..");
			Num l = (Num) left;
			Num r = (Num) right;
			value = numBool(l, op, r);
		}
		else if(left.getType() == Datatype.BOOL) {
			Bool l = (Bool) left;
			Bool r = (Bool) right;
			value = boolBool(l, op, r);
		}
		else if(left.getType() == Datatype.STRINGY) {
			Stringy l = (Stringy) left;
			Stringy r = (Stringy) right;
			value = stringyBool(l, op, r);
		}
		System.err.println("Unexpected token for bool expression: \"" + left.getClass() + "\"");
		System.exit(-1);
	}
	
	public boolean numBool(Num left, Token op, Num right) {				//for ints
		if (op.getData().equals(OpType.EQ.getPattern())) {
			if(left.getValue() == right.getValue()) { return true;}
			else { return false; }
		}
		else if(op.getData().equals(OpType.NEQ.getPattern())) {
			if(left.getValue() != right.getValue()) { return true;}
			else { return false; }
		}
		else if(op.getData().equals(OpType.GTEQ.getPattern())) {
			if(left.getValue() >= right.getValue()) { return true;}
			else { return false; }
		}
		else if(op.getData().equals(OpType.LTEQ.getPattern())) {
			if(left.getValue() <= right.getValue()) { return true;}
			else { return false; }
		}
		else if(op.getData().equals(OpType.GT.getPattern())) {
			if(left.getValue() > right.getValue()) { return true;}
			else { return false; }
		}
		else if(op.getData().equals(OpType.LT.getPattern())) {
			if(left.getValue() < right.getValue()) { return true;}
			else { return false; }
		}
		else {
			System.err.println("Bad news bools comparing number to stringy or bool");
			System.exit(-1);
		}

		return false;
	}
	
	public boolean boolBool(Bool left, Token op, Bool right) {			//for bools
		if (op.getData().equals(OpType.EQ.getPattern())) {
			if(left.getValue() == right.getValue()) { return true;}
			else { return false; }
		}
		else if(op.getData().equals(OpType.NEQ.getPattern())) {
			if(left.getValue() != right.getValue()) { return true;}
			else { return false; }
		}
		else {
			System.err.println("Bad news bools comparing bool to stringy or number");
			System.exit(-1);
		}

		return false;
	}
		
	public boolean stringyBool(Stringy left, Token op, Stringy right) {		//for stringys
		if (op.getData().equals(OpType.EQ.getPattern())) {
			if(left.getValue().equals(right.getValue())) { return true;}
			else { return false; }
		}
		else if(op.getData().equals(OpType.NEQ.getPattern())) {
			if(left.getValue().equals(right.getValue())) { return false;}
			else { return true; }
		}else {
			System.err.println("Bad news bools comparing stringy to bool or number");
			System.exit(-1);
		}

		return false;
	}

	public boolean getValue() {
		return value;
	}

}
