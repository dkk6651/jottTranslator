import java.util.ArrayList;

public class stringNode extends exprNode{
    Token token;

    public stringNode(Token token){
        this.token = token;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        if(tokens.get(0).getTokenType() != TokenType.STRING){
            System.err.println();
            throw new Exception();
        }
        return new stringNode(tokens.remove(0));
    }
}
