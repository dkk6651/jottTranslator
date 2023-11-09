import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File Name: jottTranslator.java
 * Description main file for jott translator
 * @author Daniel Kim
 */

public class jottTranslator {
    public static void main(String[] args){
        assert args.length == 3;
        String input = args[0];
        String output = args[1];
        String language = args[2];
        String temp = "";

        JottTree tree = JottParser.parse(JottTokenizer.tokenize(input));
        assert tree != null;
        switch (language) {
            case "jott", "Jott" -> temp = tree.convertToJott();
            case "C", "c" -> temp = tree.convertToC();
            case "Java", "java" -> temp = tree.convertToJava(output.substring(0, output.length() - 5));
            case "Python", "python" -> temp = tree.convertToPython();
        }

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            bw.append(temp);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
