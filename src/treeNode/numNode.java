package treeNode; /**File Name: treeNode.numNode.java
 * Description: Responsible for handling all things related to number nodes in Jott grammar.
 *
 * @author: Daniel Kim
 */

import provided.JottTree;
import provided.ReturnType;
import provided.Token;

import java.util.ArrayList;

public class numNode extends exprNode {
    Token token;
    ReturnType type;

    public numNode(Token token){
        this.token = token;
        if(isInteger(token.getToken())){
            this.type = ReturnType.Integer;
        }
        else{
            this.type = ReturnType.Double;
        }
    }

    public static JottTree parse(ArrayList<Token> tokens){
        return new numNode(tokens.remove(0));
    }

    @Override
    public String convertToJott() {
        return this.token.getToken();
    }

    @Override
    public String convertToJava(String className) {
        return this.token.getToken();
    }

    @Override
    public String convertToC() {
        return this.token.getToken();
    }

    @Override
    public String convertToPython(int depth) {
        return this.token.getToken();
    }

    @Override
    public ReturnType validateTree() throws Exception {
        if(isInteger(this.token.getToken())){
            return ReturnType.Integer;
        }
        if(isDouble(this.token.getToken())){
            return ReturnType.Double;
        }
        throw new Exception();
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
