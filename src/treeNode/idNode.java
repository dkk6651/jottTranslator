package treeNode; /**File Name: treeNode.idNode.java
 * Description: Responsible for handling anything related
 *
 * @author:
 */

import provided.ReturnType;
import provided.Token;

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
    public ReturnType validateTree() {
        return SymbolTable.scope.get(this.token.getToken());
    }
}
