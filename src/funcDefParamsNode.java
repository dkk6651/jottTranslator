/**
Author: JD Rears jar6256

 */

import java.util.ArrayList;

public class funcDefParamsNode implements JottTree {
    private ArrayList<JottTree> params;
    private JottTree type;
    private JottTree id;
    
    public funcDefParamsNode(){}

     public static JottTree parse(ArrayList<Token> tokens) throws Exception{
        Token token = tokens.get(0);
        funcDefParamsNode node = new funcDefParamsNode();
        if (token.getToken().equals("]")){
            return null;
        }else if (token.getTokenType() != TokenType.ID_KEYWORD){
            throw new Exception(String.format("Syntax Error\nToken cannot be pared into ID\n%s:%d", token.getFilename(), token.getLineNum())) ;
        }
        node.id = idNode.parse(tokens);
        
        if (tokens.get(0).getTokenType() != TokenType.COLON){
            throw new Exception(String.format("Syntax Error\nToken can not be parsed into a ParamsNode\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
        }
        tokens.remove(0);
        node.type = typeNode.parse(tokens);

        while(!tokens.get(0).getToken().equals("]")){
            node.params.add(funcDefParamsTNode.parse(tokens));
        }

        return node;
     }

 
    @Override
    public String convertToJott() {
        StringBuilder string = new StringBuilder(this.id.convertToJott() + ":" + this.type.convertToJott());

        if(params != null) {
            while (!params.isEmpty()) {
                string.append(params.get(0).convertToJott());
            }
        }

        return string.toString();
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
        for (JottTree param : params) {
            param.validateTree();
        }
        return null;
    }
}
