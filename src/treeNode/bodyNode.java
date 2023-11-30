package treeNode; /**
 * @author Cole Allen; cda9685
 * @author Daniel Kim; dkk6651
 */

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class bodyNode implements JottTree {
    private ArrayList<JottTree> body_stmt;
    private JottTree return_stmt;

    public bodyNode(ArrayList<JottTree> body_stmt, JottTree return_stmt) {
        this.body_stmt = body_stmt;
        this.return_stmt = return_stmt;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        ArrayList<JottTree> bodyList = new ArrayList<>();

        if(tokens.get(0).getTokenType() == TokenType.R_BRACE) return new bodyNode(bodyList, null);
        while(!tokens.get(0).getToken().equals("return")){
            bodyList.add(bodyStmtNode.parse(tokens));
            if(tokens.get(0).getTokenType() == TokenType.R_BRACE && SymbolTable.voidFlag)
                return new bodyNode(bodyList, null);
            else if (tokens.get(0).getTokenType() == TokenType.R_BRACE && !SymbolTable.voidFlag)
                throw new Exception("Semantic Error\nExpected return for non-void function");
        }

        // parse for return statement
        JottTree b_return_stmt = returnStmtNode.parse(tokens);

        bodyNode body = new bodyNode(bodyList, b_return_stmt);
        body.validateTree();

        return body;
    }

    @Override
    public String convertToJott() {
        StringBuilder string = new StringBuilder();
        for (JottTree jottTree : this.body_stmt) {
            string.append(jottTree.convertToJott());
            if(jottTree.getClass() == funcCallNode.class){
                string.append(";\n");
            }
        }
        if(this.return_stmt != null){
            string.append(this.return_stmt.convertToJott());
        }
        return string.toString();
    }

    @Override
    public String convertToJava(String className) {
        StringBuilder string = new StringBuilder();
        for (JottTree jottTree : this.body_stmt) {
            string.append(jottTree.convertToJava(className));
            if(jottTree.getClass() == funcCallNode.class){
                string.append(";\n");
            }
        }
        if(this.return_stmt != null){
            string.append(this.return_stmt.convertToJava(className));
        }
        return string.toString();
    }

    @Override
    public String convertToC() {
        StringBuilder string = new StringBuilder();
        for (JottTree jottTree : this.body_stmt) {
            string.append(jottTree.convertToC());
            if(jottTree.getClass() == funcCallNode.class){
                string.append(";\n");
            }
        }
        if(this.return_stmt != null){
            string.append(this.return_stmt.convertToC());
        }
        return string.toString();
    }

    @Override
    public String convertToPython(int depth) {
        StringBuilder string = new StringBuilder();
        for (JottTree jottTree : this.body_stmt) {
            string.append("\t".repeat(Math.max(0, depth)));
            string.append(jottTree.convertToPython(depth + 1));
            if(jottTree.getClass() == funcCallNode.class){
                string.append("\n");
            }
        }
        if(this.return_stmt != null){
            string.append(this.return_stmt.convertToPython(depth + 1));
        }
        return string.toString();
    }


    @Override
    public ReturnType validateTree() throws Exception {
        for (JottTree body_s : body_stmt) {
            body_s.validateTree();
        }

        return_stmt.validateTree();

        return null;
    }
    
}
