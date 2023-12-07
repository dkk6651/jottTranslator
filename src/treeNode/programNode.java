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
    public String convertToPython(int depth) {
        return null;
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
