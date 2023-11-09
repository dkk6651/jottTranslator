import java.util.ArrayList;

/**
Author: JD Rears jar6256
 */

public class funcDefParamsTNode implements JottTree {
    JottTree id;
    JottTree type;

    public funcDefParamsTNode(){}
    public static JottTree parse(ArrayList<Token> tokens) throws Exception{
        Token token = tokens.get(0);
        funcDefParamsTNode node = new funcDefParamsTNode();
        if (token.getTokenType() == TokenType.R_BRACKET){
            return null;
        }
        else if(token.getTokenType() != TokenType.COMMA){
            throw new Exception(String.format("Syntax Error\nParameters missing comma\n%s:%d", token.getFilename(), token.getLineNum()));
        }
        tokens.remove(0);
        node.id = idNode.parse(tokens);
        if(tokens.get(0).getTokenType() != TokenType.COLON){
            throw new Exception(String.format("Syntax Error\nParameters missing colon\n%s:%d", token.getFilename(), token.getLineNum()));
        }
        tokens.remove(0);
        node.type = typeNode.parse(tokens);

        return node;
    }
    
    @Override
    public String convertToJott() {
        return "," + this.id.convertToJott() + ":" + this.type.convertToJott();
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
        SymbolTable.scope.put(id.convertToJott(), id.validateTree());
        return id.validateTree();
    }
}
