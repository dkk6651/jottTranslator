/**
 * @author Jonas Long 
*/

import java.util.ArrayList;
import java.util.Collection;
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
            throw new Exception(String.format("Syntax Error\nToken can not be parsed into a ParamsNode\n%s:%d",
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
    public ReturnType validateTree() throws Exception {
        LinkedHashMap<String, ReturnType> scope = SymbolTable.scope;

        //function has no params, func call was given no params
        if (expressionNode == null && scope.size() == 0) {
            return null; //valid
        }

        //assume that the parent func call node entered the scope of the target function when validating
        //check length
        if (expressions.size() + 1 != scope.size() - 1) { //plus one because the root expr node isn't in the list
            throw new Exception(String.format("Wrong number of arguments for function (expected %d, got %d)",
                    scope.size(), expressions.size()));
        }

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
