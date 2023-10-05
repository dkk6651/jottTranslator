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
}
