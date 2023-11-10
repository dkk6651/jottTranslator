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
                node.validateTree();
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
        String returnTypeString = returnNode.convertToJott();
        ReturnType returnType = convertStringToReturnType(returnTypeString);
        funcName.validateTree();
        String name = funcName.convertToJott();
        SymbolTable.symTable.addFunc(name, returnType);

        // So far only works if there is one parameter in a function
        if (params != null) {
            String p = params.convertToJott();
            while (!p.equals("")) {
                int index1 = p.indexOf(':');
                int index2 = p.indexOf(',');
                String p_name = p.substring(0, index1);
                String p_returnTypeString;
                if (index2 != -1) {
                    p_returnTypeString = p.substring(index1 + 1, index2);
                }
                else {
                    p_returnTypeString = p.substring(index1 + 1);
                }
                ReturnType p_returnType = convertStringToReturnType(p_returnTypeString);
                SymbolTable.symTable.addParamToFunc(name, p_name, p_returnType);
                if (index2 != -1) {
                    p = p.substring(index2 + 1);
                }
                else {
                    p = "";
                }
            }
        }
        SymbolTable.symTable.enterScope(name);
        if (params != null) {
            params.validateTree();
        }
        SymbolTable.symTable.exitScope();
        return returnType;
    }

    private ReturnType convertStringToReturnType(String p_returnTypeString) throws Exception {
        return switch (p_returnTypeString) {
            case "Integer" -> ReturnType.Integer;
            case "Double" -> ReturnType.Double;
            case "String" -> ReturnType.String;
            case "Boolean" -> ReturnType.Boolean;
            case "Void" -> ReturnType.Void;
            default -> throw new Exception("Semantic Error\nReturn type is not valid");
        };
    }
}
