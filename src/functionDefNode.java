/**
Author: JD Rears jar6256
 */
public class functionDefNode implements JottTree {
     private JottTree funcName;
     private ArrayList<JottTree> params;
     private JottTree return;
     private JottTree body;

    
    public static JottTree parse(ArrayList<token> tokens throws Exception){
        Token token  = tokens.get(0);
        functionDefNode node = new functionDefNode();
        ArrayList<JottTree> params = new ArrayList<>();
        if token.getToken().equals("def"){
            tokens.remove(0);
            node.funcName = idNode.parse(tokens);
            if tokens.get(0).getToken().equals('['){
                tokens.remove(0);
                while (!tokens.get(0).getToken().equals("]")) {
                    params.add(funcDefParamsNode.parse(tokens));
                 }
            }else{
                throw new Exception("Syntax Error: expected '[' as next token"); 
            }
            
            node.params = params

            if tokens.get(0).getToken().equals(']'){
                tokens.remove(0);
            }else{
                throw new Exception("Syntax Error: expected ']' as next token");
            }

            if tokens.get(0).getToken().equals(':'){
                tokens.remove(0);
                node.return = functionReturnNode.parse(tokens);
            }else{
                throw new Exception("Syntax Error: expected ':' as next token")
            }
            
            if tokens.get(0).getToken().equals('{'){
                tokens.remove(0);
                node.body = bodyNode.parse(tokens);
            }else{
                throw new Exception("Syntax Error: expected '{' as next token") 
            }
        }else{
            throw new Exception("Syntax Error: expected 'def' as next token")
        }
        return node;
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
