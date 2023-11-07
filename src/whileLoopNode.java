/**
 * @author: Ishan Shah is4761
 */
import java.util.ArrayList;

public class whileLoopNode implements JottTree {

    public String value;
    public JottTree condition;
    public JottTree body_node;

    public whileLoopNode(Token token){
        this.value = token.getToken();
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        whileLoopNode node = null;
        // check for while
        if(token.getToken().equals("while")){
            tokens.remove(0);
            node = new whileLoopNode(token);
            Token next_token = tokens.get(0);
            //check for [
            if (!next_token.getToken().equals("[")) {
                throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a [\n "
                        + next_token.getFilename() + ":" + next_token.getLineNum());
            }
            else{
                tokens.remove(0);
                node.condition = exprNode.parse(tokens);
                //check for ]
                next_token = tokens.get(0);
                if (!next_token.getToken().equals("]")) {
                    throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a ]\n "
                            + next_token.getFilename() + ":" + next_token.getLineNum());
                }
                else{
                    tokens.remove(0);
                    next_token = tokens.get(0);
                    //check for {
                    if (!next_token.getToken().equals("{")) {
                        throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a {\n "
                                + next_token.getFilename() + ":" + next_token.getLineNum());
                    }
                    else{
                        tokens.remove(0);
                        node.body_node = bodyNode.parse(tokens);
                        // check for }
                        next_token = tokens.remove(0);
                        if (!next_token.getToken().equals("}")) {
                            throw new Exception("Syntax Error\n Token " + next_token.getToken() + " cannot be parsed into a }\n "
                                    + next_token.getFilename() + ":" + next_token.getLineNum());
                        }
                    }

                }

            }
        }
        return node;
    }


    @Override
    public String convertToJott() {
        return "while["+condition.convertToJott()+"]{"+body_node.convertToJott()+"}";
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
        //check boolean condition
        return this.condition.validateTree() && this.body_node.validateTree();
    }
}
