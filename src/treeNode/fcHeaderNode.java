package treeNode; /**File Name: treeNode.fcHeaderNode.java
 * Description: Responsible for anything related to function header.
 *
 * @author: Daniel Kim
 */

import provided.JottTree;
import provided.ReturnType;
import provided.Token;

import java.util.ArrayList;

public class fcHeaderNode implements JottTree {
    Token token;

    public fcHeaderNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens){
        return new fcHeaderNode(tokens.remove(0));
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
        return null;
    }
}
