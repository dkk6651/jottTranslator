import java.util.ArrayList;

public class opNode implements JottTree {
    String value;

    public opNode(Token token){
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens){
        Token token = tokens.remove(0);
        opNode node = null;
        if(token.getTokenType() == TokenType.MATH_OP || token.getTokenType() == TokenType.REL_OP){
            node = new opNode(token);
        }
        else{
            System.err.println(); //@Todo Implement syntax error message
            return null;
        }
        return node;
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
