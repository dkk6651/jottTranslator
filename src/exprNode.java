import java.util.ArrayList;

public class exprNode implements JottTree {
    JottTree id;
    JottTree bool;
    JottTree funcCall;
    JottTree op;
    JottTree expr;
    String value;

    public exprNode(Token token){
        this.value = token.getToken();
    }

    public exprNode(){}

    public static JottTree parse(ArrayList<Token> tokens){
        Token token = tokens.get(0);
        exprNode node = new exprNode();
        if(token.getTokenType() == TokenType.NUMBER){
            token = tokens.remove(0);
            node.value = token.getToken();
        }
        else if(token.getToken().equals("True") || token.getToken().equals("False")){
            node.bool = boolNode.parse(tokens);
            return node;
        }
        else if(token.getTokenType() == TokenType.ID_KEYWORD){
            node.id = idNode.parse(tokens);
        }
        else if(token.getTokenType() == TokenType.STRING){
            token = tokens.remove(0);
            node.value = token.getToken();
            return node;
        }
        else if(token.getTokenType() == TokenType.FC_HEADER){
            node.funcCall = funcCallNode.parse(tokens);
        }
        else{
            System.err.println(); //@Todo implement syntax error message
            return null;
        }

        if(tokens.get(0).getTokenType() == TokenType.MATH_OP || tokens.get(0).getTokenType() == TokenType.REL_OP){
            node.op = opNode.parse(tokens);
            node.expr = exprNode.parse(tokens);
            return node;
        }
        else if(tokens.get(0).getTokenType() == TokenType.SEMICOLON || tokens.get(0).getTokenType() == TokenType.R_BRACKET || tokens.get(0).getToken().equals(",")){
            return node;
        }
        else{
            System.err.println(); //@Todo implement syntax error message
            return null;
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
