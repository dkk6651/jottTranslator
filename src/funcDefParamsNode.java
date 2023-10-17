/**
Author: JD Rears jar6256

 */

import provided.JottTree;
import provided.ParserException;
import provided.Token;
import provided.TokenType;
import java.util.ArrayList;

public class funcDefParamsNode implements JottTree {
    private JottTree param;
    private JottTree type;
    
    public funcDefParamsNode(String id, Type type, funcdefparamst funcdefparamst){
        this.id = id;
        this.type=type;
    }

     public static JottTree parse(ArrayList<token> tokens throws Exception){
        Token token = tokens.get(0);
        if token.getToken().equals(']'){
            return null;
        }else if token.getToken().equals(':') || token.getTokenType() != TokenType.ID_KEYWORD{
            throw new Exception("Syntax Error") ; // TODO add msg
        }
        String id = idNode.parse(tokens)

        
        if tokens.get(0).getToken.equals(':'){
            tokens.remove(0)
            Type type = FuncDefParamsTNode.parse(tokens)
            return new funcDefParamsNode(id, type)
        }

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
