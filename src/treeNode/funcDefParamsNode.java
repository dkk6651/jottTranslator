package treeNode;

/**
Author: JD Rears jar6256

*/

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class funcDefParamsNode implements JottTree {
    private ArrayList<JottTree> params = new ArrayList<>();
    private JottTree type;
    private JottTree id;

    public funcDefParamsNode() {
    }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        Token token = tokens.get(0);
        funcDefParamsNode node = new funcDefParamsNode();
        if (token.getToken().equals("]")) {
            return null;
        } else if (token.getTokenType() != TokenType.ID_KEYWORD) {
            throw new Exception(String.format("Syntax Error\nprovided.Token cannot be pared into ID\n%s:%d",
                    token.getFilename(), token.getLineNum()));
        }
        node.id = idNode.parse(tokens);

        if (tokens.get(0).getTokenType() != TokenType.COLON) {
            throw new Exception(String.format("Syntax Error\nprovided.Token can not be parsed into a ParamsNode\n%s:%d",
                    tokens.get(0).getFilename(), tokens.get(0).getLineNum()));
        }
        tokens.remove(0);
        node.type = typeNode.parse(tokens);

        while (!tokens.get(0).getToken().equals("]")) {
            node.params.add(funcDefParamsTNode.parse(tokens));
        }

        return node;
    }

    @Override
    public String convertToJott() {
        StringBuilder string = new StringBuilder(this.id.convertToJott() + ":" + this.type.convertToJott());

        while (!params.isEmpty()) {
            string.append(params.get(0).convertToJott());
        }

        return string.toString();
    }

    @Override
    public String convertToJava(String className) {
        StringBuilder string = new StringBuilder(
                this.type.convertToJava(className) + ' ' + this.id.convertToJava(className));
        while (!params.isEmpty()) {
            string.append(',');
            string.append(params.get(0).convertToJava(className));
        }
        return string.toString();
    }

    @Override
    public String convertToC() {
        StringBuilder string = new StringBuilder(this.type.convertToC() + ' ' + this.id.convertToC());
        while (!params.isEmpty()) {
            string.append(',');
            string.append(params.get(0).convertToC());
        }
        return string.toString();
    }

    @Override
    public String convertToPython(int depth) {
        StringBuilder string = new StringBuilder(this.id.convertToPython(depth));
        while (!params.isEmpty()) {
            string.append(',');
            string.append(params.get(0).convertToPython(depth));
        }
        return string.toString();
    }

    @Override
    public ReturnType validateTree() throws Exception {
        if (id != null) {
            SymbolTable.scope.put(id.convertToJott(), type.validateTree());
        }
        for (JottTree param : params) {
            param.validateTree();
        }
        return null;
    }
}
