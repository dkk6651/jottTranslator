import java.util.ArrayList;

public class paramsTNode implements JottTree {
    public JottTree expression;
    @Override
    public String convertToJott() {
        return null;
    }

    public paramsTNode(){ }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        paramsTNode tNode = new paramsTNode();
        if(token.getTokenType()==TokenType.COMMA){
            tokens.remove(0);
            tNode.expression = exprNode.parse(tokens);
            return tNode;
        } else {
            throw new Exception(String.format("Syntax Error\n Token can not be parsed into a ParamsTNode\n{0}:{1}", token.getFilename(), token.getLineNum()));
        }
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
