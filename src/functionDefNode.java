import java.util.ArrayList;

/**
Author: JD Rears jar6256
 */
public class functionDefNode implements JottTree {
     private JottTree funcName;
     private JottTree params;
     private JottTree returnNode;
     private JottTree body;

    public functionDefNode(){}

    public static JottTree parse(ArrayList<Token> tokens) throws Exception{
        Token token  = tokens.get(0);
        functionDefNode node = new functionDefNode();
        if (token.getToken().equals("def")){
            tokens.remove(0);
            node.funcName = idNode.parse(tokens);
            if (tokens.get(0).getToken().equals("[")){
                tokens.remove(0);
                node.params = funcDefParamsNode.parse(tokens);
            }else{
                throw new Exception("Syntax Error: expected '[' as next token"); 
            }

            if (tokens.get(0).getToken().equals("]")){
                tokens.remove(0);
            }else{
                throw new Exception("Syntax Error: expected ']' as next token");
            }

            if (tokens.get(0).getToken().equals(":")){
                tokens.remove(0);
                node.returnNode = functionReturnNode.parse(tokens);
            }else{
                throw new Exception("Syntax Error: expected ':' as next token");
            }
            
            if (tokens.get(0).getToken().equals("{")){
                tokens.remove(0);
                node.body = bodyNode.parse(tokens);
                if(!tokens.get(0).getToken().equals("}")){
                    throw new Exception("Syntax Error: expected '}' as next token");
                }
                tokens.remove(0);
            }else{
                throw new Exception("Syntax Error: expected '{' as next token");
            }
        }else{
            throw new Exception("Syntax Error: expected 'def' as next token");
        }
        return node;
    }
    @Override
    public String convertToJott() {
        String params;
        if(this.params == null){
            params = "";
        }
        else{
            params = this.params.convertToJott();
        }
        return "def " + this.funcName.convertToJott() + "[" + params + "]:" + this.returnNode.convertToJott() + "{" + this.body.convertToJott() + "}";
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
        returnType = this.returnNode.typeReturn
        SymbolTable.addFunc(this.funcName, returnType)
        for param in this.params:
            SymbolTable.addParamToFunc(this.funcName, param.id, returnType)
    }
}
