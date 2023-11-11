package treeNode; /**File Name: treeNode.typeNode.java
 * Description: Responsible for handling everything related to type node in Jott grammar.
 *
 * @author: Daniel Kim
 */

import provided.JottTree;
import provided.ReturnType;
import provided.Token;

import java.util.ArrayList;

public class typeNode implements JottTree {
    Token token;

    public typeNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception{
        Token token = tokens.get(0);
        typeNode node;
        if(token.getToken().equals("Double") || token.getToken().equals("Integer") ||
                token.getToken().equals("String") || token.getToken().equals("Boolean)")){
            node = new typeNode(tokens.remove(0));
        }
        else{
            throw new Exception(String.format("Syntax Error\nMissing token type\n%s:%d", token.getFilename(), token.getLineNum()));
        }
        return node;
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
        switch (token.getToken()) {
            case "Double" -> {
                return ReturnType.Double;
            }
            case "Integer" -> {
                return ReturnType.Integer;
            }
            case "Boolean" -> {
                return ReturnType.Boolean;
            }
            case "String" -> {
                return ReturnType.String;
            }
        }
        return null;
    }
}
