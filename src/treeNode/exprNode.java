package treeNode; /**File Name: treeNode.exprNode.java
 * Description: Responsible for handling anything related to expression nodes in Jott Grammar.
 *
 * @author: Daniel Kim
 **/

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public abstract class exprNode implements JottTree {
    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        JottTree node;
        if(token.getTokenType() == TokenType.NUMBER){
            node = numNode.parse(tokens);
        }
        else if(token.getToken().equals("True") || token.getToken().equals("False")){
            node = boolNode.parse(tokens);
            return node;
        }
        else if(token.getTokenType() == TokenType.ID_KEYWORD){
            node = idNode.parse(tokens);
        }
        else if(token.getTokenType() == TokenType.STRING){
            node = stringNode.parse(tokens);
            return node;
        }
        else if(token.getTokenType() == TokenType.FC_HEADER){
            node = funcCallNode.parse(tokens);
        }
        else{
            throw new Exception(String.format("Syntax Error\n provided.Token can not be parsed into a Expression Node\n%s:%d", token.getFilename(), token.getLineNum()));
        }

        if(tokens.get(0).getTokenType() == TokenType.MATH_OP || tokens.get(0).getTokenType() == TokenType.REL_OP){
            return new binaryExprNode(node, opNode.parse(tokens), exprNode.parse(tokens));
        }
        else{
            return node;
        }
    }
}
