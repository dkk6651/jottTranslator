import java.util.ArrayList;

public abstract class exprNode implements JottTree {
    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        JottTree node;
        if(token.getTokenType() == TokenType.NUMBER){
            node = numNode.parse(tokens);
        }
        else if(token.getToken().equals("True") || token.getToken().equals("False")){
            node = boolNode.parse(tokens);
            return node;
        }
        else if(token.getTokenType() == TokenType.ID_KEYWORD){
            node = idNode.parse(tokens);
        }
        else if(token.getTokenType() == TokenType.STRING){
            node = stringNode.parse(tokens);
            return node;
        }
        else if(token.getTokenType() == TokenType.FC_HEADER){
            node = funcCallNode.parse(tokens);
        }
        else{
            System.err.println(); //@Todo implement syntax error message
            return null;
        }

        if(tokens.get(0).getTokenType() == TokenType.MATH_OP || tokens.get(0).getTokenType() == TokenType.REL_OP){
            return new binaryExprNode(node, opNode.parse(tokens), exprNode.parse(tokens));
        }
        else{
            return node;
        }
    }

    @Override
    public String convertToJott() {
        return null;
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
