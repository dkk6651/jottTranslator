import java.util.ArrayList;

public class asmtNode implements JottTree {
    private JottTree type;
    private exprNode id;
    private exprNode expr;

    public asmtNode(JottTree type, exprNode id, exprNode expr) {
        this.type = type;
        this.id = id;
        this.expr = expr;
    }

    public asmtNode(exprNode id, exprNode expr) {
        this.id = id;
        this.expr = expr;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        JottTree a_type = typeNode.parse(tokens);
        
        return null;
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
