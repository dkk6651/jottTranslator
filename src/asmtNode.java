/**
 * @author Cole Allen; cda9685
 */

import java.util.ArrayList;

public class asmtNode implements JottTree {
    private JottTree type;
    private JottTree id;
    private JottTree expr;

    public asmtNode(JottTree type, exprNode id, JottTree expr) {
        this.type = type;
        this.id = id;
        this.expr = expr;
    }

    public asmtNode(exprNode id, JottTree expr) {
        this.id = id;
        this.expr = expr;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        boolean a = false; // turns true if a type token is present

        Token token = tokens.get(0);

        JottTree a_type = null;

        // if first token is type, parse for type
        if(token.getToken().equals("Double") || token.getToken().equals("Integer") ||
                token.getToken().equals("String") || token.getToken().equals("Boolean)")) {
                    a_type = typeNode.parse(tokens);
                    a = true;
        }

        // parse for id (does regardless)
        exprNode a_id = idNode.parse(tokens);

        // check for equals sign
        if (tokens.get(0).getTokenType() != TokenType.ASSIGN) {
            throw new Exception(String.format("Syntax Error\n Token is not an assignment\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
        }
        tokens.remove(0);

        // parse for expr
        JottTree a_expr = exprNode.parse(tokens);

        // check for semicolon
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            throw new Exception(String.format("Syntax Error\n Missing semicolon\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
        }
        tokens.remove(0);

        if (a) {
            return new asmtNode(a_type, a_id, a_expr);
        }
        else {
            return new asmtNode(a_id, a_expr);
        }
    }

    @Override
    public String convertToJott() {
        String output = "";

        if (type != null) {
            output += type.convertToJott() + " ";
        }
        
        output += id.convertToJott();
        output += " = ";
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
    public ReturnType validateTree() throws Exception {
        if (type == null) {
            // Checks whether this id exists already & return its type
            if (id.validateTree() != null) {
                throw new Exception(String.format("Semantic Error\nThe id '%s' is already in use", id));
            }
        }

        // Checks if the expression matches the type provided
        if (SymbolTable.symTable.paramInScopeMatchType(id.convertToJott(), ReturnType.Integer)
                && !expr.validateTree().equals(ReturnType.Integer)) {
            throw new Exception("Semantic Error\nType does not match assignment");
        }
        else if (SymbolTable.symTable.paramInScopeMatchType(id.convertToJott(), ReturnType.Double)
                && !expr.validateTree().equals(ReturnType.Double)) {
            throw new Exception("Semantic Error\nType does not match assignment");
        }
        else if (SymbolTable.symTable.paramInScopeMatchType(id.convertToJott(), ReturnType.String)
                && !expr.validateTree().equals(ReturnType.String)) {
            throw new Exception("Semantic Error\nType does not match assignment");
        }
        else if (SymbolTable.symTable.paramInScopeMatchType(id.convertToJott(), ReturnType.Boolean)
                && !expr.validateTree().equals(ReturnType.Boolean)) {
            throw new Exception("Semantic Error\nType does not match assignment");
        }

        return null;
    }
}
