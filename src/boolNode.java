import java.util.ArrayList;

public class boolNode implements JottTree {
    public String value;

    public boolNode(Token token){
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.remove(0);
        boolNode node = null;
        if(token.getToken().equals("True") || token.getToken().equals("False")){
            node = new boolNode(token);
            return node;
        }
        else{
            throw new Exception("Syntax Error\n Token can not be parsed into a Boolean\n"
                        + token.getFilename() + ":" + token.getLineNum());
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
