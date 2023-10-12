/**File Name: funcCallNode.java
 * Description: Responsible for handling everything related to function call node in Jott grammar.
 *
 * @author: Daniel Kim
 */

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
                System.err.println();
                return null;
            }
            node.id = idNode.parse(tokens);
            if(tokens.get(0).getTokenType() == TokenType.L_BRACKET){
                tokens.remove(0);
                node.params = paramsNode.parse(tokens);
                if(tokens.get(0).getTokenType() == TokenType.R_BRACKET){
                    tokens.remove(0);
                    return node;
                }
                else{
                    System.err.println();
                    return null;
                }
            }
            else{
                System.err.println();
                return null;
            }
        }
        else{
            System.err.println();
            return null;
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
    public boolean validateTree() {
        return false;
    }
}
