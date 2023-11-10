/**File Name: funcCallNode.java
 * Description: Responsible for handling everything related to function call node in Jott grammar.
 *
 * @author: Daniel Kim
 */

import java.util.ArrayList;
import java.util.Set;

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
                throw new Exception(String.format("Syntax Error\nToken is not an ID\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
            }
            if(!SymbolTable.symTable.checkFunc(tokens.get(0).getToken())){
                throw new Exception(String.format("Semantic Error\nFunction is not defined\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
            }
            node.id = idNode.parse(tokens);
            if(tokens.get(0).getTokenType() == TokenType.L_BRACKET){
                tokens.remove(0);
                node.params = paramsNode.parse(tokens);
                ReturnType returnType = ReturnType.Void;
                if (isInteger(node.params.convertToJott())) {
                    returnType = ReturnType.Integer;
                }
                else if (isDouble(node.params.convertToJott())) {
                    returnType = ReturnType.Double;
                }
                Set<String> keys = SymbolTable.symTable.getFuncParams(node.id.convertToJott()).keySet();
                for (String key : keys) {
                    if (!key.equals("return")) {
                        if (!SymbolTable.symTable.checkParamFunc(node.id.convertToJott(), key, returnType)) {
                            throw new Exception(String.format("Semantic Error\nMethod cannot be applied to the given types\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
                        }
                    }
                }
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
    public ReturnType validateTree() {
        return SymbolTable.symTable.getFuncReturn(this.id.convertToJott());
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
