package treeNode; /**
 * @author Cole Allen; cda9685
 */

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class returnStmtNode implements JottTree {
    private JottTree expr;

    public returnStmtNode(JottTree expr) {
        this.expr = expr;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);

        if (token.getTokenType() == TokenType.R_BRACE) {
            return null;
        }

        // check for "return"
        if (!token.getToken().equals("return")) {
            throw new Exception(String.format("Syntax Error\nMissing return statement\n%s:%d", token.getFilename(), token.getLineNum()));
        }
        tokens.remove(0);

        if (!tokens.isEmpty() && SymbolTable.voidFlag)
            throw new Exception("Semantic Error\nVoid function cannot return a value");

        // parse for expression
        JottTree r_expr = exprNode. parse(tokens);
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
        String output = "";

        if (expr == null) {
            return output;
        }

        output += "return ";
        output += expr.convertToJott();
        output += ";";

        return output;
    }

    @Override
    public String convertToJava(String className) {
        String output = "";

        if (expr == null) {
            return output;
        }

        output += "return ";
        output += expr.convertToJava(className);
        output += ";";

        return output;
    }

    @Override
    public String convertToC() {
        String output = "";

        if (expr == null) {
            return output;
        }

        output += "return ";
        output += expr.convertToC();
        output += ";";

        return output;
    }

    @Override
    public String convertToPython() {
        String output = "";

        if (expr == null) {
            return output;
        }

        output += "return ";
        output += expr.convertToPython();

        return output;
    }

    @Override
    public ReturnType validateTree() throws Exception {
        // Returns the provided.ReturnType of the expression for the function def to validate
        return expr.validateTree();
    }
}
