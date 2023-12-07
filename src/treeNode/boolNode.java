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
        if(this.token.getToken().equals("True")) return "true";
        else return "false";
    }

    @Override
    public String convertToC() {
        if(this.token.getToken().equals("True")) return "true";
        else return "false";
    }

    @Override
    public String convertToPython(int depth) {
        return this.token.getToken();
    }

    @Override
    public ReturnType validateTree() {
        return ReturnType.Boolean;
    }
}
