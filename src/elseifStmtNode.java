import java.util.ArrayList;

public class elseifStmtNode implements JottTree {

    public String value;
    public JottTree condition;
    public JottTree bodyNode;

    public elseifStmtNode(Token token){
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.remove(0);
        elseifStmtNode node = null;
        // check for elsif
        if(token.getToken().equals("elseif")){
            node = new elseifStmtNode(token);
            Token next_token = tokens.get(0);
            //check for [
            if (!next_token.getToken().equals("[")) {
                throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a [\n "
                        + next_token.getFilename() + ":" + next_token.getLineNum());
            }
            else{
                tokens.remove(0);
                node.condition = exprNode.parse(tokens);
                //check for ]
                next_token = tokens.get(0);
                if (!next_token.getToken().equals("]")) {
                    throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a ]\n "
                            + next_token.getFilename() + ":" + next_token.getLineNum());
                }
                else{
                    tokens.remove(0);
                    next_token = tokens.get(0);
                    //check for {
                    if (!next_token.getToken().equals("{")) {
                        throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a {\n "
                                + next_token.getFilename() + ":" + next_token.getLineNum());
                    }
                    else{
                        tokens.remove(0);
                        node.bodyNode = bodyStmtNode.parse(tokens);
                        // check for }
                        if (!next_token.getToken().equals("}")) {
                            throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a }\n "
                                    + next_token.getFilename() + ":" + next_token.getLineNum());
                        }
                    }

                }

            }
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