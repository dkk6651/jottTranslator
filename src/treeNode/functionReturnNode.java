package treeNode;

import provided.JottTree;
  import provided.ReturnType;
  import provided.Token;
  import provided.TokenType;

  import java.util.ArrayList;

public class functionReturnNode implements JottTree {
    private JottTree typeReturn;

    public functionReturnNode(){}

    public static JottTree parse(ArrayList<Token> tokens) throws Exception{
        Token token = tokens.get(0);
        if (token.getToken().equals("Void")) {
            tokens.remove(0);
            return new functionReturnNode();
        }
        else if(token.getTokenType() == TokenType.ID_KEYWORD){
            functionReturnNode node = new functionReturnNode();
            node.typeReturn = typeNode.parse(tokens);
            return node;
        }
        else{
            throw new Exception(String.format("Syntax Error\nprovided.Token missing return statement\n%s:%d", token.getFilename(), token.getLineNum()));
        }
    }

    @Override
    public String convertToJott() {
        if(this.typeReturn == null){
            return "Void";
        }
        else{
            return this.typeReturn.convertToJott();
        }
    }

    @Override
    public String convertToJava(String className) {
        if(this.typeReturn == null) return "void";
        else return this.typeReturn.convertToJava(className);
    }

    @Override
    public String convertToC() {
        if(this.typeReturn == null) return "void";
        else return this.typeReturn.convertToC();
    }

    @Override
    public String convertToPython(int depth) {
        return null;
    }

    @Override
    public ReturnType validateTree() throws Exception {
        if(typeReturn == null){
            return ReturnType.Void;
        }
        return typeReturn.validateTree();
    }
}
