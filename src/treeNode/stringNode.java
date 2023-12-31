package treeNode;

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class stringNode extends exprNode{
    Token token;

    public stringNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        if(tokens.get(0).getTokenType() != TokenType.STRING){
            System.err.println();
            throw new Exception();
        }
        return new stringNode(tokens.remove(0));
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
        return ReturnType.String;
    }
}
