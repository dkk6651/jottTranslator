import java.util.HashMap;

public class SymbolTable {
    HashMap<String, HashMap<String, ReturnType>> functions = new HashMap<>();
    public SymbolTable(){}

    /**
     * Adds function to the symbol table
     * @param name name of the function to add
     * @param returnType return type of the function
     * @return True if successful else False
     */
    public Boolean addFunc(String name, ReturnType returnType){
        if(functions.containsKey(name)){
            return false;
        }
        functions.put(name, new HashMap<>());
        functions.get(name).put("return", returnType);
        return true;
    }

    /**
     * Adds parameter of the function to the symbol table
     * @param funcName function of the parameter to add
     * @param paramName parameter to add
     * @param type return type to add with parameter
     * @return True if successful else False
     */
    public Boolean addParamToFunc(String funcName, String paramName, ReturnType type){
        if(functions.containsKey(funcName)){
            return false;
        }
        if(functions.get(funcName).containsKey(paramName)){
            return false;
        }
        functions.get(funcName).put(paramName, type);
        return true;
    }

    /**
     * Checks to see if the parameter is in function
     * @param funcName function to search
     * @param paramName parameter to search
     * @return Boolean based on existence of parameter in the function
     */
    public Boolean checkParamInFunc(String funcName, String paramName){
        return functions.get(funcName).containsKey(paramName);
    }

    /**
     * Checks to see if the return type of the saved parameter matches the search type
     * @param funcName function to search
     * @param paramName parameter to search
     * @param type type of the parameter
     * @return Boolean based on if the checked parameter type matches saved parameter type
     */
    public Boolean checkParamFunc(String funcName, String paramName, ReturnType type){
        return functions.get(funcName).get(paramName) == type;
    }

    /**
     * Checks to see if the function exists in the symbol table
     * @param funcName function to search
     * @return Boolean based on the existence of function in the symbol table
     */
    public Boolean checkFunc(String funcName){
        return functions.containsKey(funcName);
    }

    /**
     * Checks to see if the return type matches the searched return type
     * @param funcName function to search
     * @param type return type to search
     * @return Boolean based on if the return type of the function matches the searched return type
     */
    public Boolean checkFuncReturn(String funcName, ReturnType type){
        return functions.get(funcName).get("return") == type;
    }
}
