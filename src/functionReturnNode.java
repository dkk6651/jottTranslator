  import java.util.ArrayList;

public class functionReturnNode implements JottTree {
    private String value;
    private JottTree typeReturn;

    public functionReturnNode(Token token){
        this.value = token.getToken();
    }

    public functionReturnNode(){}

    public static JottTree parse(ArrayList<Token> tokens) {
        Token token = tokens.get(0);
        if (token.getToken().equals("Void")) {
            token = tokens.remove(0);
            return new functionReturnNode(token);
        }
        else{
            JottTree type = typeNode.parse(tokens);
            functionReturnNode node = new functionReturnNode();
            node.typeReturn = type;
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
