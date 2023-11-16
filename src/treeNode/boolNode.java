package treeNode;

import provided.JottTree;
import provided.ReturnType;
import provided.Token;

import java.util.ArrayList;

public class boolNode extends exprNode {
    Token token;

    public boolNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens) {
        return new boolNode(tokens.remove(0));
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
        return ReturnType.Boolean;
    }
}
