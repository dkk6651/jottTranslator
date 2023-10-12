import java.util.ArrayList;

public class typeNode implements JottTree {
    Token token;

    public typeNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens) {
        Token token = tokens.get(0);
        typeNode node;
        if(token.getToken().equals("Double") || token.getToken().equals("Integer") ||
                token.getToken().equals("String") || token.getToken().equals("Boolean)")){
            node = new typeNode(tokens.remove(0));
        }
        else{
            System.err.println(); //@Todo Implement syntax error message
            return null;
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
