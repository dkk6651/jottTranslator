import java.util.ArrayList;

public class fcHeaderNode implements JottTree {
    public String value;

    public fcHeaderNode(Token token){
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens){
        Token token = tokens.remove(0);
        fcHeaderNode node = new fcHeaderNode(token);
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
