import java.util.ArrayList;

public class returnStmtNode implements JottTree {
    private JottTree expr = null;

    public returnStmtNode(JottTree expr) {
        this.expr = expr;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);

        if (token == null) {
            return null;
        }

        // check for "return"
        if (!token.getToken().equals("return")) {
            throw new Exception(String.format("Syntax Error\nMissing return statement\n%s:%d", token.getFilename(), token.getLineNum()));
        }
        tokens.remove(0);

        // parse for expression
        JottTree r_expr = exprNode.parse(tokens);
        token = tokens.get(0);

        // check for semicolon
        if (token.getTokenType() != TokenType.SEMICOLON) {
            throw new Exception(String.format("Syntax Error\nMissing semicolon\n%s:%d", token.getFilename(), token.getLineNum()));
        }
        tokens.remove(0);

        return new returnStmtNode(r_expr);
    }

    @Override
    public String convertToJott() {
        String output = null;

        if (expr == null){
            return output;
        }

        output += "return";
        output += expr.convertToJott();
        output += ";";

        return output;
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
