import java.util.ArrayList;

public class elseStmtNode implements JottTree {

    public String value;
    public JottTree bodyNode;

    public elseStmtNode(Token token){
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.remove(0);
        elseStmtNode node = null;
        if(token.getToken().equals("else")){
            node = new elseStmtNode(token);
            Token next_token = tokens.get(0);
            if (!token.getToken().equals("{")) {
                throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a {\n "
                        + next_token.getFilename() + ":" + next_token.getLineNum());
            }
            else{
                tokens.remove(0);
                node.bodyNode = bodyStmtNode.parse(tokens);
            }
            return node;
        }
        else{
            throw new Exception("Syntax Error\n Token can not be parsed into an Else\n"
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
