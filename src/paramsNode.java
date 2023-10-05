import java.util.ArrayList;

public class paramsNode implements JottTree {
    public JottTree expressionNode;
    ArrayList<JottTree> expressions;


    public paramsNode(){ }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        paramsNode node = new paramsNode();
        ArrayList<JottTree> expressions = new ArrayList<>();
        node.expressions = expressions; //shallow copy the list to node ahead of time

        Token curToken = tokens.get(0);
        if(curToken.getTokenType() == TokenType.R_BRACKET){
            return node;
        } else {
            node.expressionNode = exprNode.parse(tokens);

            boolean foundEnd = true;
            while (foundEnd) { //only leave loop on error, returns when end bracket is found
                foundEnd = false;
                for (Token i : tokens) {
                    if (i.getTokenType() == TokenType.R_BRACKET) {
                        return node;
                    } else if (i.getTokenType() == TokenType.COMMA) {
                        expressions.add(paramsTNode.parse(tokens));
                        foundEnd = true;
                    }
                }
            }
            throw new Exception(String.format("Syntax Error\n Token can not be parsed into a ParamsNode\n{0}:{1}", curToken.getFilename(), curToken.getLineNum()));
        }




    }

    @Override
    public String convertToJott() {
        return null;
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
