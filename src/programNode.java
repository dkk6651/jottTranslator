/**
 * @author Jonas Long
 */

import java.util.ArrayList;

public class programNode implements JottTree {
    ArrayList<JottTree> functionDefNodes;

    public programNode() {}

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        programNode pNode = new programNode();
        pNode.functionDefNodes = new ArrayList<>();

        while (!tokens.isEmpty()) {
            pNode.functionDefNodes.add(functionDefNode.parse(tokens));
        }
        return pNode;
    }

    @Override
    public String convertToJott() {
        StringBuilder jottOutput = new StringBuilder();
        for (JottTree funcDef : functionDefNodes) {
            jottOutput.append(funcDef.convertToJott());
            jottOutput.append(" ");
        }
        return jottOutput.toString();
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
    public ReturnType validateTree() {
        return null;
    }
}
