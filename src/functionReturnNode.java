import java.util.ArrayList;

public class functionReturnNode implements JottTree {
    private String value;
    private int lineNumber;
    private String filename;
    private TokenType type;
    private JottTree typeReturn = null;

    public functionReturnNode(ArrayList<Token> tokens){
        parse(tokens);
    }

    public static JottTree parse(ArrayList<Token> tokens) {
        Token token = tokens.get(0);
        functionReturnNode node = null;
        if (token.getToken().equals("Void")) {
            token = tokens.remove(0);
            node = new functionReturnNode(token);
            return node;
        }
        else if(token.getToken().equals("Double") || token.getToken().equals("Integer") || token.getToken().equals("String") || token.getToken().equals("Boolean")){
            node.typeReturn = typeNode.Parse(tokens);
        }

        return null;
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
