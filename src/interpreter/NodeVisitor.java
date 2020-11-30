package interpreter;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import parser.tree.*;
import interpreter.*;

public class NodeVisitor {

    public Object visit(Node node){
        String methodName = "visit_" + node.getClass().getSimpleName();
        Method visitor = null;

        try{
            visitor = this.getClass().getMethod(methodName, node.getClass());
            return visitor.invoke(this, node);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.err.println(e.getCause().getMessage());
			System.exit(-1);
			//throw new InvalidIdentifierException(e.getCause().getMessage());
		}
		return visitor;
    }
}
