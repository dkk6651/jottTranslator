/**File Name: idNode.java
 * Description: Responsible for handling anything related
 *
 * @author:
 */

import java.util.ArrayList;

public class idNode extends exprNode{
    Token token;

    public idNode(Token token){
        this.token = token;
    }

    public static exprNode parse(ArrayList<Token> tokens){
        return new idNode(tokens.remove(0));
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
    public ReturnType validateTree() {
        return SymbolTable.scope.get(this.token.getToken());
    }
}
