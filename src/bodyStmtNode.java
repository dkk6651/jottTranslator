import java.util.ArrayList;

public abstract class bodyStmtNode {
    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);

        // check if if-statement
        if (token.getToken().equals("if")) {
            return ifStmtNode.parse(tokens);
        }

        // check if while loop
        else if (token.getToken().equals("while")) {
            return whileLoopNode.parse(tokens);
        }

        // check if function call
        else if (token.getTokenType() == TokenType.FC_HEADER) {
            return funcCallNode.parse(tokens);
        } else if (token.getTokenType() == TokenType.ID_KEYWORD) {
            if(token.getToken().equals("Double") || token.getToken().equals("Integer") || token.getToken().equals("String") || token.getToken().equals("Boolean")){
                // Look 2 tokens ahead
                if (tokens.get(2).getTokenType() == TokenType.SEMICOLON) {
                    return varDecNode.parse(tokens);
                } else {
                    return asmtNode.parse(tokens);
                }
            }
            else{
                return asmtNode.parse(tokens);
            }
        } else {
            throw new Exception(); //@Todo error handling
        }
    }
}
