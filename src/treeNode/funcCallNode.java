package treeNode; /**File Name: treeNode.funcCallNode.java
 * Description: Responsible for handling everything related to function call node in Jott grammar.
 *
 * @author: Daniel Kim
 */

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class funcCallNode implements JottTree {
    private JottTree fcHeader;
    private JottTree id;
    private JottTree params;

    public funcCallNode() {
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        funcCallNode node = new funcCallNode();
        if(token.getTokenType() == TokenType.FC_HEADER){
            node.fcHeader = fcHeaderNode.parse(tokens);
            if(tokens.get(0).getTokenType() != TokenType.ID_KEYWORD){
                throw new Exception(String.format("Syntax Error\nprovided.Token is not an ID\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
            }
            if(!SymbolTable.symTable.checkFunc(tokens.get(0).getToken())){
                throw new Exception(String.format("Semantic Error\nFunction is not defined\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
            }
            node.id = idNode.parse(tokens);
            if(tokens.get(0).getTokenType() == TokenType.L_BRACKET){
                tokens.remove(0);
                String funcName = node.id.convertToJott();
                SymbolTable.symTable.enterScope(funcName);
                if(funcName.equals("print")) SymbolTable.printFlag = true;
                node.params = paramsNode.parse(tokens);
                if(funcName.equals("print")) SymbolTable.printFlag = true;
                node.params.validateTree();
                SymbolTable.symTable.exitScope();
                if(tokens.get(0).getTokenType() == TokenType.R_BRACKET){
                    tokens.remove(0);
                    return node;
                }
                else{
                    throw new Exception(String.format("Syntax Error\nMissing right bracket ']'\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
                }
            }
            else{
                throw new Exception(String.format("Syntax Error\nMissing left bracket '['\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
            }
        }
        else{
            throw new Exception(String.format("Syntax Error\nMissing function header\n%s:%d", token.getFilename(), token.getLineNum()));
        }
    }

    @Override
    public String convertToJott() {
        return String.format("%s%s[%s]", this.fcHeader.convertToJott(), this.id.convertToJott(), this.params.convertToJott());
    }

    @Override
    public String convertToJava(String className) {
        if (this.id.convertToJava(className).equals("print")) {
            return "System.out.println(" + this.params.convertToJava(className) + ")";
        }
        return className + "." + this.id.convertToJava(className) + "(" + this.params.convertToJava(className) + ")";
    }

    @Override
    public String convertToC() {
        if(this.id.convertToC().equals("print")){
            try{
                if(this.params.validateTree() == ReturnType.Integer){
                    return "printInt(" + this.params.convertToC() +")";
                }
                else if(this.params.validateTree() == ReturnType.String){
                    return "printString(" + this.params.convertToC() + ")";
                }
                else if(this.params.validateTree() == ReturnType.Double){
                    return "printDouble(" + this.params.convertToC() + ")";
                }
                else if(this.params.validateTree() == ReturnType.Boolean){
                    return "printBool(" + this.params.convertToC() + ")";
                }
            }
            catch(Exception ignored){}

        }
        return this.id.convertToC() + "(" + this.params.convertToC() + ")";
    }

    @Override
    public String convertToPython(int depth) {
        return this.id.convertToPython(depth) + "(" + this.params.convertToPython(depth) +")";
    }

    @Override
    public ReturnType validateTree() {
        return SymbolTable.symTable.getFuncReturn(this.id.convertToJott());
    }
}
