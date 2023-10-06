import java.util.ArrayList;

public class ifStmtNode implements JottTree {
    
    public String value;
    public JottTree bodyNode;
    public JottTree condition;
    public ArrayList<JottTree> elseifLst;
    public JottTree hasElse;

    public ifStmtNode(Token token){
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        ifStmtNode node = null;
        // check for if
        if(token.getToken().equals("if")){
            node = new ifStmtNode(token);
            tokens.remove(0);
            Token next_token = tokens.get(0);
            // check for [
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
                        next_token = tokens.remove(0);
                        if (!next_token.getToken().equals("}")) {
                            throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a }\n "
                                    + next_token.getFilename() + ":" + next_token.getLineNum());
                        }
                        else{
                            while(true){
                                JottTree eachelseif = elseifStmtNode.parse(tokens);
                                if(eachelseif == null){
                                    break;
                                }
                                node.elseifLst.add(eachelseif);
                            }
                            node.hasElse = elseStmtNode.parse(tokens);
                        }
                    }
                }

            }
        }
        return node;
    }

    @Override
    public String convertToJott() {
        String ifString = "if[" + condition.convertToJott() + "]{" + bodyNode.convertToJott() + "}";
        if(!elseifLst.isEmpty()){
            for (JottTree jottTree : elseifLst) {
                ifString.concat(jottTree.convertToJott());
            }
        }
        if(hasElse!=null){
            ifString.concat(hasElse.convertToJava(ifString));
        }
        return ifString;
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
