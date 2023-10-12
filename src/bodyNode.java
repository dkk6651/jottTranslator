import java.util.ArrayList;

public class bodyNode implements JottTree {
    private JottTree body_stmt;
    private JottTree return_stmt;

    public bodyNode(JottTree body_stmt, JottTree return_stmt) {
        this.body_stmt = body_stmt;
        this.return_stmt = return_stmt;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        boolean foundEnd = false;

        while (!foundEnd) {
            
        }

        // parse for return statement
        JottTree b_return_stmt = bodyStmtNode.parse(tokens);

        return null;
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJott'");
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
