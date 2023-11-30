package treeNode;

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class opNode implements JottTree {
    Token token;

    public opNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception{
        Token token = tokens.get(0);
        opNode node;
        if(token.getTokenType() == TokenType.MATH_OP || token.getTokenType() == TokenType.REL_OP){
            node = new opNode(tokens.remove(0));
        }
        else{
            throw new Exception(String.format("Syntax Error\nMissing math operator\n%s:%d", token.getFilename(), token.getLineNum()));
        }
        return node;
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
        if(token.getTokenType() == TokenType.MATH_OP){
            return ReturnType.MathOP;
        }
        else{
            return ReturnType.RelOP;
        }
    }
}
