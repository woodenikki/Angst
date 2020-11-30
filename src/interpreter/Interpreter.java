package interpreter;

import java.util.HashMap;

import lexer.*;
import parser.*;
import parser.tree.*;


public class Interpreter extends NodeVisitor {
    private Parser parser;
    public HashMap<String, Object> variables;

    public Interpreter(Parser parser){
        this.parser = parser;
        this.variables = new HashMap<String, Object>();
    }

    public Object visit_BinOp(BinOp node){
        Integer left = (Integer) this.visit(node.getRight());
        Integer right = (Integer) this.visit(node.getLeft());

        if(node.getOp().getData().equals(OpType.PLUS.getPattern())) {
            return left + right;
        }
        else if(node.getOp().getData().equals(OpType.MINUS.getPattern())) {
            return left - right;
        }
        else if(node.getOp().getData().equals(OpType.MINUS.getPattern())) {
            return left * right;
        }
        else if(node.getOp().getData().equals(OpType.DIV.getPattern())) {
            return left / right;
        }
        else if(node.getOp().getData().equals(OpType.MOD.getPattern())) {
            return left % right;
        }
        return null;
    }

    public Object visit_UnaryOp(UnaryOp node){
        if (node.getOp().getData().equals(OpType.INCR.getPattern())){
            return (Integer) this.visit(node.getFactor()) + 1;
        }
        else if(node.getOp().getData().equals(OpType.DECR.getPattern())){
            return (Integer) this.visit(node.getFactor()) - 1;
        }
        else if(node.getOp().getData() == OpType.MINUS.getPattern()){        //negative?
            return -(Integer) this.visit(node.getFactor());
        }
        

        return null;
    }

    public Object visit_Compound(Compound node) {
        for (Node child : node.getChildren()) {
            this.visit(child);
        }
        return null;
    }

    public Object visit_NoOp(NoOp node){
        return null;
    }

    public Object visit_Var(Var node) {
        String varName = node.getName();
        Object value = variables.get(varName);
        if(value == null){
            throw new InvalidIDException(varName);
        }
        return value;
    }

    public Object visit_Assign(Assign node) {
        String varName = ((Var)node.getLeft()).getName();
        Object result = this.visit(node.getRight());
        if (this.variables.containsKey(varName)) {
            this.variables.remove(varName);
        }
        this.variables.put(varName, result);
        return null;
    }

    public Integer visit_Num(Num node){
        return node.getValue();
    }

    public Object interpret() {
        Node tree = parser.parse();
        if(tree==null) { System.out.println("bad lemons"); }
        return this.visit(tree);
    }
}