/**
Author: JD Rears jar6256
 */

public class funcDefParamsTNode implements JottTree {

    public static JottTree parse(ArrayList<token> tokens throws Exception){
        Token token = tokens.get(0);
        if token.getToken.equals(']'){
            throw new Exception("Syntax Error") ; // TODO add msg 
        }
        tokens.remove(0);
        return typeNode.parse(tokens)

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
