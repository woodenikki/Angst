package interpreter;

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

        if(node.getData() == OpType.PLUS.getPattern()) {
            return left + right;
        }
        else if(node.getData() == OpType.SUB.getPattern()) {
            return left - right;
        }
        else if(node.getData() == OpType.MULT.getPattern()) {
            return left * right;
        }
        else if(node.getData() == OpType.DIV.getPattern()) {
            return left / right;
        }
        else if(node.getData() == OpType.MOD.getPattern()) {
            return left % right;
        }
        return null;
    }

    public Object visit_UnaryOp(UnaryOp node){
        if (node.getData() == OpType.INCR.getPattern()){
            return (Integer) this.visit(node.getFactor() + 1);
        }
        else if(node.getData() == OpType.DECR.getPattern()){
            return (Integer) this.visit(node.getFactor() - 1);
        }
/*        else if(node.getData() == OpType.NEG.getPattern()){        //negative?
            return -(Integer) this.visit(node.getFactor());
        }
*/
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
            throw new InvalidIdentifierException(varName);
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
        return this.visit(tree);
    }
}