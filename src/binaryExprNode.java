/**File Name: binaryExprNode.java
 * Description: Handles saving and returning when expression is called as a binary expression.
 *
 * @author: Daniel Kim
 */

import java.util.ArrayList;

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
