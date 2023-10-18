import java.util.ArrayList;

public class varDecNode implements JottTree {
    private JottTree type;
    private exprNode id;

    public varDecNode(JottTree type, exprNode id) {
        this.type = type;
        this.id = id;
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        // parse for type
        JottTree v_type = typeNode.parse(tokens);

        // parse for id
        exprNode v_id = idNode.parse(tokens);

        // check for semicolon
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            throw new Exception(String.format("Syntax Error\nMissing semicolon\n%s:%d", tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
        }
        tokens.remove(0);

        return new varDecNode(v_type, v_id);
    }

    @Override
    public String convertToJott() {
        String output = type.convertToJott();
        output += id.convertToJott();
        output += ";";

        return output;
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
