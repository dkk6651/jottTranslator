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
            throw new Exception();
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
    public boolean validateTree() {
        return false;
    }
}
