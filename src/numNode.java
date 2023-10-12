/**File Name: numNode.java
 * Description: Responsible for handling all things related to number nodes in Jott grammar.
 *
 * @author: Daniel Kim
 */

import java.util.ArrayList;

public class numNode extends exprNode {
    Token token;

    public numNode(Token token){
        this.token = token;
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
    public boolean validateTree() {
        return false;
    }
}
