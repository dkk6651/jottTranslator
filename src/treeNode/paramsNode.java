package treeNode; /**
 * @author Jonas Long 
*/

import provided.JottTree;
import provided.ReturnType;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class paramsNode implements JottTree {
    public JottTree expressionNode;
    ArrayList<JottTree> expressions;


    public paramsNode(){ }

    public static JottTree parse(ArrayList<Token> tokens) throws Exception {
        paramsNode node = new paramsNode();
        ArrayList<JottTree> expressions = new ArrayList<>();
        node.expressions = expressions; //shallow copy the list to node ahead of time

        Token curToken = tokens.get(0);
        if (curToken.getTokenType() == TokenType.R_BRACKET) {
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
            throw new Exception(String.format("Syntax Error\nprovided.Token can not be parsed into a ParamsNode\n%s:%d",
                    curToken.getFilename(), curToken.getLineNum()));
        }
    }

    @Override
    public String convertToJott() {
        if(expressionNode==null){
            return "";
        } else {
            ArrayList<String> paramStrs = new ArrayList<>();
            expressions.forEach(itm -> {paramStrs.add(itm.convertToJott());});

            return String.format("%s%s",expressionNode.convertToJott(), String.join(" ", paramStrs));
        }
    }

    @Override
    public String convertToJava(String className) {
        String build = "";
        if (expressionNode != null) {
            build += expressionNode.convertToJava(className);
            for (JottTree arg : expressions) {
                build += ", ";
                build += arg.convertToJava(className);
            }
        }
        return build;
    }

    @Override
    public String convertToC() {
        String build = "";
        if (expressionNode != null) {
            build += expressionNode.convertToC();
            for (JottTree arg : expressions) {
                build += ", ";
                build += arg.convertToC();
            }
        }
        return build;
    }

    @Override
    public String convertToPython(int depth) {
        String build = "";
        
        if (expressionNode!=null){
            build += expressionNode.convertToPython(depth);
            for (JottTree arg : expressions) {
                build += ", ";
                build+= arg.convertToPython(depth);
            }
        }
        return build;
    }

    @Override
    public ReturnType validateTree() throws Exception {
        LinkedHashMap<String, ReturnType> scope = new LinkedHashMap<>(SymbolTable.scope);

        if(SymbolTable.printFlag){
            SymbolTable.printFlag = false;
            if(expressionNode.validateTree() == ReturnType.Void){
                throw new Exception("Semantic Error:\nFunction \"Print\" cannot take in Void parameter");
            }
            else if(!expressions.isEmpty()){
                throw new Exception("Semantic Error:\nFunction \"Print\" cannot take more than one parameter");
            }
            else{
                SymbolTable.printFlag = false;
                return expressionNode.validateTree();
            }
        }

        //function has no params, func call was given no params
        if (expressionNode == null && scope.isEmpty()) {
            return null; //valid
        }

        int size = 0;
        if(expressionNode != null) size += 1;
        //assume that the parent func call node entered the scope of the target function when validating
        //check length
        if (expressions.size() + size != scope.size() - 1) { //plus one because the root expr node isn't in the list
            throw new Exception(String.format("Wrong number of arguments for function (expected %d, got %d)",
                    scope.size(), expressions.size()));
        }

        if(expressionNode != null){
            ArrayList<ReturnType> functionParams = new ArrayList<>(scope.values());
            //ignore index 0, which is the function return type

            ReturnType firstExpressionType = expressionNode.validateTree(); //check the type of the first param
            checkExpressionTypes(firstExpressionType, functionParams.get(1));

            //type checking
            for (int i = 0; i < expressions.size(); i++) {

                paramsTNode expr = (paramsTNode) expressions.get(i);
                ReturnType expressionType = expr.validateTree();

                int targetIndex = i + 2;
                ReturnType funcParamType = functionParams.get(targetIndex); //skip first and second entries (the return value and first param)

                checkExpressionTypes(expressionType, funcParamType);
            }
        }

        //valid
        return null; //don't return the functon type here, return it in the function call node
    }
    
    private void checkExpressionTypes(ReturnType expressionType, ReturnType funcParamType) throws Exception {
        if (!expressionType.equals(funcParamType)) {
            throw new Exception(
                    String.format("Wrong type for the function call parameter. (expected type %s, got %s)",
                            funcParamType, expressionType));
            
        }
    }
}
