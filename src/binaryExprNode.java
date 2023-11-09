/**File Name: binaryExprNode.java
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
        ReturnType leftType = left.validateTree();
        ReturnType rightType = right.validateTree();
        ReturnType opType = op.validateTree();
        if(leftType != rightType || leftType == ReturnType.Void){
            throw new Exception();
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
