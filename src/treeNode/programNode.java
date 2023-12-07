package treeNode; /**
 * @author Jonas Long
 */

import provided.JottTree;
import provided.ReturnType;
import provided.Token;

import java.util.ArrayList;

public class programNode implements JottTree {
    ArrayList<JottTree> functionDefNodes;

    public programNode() {}

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        programNode pNode = new programNode();
        pNode.functionDefNodes = new ArrayList<>();
        SymbolTable.symTable.addFunc("length", ReturnType.Integer);
        SymbolTable.symTable.addParamToFunc("length", "input", ReturnType.String);

        SymbolTable.symTable.addFunc("concat", ReturnType.String);
        SymbolTable.symTable.addParamToFunc("concat", "input1", ReturnType.String);
        SymbolTable.symTable.addParamToFunc("concat", "input2", ReturnType.String);

        SymbolTable.symTable.addFunc("print", ReturnType.Void);

        while (!tokens.isEmpty()) {
            pNode.functionDefNodes.add(functionDefNode.parse(tokens));
        }
        pNode.validateTree();
        return pNode;
    }

    @Override
    public String convertToJott() {
        StringBuilder jottOutput = new StringBuilder();
        for (JottTree funcDef : functionDefNodes) {
            jottOutput.append(funcDef.convertToJott());
            jottOutput.append("\n");
        }
        return jottOutput.toString();
    }

    @Override
    public String convertToJava(String className) {
        StringBuilder javaOutput = new StringBuilder();
        javaOutput.append("public class ").append(className).append(" {");
        for (JottTree funcDef : functionDefNodes) {
            javaOutput.append(funcDef.convertToJava(className));
            javaOutput.append("\n");
        }
        javaOutput.append("}");
        return javaOutput.toString();
    }

    @Override
    public String convertToC() {
        StringBuilder cOutput = new StringBuilder();
        cOutput.append("#include <stdio.h>\n");
        cOutput.append("#include <string.h>\n");
        cOutput.append("#include <stdlib.h>\n");
        cOutput.append("#include \"stringHelper.h\"\n");
        for (JottTree funcDef : functionDefNodes) {
            cOutput.append(funcDef.convertToC());
            cOutput.append("\n");
        }
        return cOutput.toString();
    }

    @Override
    public String convertToPython(int depth) {
        StringBuilder pythonOutput = new StringBuilder();
        for (JottTree funcDef : functionDefNodes) {
            pythonOutput.append(funcDef.convertToPython(0));
            pythonOutput.append("\n");
        }
        return pythonOutput.toString();
    }

    @Override
    public ReturnType validateTree() throws Exception {
        //check that main function exists and has no params
        if (SymbolTable.symTable.checkFunc("main")) {
            SymbolTable.symTable.enterScope("main");
            if(SymbolTable.scope.size()!=1){
                throw new Exception("\"main\" function may not take any arguments");
            }
            if(SymbolTable.scope.get("return") != ReturnType.Void){
                throw new Exception("\"main\" function must return nothing");
            }
            SymbolTable.symTable.exitScope();
        } else {
            throw new Exception("\"main\" function is not defined");
        }

        return null;
    }
}
