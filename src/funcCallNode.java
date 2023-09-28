import java.util.ArrayList;

public class funcCallNode implements JottTree {
    private JottTree fcHeader;
    private Token id;
    private JottTree params;
    String value;

    public funcCallNode(Token token) {
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens){
        Token token = tokens.remove(0);
        funcCallNode node = new funcCallNode(token);
        node.fcHeader = fcHeaderNode.parse(tokens);

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
