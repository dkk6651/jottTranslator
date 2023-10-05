import java.util.ArrayList;

public class paramsTNode implements JottTree {
    public JottTree expression;
    @Override
    public String convertToJott() {
        return null;
    }

    public paramsTNode(){ }

    public static JottTree parse(ArrayList<Token> tokens){
        Token token = tokens.get(0);
        paramsTNode tNode = new paramsTNode();
        if(token.getTokenType()==TokenType.COMMA){
            tokens.remove(0);
            tNode.expression = exprNode.parse(tokens);
            return tNode;
        } else {
            System.err.println(); //@Todo implement syntax error message
            return null;
        }
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
