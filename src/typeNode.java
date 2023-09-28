import java.util.ArrayList;

public class typeNode implements JottTree {
    private String value;
    private int lineNumber;
    private String filename;
    private TokenType type;

    public typeNode(Token token){
        this.value = token.getToken();
        this.lineNumber = token.getLineNum();
        this.filename = token.getFilename();
        this.type = token.getTokenType();
    }

    public static JottTree Parse(ArrayList<Token> tokens) {
        Token token = tokens.remove(0);
        typeNode node = null;
        if(token.getToken().equals("Double") || token.getToken().equals("Integer") ||
                token.getToken().equals("String") || token.getToken().equals("Boolean)")){
            node = new typeNode(token);
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
