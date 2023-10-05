import java.util.ArrayList;

public class numNode extends exprNode {
    Token token;

    public numNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens){
        return new numNode(tokens.remove(0));
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
