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
