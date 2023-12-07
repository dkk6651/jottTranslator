package treeNode;

import provided.JottTree;
import provided.ReturnType;

/**File Name: treeNode.binaryExprNode.java
 * Description: Handles saving and returning when expression is called as a binary expression.
 *
 * @author: Daniel Kim
 */

public class binaryExprNode extends exprNode{
    JottTree left;
    JottTree op;
    JottTree right;

    public binaryExprNode(JottTree left, JottTree op, JottTree right){
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String convertToJott(){
        return String.format("%s %s %s", this.left.convertToJott(), this.op.convertToJott(), this.right.convertToJott());
    }

    @Override
    public String convertToJava(String className) {
        return this.left.convertToJava(className) + " " + this.op.convertToJava(className) + " " + this.right.convertToJava(className);
    }

    @Override
    public String convertToC() {
        return this.left.convertToC() + " " + this.op.convertToC() + " " + this.right.convertToC();
    }

    @Override
    public String convertToPython(int depth) {
        return this.left.convertToPython(depth) + " " + this.op.convertToPython(depth) + " " + this.right.convertToPython(depth);
    }

    @Override
    public ReturnType validateTree() throws Exception {
        ReturnType leftType = left.validateTree();
        ReturnType rightType = right.validateTree();
        ReturnType opType = op.validateTree();
        if(leftType != rightType || leftType == ReturnType.Void){
            throw new Exception("Semantic Error:\nExpression types do not match.");
        }
        else{
            if(opType == ReturnType.RelOP){
                return ReturnType.Boolean;
            }
            else{
                return leftType;
            }
        }
    }
}
