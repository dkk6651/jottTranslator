import java.util.ArrayList;

public class boolNode implements JottTree {
    public String value;

    public boolNode(Token token){
        this.value = token.getToken();
    }

    public JottTree parse(ArrayList<Token> tokens) {
        Token token = tokens.remove(0);
        boolNode node = null;
        if(token.getToken().equals("True") || token.getToken().equals("False")){
            node = new boolNode(token);
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
