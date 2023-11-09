/**File Name: numNode.java
 * Description: Responsible for handling all things related to number nodes in Jott grammar.
 *
 * @author: Daniel Kim
 */

import java.util.ArrayList;

public class numNode extends exprNode {
    Token token;
    ReturnType type;

    public numNode(Token token){
        this.token = token;
        if(isDouble(token.getToken())){
            this.type = ReturnType.Double;
        }
        else{
            this.type = ReturnType.Integer;
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
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public ReturnType validateTree() throws Exception {
        if(isDouble(this.token.getToken())){
            return ReturnType.Double;
        }
        if(isInteger(this.token.getToken())){
            return ReturnType.Integer;
        }
        throw new Exception();
    }

    public static boolean isDouble(String str) {
        try {
            double v = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
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
