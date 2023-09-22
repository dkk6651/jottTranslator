/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Daniel Kim
 * @author Jonas Long
 * @author JD Rears
 * @author Ishan Shah
 * @author Cole Allen
 **/

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

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
			int lineNumber = 1;
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
						result.add(new Token(array[i], filename, lineNumber, TokenType.COMMA));
					}
					else if(array[i].equals("]")){
						result.add(new Token(array[i], filename, lineNumber, TokenType.R_BRACKET));
					}
					else if(array[i].equals("[")){
						result.add(new Token(array[i], filename, lineNumber, TokenType.L_BRACKET));
					}
					else if(array[i].equals("}")){
						result.add(new Token(array[i], filename, lineNumber, TokenType.R_BRACE));
					}
					else if(array[i].equals("{")){
						result.add(new Token(array[i], filename, lineNumber, TokenType.L_BRACE));
					}
					else if(array[i].equals("=")){
						if(i+1 < array.length && array[i+1].equals("=")){
							result.add(new Token(array[i] + array[i+1], filename, lineNumber, TokenType.REL_OP));
							i++;
							continue;
						}
						result.add(new Token(array[i], filename, lineNumber, TokenType.ASSIGN));
					}
					else if(array[i].equals("<") || array[i].equals(">")){
						if(i+1 < array.length && array[i+1].equals("=")){
							result.add(new Token(array[i] + array[i+1], filename, lineNumber, TokenType.REL_OP));
							i++;
							continue;
						}
						result.add(new Token(array[i], filename, lineNumber, TokenType.REL_OP));
					}
					else if(array[i].equals("/") || array[i].equals("+") || array[i].equals("-") || array[i].equals("*")){
						result.add(new Token(array[i], filename, lineNumber, TokenType.MATH_OP));
					}
					else if(array[i].equals(";")){
						result.add(new Token(array[i], filename, lineNumber, TokenType.SEMICOLON));
					}
					else if(array[i].equals(".")){
						if(i+1 < array.length && isNumeric(array[i+1])){
							StringBuilder number = new StringBuilder();
							number.append(array[i]);
							while(i+1 < array.length && isNumeric(array[i+1])){
								number.append(array[i+1]);
								i++;
							}
							result.add(new Token(number.toString(), filename, lineNumber, TokenType.NUMBER));
						}
						else if(i+1 <= array.length || !isNumeric(array[i+1])){
							System.err.println("Syntax Error\n" +
									"Invalid token \"" + array[i] +"\"\n" +
									filename + ":" + lineNumber);
							return null;
						}
						else{
							return null;
						}
					}
					else if(isNumeric(array[i])){
						StringBuilder number = new StringBuilder();
						number.append(array[i]);
						if(i+1 < array.length){
							while(i+1 < array.length && (isNumeric(array[i+1])) && !array[i+1].equals(".")){
								number.append(array[i+1]);
								i++;
							}
							if(array[i+1].equals(".")){
								number.append(array[i+1]);
								i++;
								while(i+1 < array.length && (isNumeric(array[i+1]))){
									number.append(array[i+1]);
									i++;
								}
							}
						}
						result.add(new Token(number.toString(), filename, lineNumber, TokenType.NUMBER));
					}
					else if(isAlpha(array[i])){
						StringBuilder id = new StringBuilder();
						id.append(array[i]);
						if(i+1 < array.length && isAlpha(array[i+1])){
							while(i+1 < array.length && (isAlpha(array[i+1]) || isNumeric(array[i+1]))){
								id.append(array[i+1]);
								i++;
							}
						}
						result.add(new Token(id.toString(), filename, lineNumber, TokenType.ID_KEYWORD));
					}
					else if(array[i].equals(":")){
						if(i+1 < array.length && array[i+1].equals(":")){
							result.add(new Token("::", filename, lineNumber, TokenType.FC_HEADER));
							i++;
							continue;
						}
						result.add(new Token(array[i], filename, lineNumber, TokenType.COLON));
					}
					else if(array[i].equals("!")){
						if(i+1 < array.length && array[i+1].equals("=")){
							i++;
							result.add(new Token("!=", filename, lineNumber, TokenType.REL_OP));
						}
						else{
							System.err.println("Syntax Error\n" +
									"Invalid token \"" + array[i] +"\"\n" +
									filename + ":" + lineNumber);
							return null;
						}
					}
					else if(array[i].equals("\"")){
						StringBuilder string = new StringBuilder();
						string.append(array[i]);
						if(i+1 < array.length){
							while(!array[i+1].equals("\"")){
								string.append(array[i+1]);
								i++;
								if(i+1 >= array.length){
									System.err.println("Syntax Error\n" +
											"Invalid token \"" + array[i] +"\"\n" +
											filename + ":" + lineNumber);
									return null;
								}
								if(array[i+1].equals("\"")){
									string.append(array[i+1]);
									result.add(new Token(string.toString(), filename, lineNumber, TokenType.STRING));
									i++;
									break;
								}
							}
						}
						else{
							System.err.println("Syntax Error\n" +
									"Invalid token \"" + array[i] +"\"\n" +
									filename + ":" + lineNumber);
							return null;
						}
					}
				}
				lineNumber++;
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