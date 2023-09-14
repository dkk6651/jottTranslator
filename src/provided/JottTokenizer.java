package provided;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
    	ArrayList<Token> result = new ArrayList<>();

    	try{
    		FileReader fr = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fr);
			LineNumberReader lnr = new LineNumberReader(fr);
			String line;

			while((line = reader.readLine()) != null){
				String[] array = line.split("");
				for(int i = 0; i < array.length; i++){
					if(array[i].equals("\n")){
						continue;
					}
					else if(array[i].equals(" ")){
						continue;
					}
					else if(array[i].equals("#")){
						StringBuilder comment = new StringBuilder();
						while(i < array.length){
							comment.append(array[i]);
							i++;
						}
					}
					else if(array[i].equals(",")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.COMMA));
					}
					else if(array[i].equals("]")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.R_BRACKET));
					}
					else if(array[i].equals("[")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.L_BRACKET));
					}
					else if(array[i].equals("}")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.R_BRACE));
					}
					else if(array[i].equals("{")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.L_BRACE));
					}
					else if(array[i].equals("=")){
						if(array[i + 1].equals("=")){
							result.add(new Token(array[i] + array[i+1], filename, lnr.getLineNumber(), TokenType.REL_OP));
							i++;
							continue;
						}
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.ASSIGN));
					}
					else if(array[i].equals("<") || array[i].equals(">")){
						if(array[i + 1].equals("=")){
							result.add(new Token(array[i] + array[i+1], filename, lnr.getLineNumber(), TokenType.REL_OP));
							i++;
							continue;
						}
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.REL_OP));
					}
					else if(array[i].equals("/") || array[i].equals("+") || array[i].equals("-") || array[i].equals("*")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.MATH_OP));
					}
					else if(array[i].equals(";")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.SEMICOLON));
					}
					else if(array[i].equals(".")){
						if(i+1 < array.length && isNumeric(array[i+1])){
							StringBuilder number = new StringBuilder();
							number.append(array[i]);
							while(i < array.length && isNumeric(array[i+1])){
								number.append(array[i+1]);
								i++;
							}
							result.add(new Token(number.toString(), filename, lnr.getLineNumber(), TokenType.NUMBER));
						}
						else if(!isNumeric(array[i+1])){
							System.out.println("Syntax Error\n" +
									"Invalid token \"" + array[i] +"\"\n" +
									filename + ":" + lnr.getLineNumber());
							return null;
						}
					}
					else if(isNumeric(array[i])){
						StringBuilder number = new StringBuilder();
						number.append(array[i]);
						if(i+1 < array.length && isNumeric(array[i+1])){
							while(i+1 < array.length && isNumeric(array[i+1])){
								number.append(array[i+1]);
								i++;
							}
						}
						result.add(new Token(number.toString(), filename, lnr.getLineNumber(), TokenType.NUMBER));
					}
					else if(isAlpha(array[i])){
						StringBuilder id = new StringBuilder();
						id.append(array[i]);
						if(i+1 < array.length && isAlpha(array[i+1])){
							while(i+1 < array.length && isAlpha(array[i+1])){
								id.append(array[i+1]);
								i++;
							}
						}
						result.add(new Token(id.toString(), filename, lnr.getLineNumber(), TokenType.ID_KEYWORD));
					}
					else if(array[i].equals(":")){
						result.add(new Token(array[i], filename, lnr.getLineNumber(), TokenType.COLON));
					}
					else if(array[i].equals("!")){
						if(i+1 < array.length && array[i+1].equals("=")){
							i++;
							result.add(new Token("!=", filename, lnr.getLineNumber(), TokenType.REL_OP));
						}
						else{
							System.out.println("Syntax Error\n" +
									"Invalid token \"" + array[i] +"\"\n" +
									filename + ":" + lnr.getLineNumber());
							return null;
						}
					}
					else if(array[i].equals("\"")){
						StringBuilder string = new StringBuilder();
						string.append(array[i]);
						if(i+1 < array.length){
							while(array[i+1].equals(" ") || isNumeric(array[i+1]) || isAlpha(array[i+1])){
								string.append(array[i+1]);
								i++;
								if(i+1 >= array.length){
									System.out.println("Syntax Error\n" +
											"Invalid token \"" + array[i] +"\"\n" +
											filename + ":" + lnr.getLineNumber());
									return null;
								}
								if(array[i+1].equals("\"")){
									string.append(array[i+1]);
									result.add(new Token(string.toString(), filename, lnr.getLineNumber(), TokenType.STRING));
									break;
								}
							}
						}
					}
				}
			}
		}
    	catch(FileNotFoundException e){
    		System.out.println("File not found");
    		e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static boolean isNumeric(String str){
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public static boolean isAlpha(String str) {
		return str.matches("^[a-zA-Z]*$");
	}
}