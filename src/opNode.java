import java.util.ArrayList;

public class opNode implements JottTree {
    private String value;
    private int lineNumber;
    private String filename;
    private TokenType type;

    public opNode(Token token){
        this.value = token.getToken();
        this.lineNumber = token.getLineNum();
        this.filename = token.getFilename();
        this.type = token.getTokenType();
    }

    @Override
    public JottTree Parse(ArrayList<Token> tokens){
        Token token = tokens.remove(0);
        opNode node = null;
        if(token.getToken().equals("+") ||
                token.getToken().equals("*") || token.getToken().equals("/") || token.getToken().equals("-") ||
                token.getToken().equals(">") || token.getToken().equals(">=") || token.getToken().equals("<") ||
                token.getToken().equals("<=") || token.getToken().equals("==") || token.getToken().equals("!=")){
            node = new opNode(token);
        }
        else{
            System.err.println(); //@Todo Implement syntax error message
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
