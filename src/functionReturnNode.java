  import java.util.ArrayList;

public class functionReturnNode implements JottTree {
    private JottTree typeReturn;

    public functionReturnNode(){}

    public static JottTree parse(ArrayList<Token> tokens) throws Exception{
        Token token = tokens.get(0);
        if (token.getToken().equals("Void")) {
            tokens.remove(0);
            return new functionReturnNode();
        }
        else if(token.getTokenType() == TokenType.ID_KEYWORD){
            functionReturnNode node = new functionReturnNode();
            node.typeReturn = typeNode.parse(tokens);
            return node;
        }
        else{
            throw new Exception();
        }
    }

    @Override
    public String convertToJott() {
        if(this.typeReturn == null){
            return "Void";
        }
        else{
            return this.typeReturn.convertToJott();
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
