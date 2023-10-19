import java.util.ArrayList;

public class bodyNode implements JottTree {
    private ArrayList<JottTree> body_stmt;
    private JottTree return_stmt;

    public bodyNode(ArrayList<JottTree> body_stmt, JottTree return_stmt) {
        this.body_stmt = body_stmt;
        this.return_stmt = return_stmt;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        ArrayList<JottTree> bodyList = new ArrayList<>();

        while(!tokens.get(0).getToken().equals("return")){
            bodyList.add(bodyStmtNode.parse(tokens));
            if(tokens.get(0).getTokenType() == TokenType.R_BRACE) return new bodyNode(bodyList, null);
        }

        // parse for return statement
        JottTree b_return_stmt = returnStmtNode.parse(tokens);

        return new bodyNode(bodyList, b_return_stmt);
    }

    @Override
    public String convertToJott() {
        StringBuilder string = new StringBuilder();
        for (JottTree jottTree : this.body_stmt) {
            string.append(jottTree.convertToJott());
            if(jottTree.getClass() == funcCallNode.class){
                string.append(";");
            }
        }
        if(this.return_stmt != null){
            string.append(this.return_stmt.convertToJott());
        }
        return string.toString();
    }

    @Override
    public String convertToJava(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJava'");
    }

    @Override
    public String convertToC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToC'");
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }
    
}
