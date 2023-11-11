import java.util.ArrayList;
import java.util.LinkedHashMap;

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
            if(SymbolTable.symTable.checkFunc(node.funcName.convertToJott())){
                throw new Exception(String.format("Semantic Error:\nFunction %s already defined\n%s:%d", node.funcName.convertToJott(), token.getFilename(), token.getLineNum()));
            }
            SymbolTable.symTable.addFunc(node.funcName.convertToJott(), node.returnNode.validateTree());

            if (tokens.get(0).getToken().equals("{")){
                tokens.remove(0);
                node.validateTree();
                SymbolTable.symTable.enterScope(node.funcName.convertToJott());
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
        SymbolTable.symTable.exitScope();
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
    public ReturnType validateTree() throws Exception { 
        ReturnType returnType = returnNode.validateTree();
        String name = funcName.convertToJott();
        SymbolTable.symTable.enterScope(name);
        if(params != null){
            params.validateTree();
        }
        SymbolTable.symTable.functions.replace(name, new LinkedHashMap<>(SymbolTable.scope));
        SymbolTable.symTable.exitScope();
        return returnType;
    }
}
