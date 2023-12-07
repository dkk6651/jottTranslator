package treeNode; /**
 * @author Jonas Long 
*/

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class paramsTNode implements JottTree {
    public JottTree expression;
    @Override
    public String convertToJott() {
        return String.format(", %s", expression.convertToJott());
    }

    public paramsTNode(){ }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        paramsTNode tNode = new paramsTNode();
        if(token.getTokenType()== TokenType.COMMA){
            tokens.remove(0);
            tNode.expression = exprNode.parse(tokens);
            return tNode;
        } else {
            throw new Exception(String.format("Syntax Error\nprovided.Token can not be parsed into a ParamsTNode\n%s:%d", token.getFilename(), token.getLineNum()));
        }
    }

    @Override
    public String convertToJava(String className) {
        return expression.convertToJava(className);
    }

    @Override
    public String convertToC() {
        return expression.convertToC();
    }

    @Override
    public String convertToPython(int depth) {
        return expression.convertToPython(depth);
    }

    @Override
    public ReturnType validateTree() throws Exception {
        return expression.validateTree();
    }
}
