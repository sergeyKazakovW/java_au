import javax.imageio.stream.FileImageInputStream;

import static java.lang.System.*;
import java.io.*;

public class Generator {
    static String readFile(String filename) throws IOException {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(filename);
        } catch(FileNotFoundException e){
            System.out.println("Cannot open file \"" + filename + "\"");
            throw new FileNotFoundException();
        }

        String result = "";
        int c;
        while ((c = inputStream.read()) != -1) {
            result += (char)c;
        }

        inputStream.close();

        return result;
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 2){
            System.out.println("Usage: prog input_file output.md");
        }

        String inputText;
        String existingMarkdownText;

        try{
            inputText = readFile(args[0]);
            existingMarkdownText = readFile(args[1]);
        } catch (IOException e){
            if(e instanceof FileNotFoundException) {
                return; //We already printed an error in readText;
            } else{
                System.out.println(e.toString());
                return;
            }
        }

        String inputLines[] = inputText.split("\n");
        String existingMarkdownLines[] = existingMarkdownText.split("\n");

        if(inputLines.length < 3){
            System.out.println("Error: invalid input file");
        }

        String taskName = inputLines[0].split("\\.\\s")[1]; //regex checks for ". "
        taskName = taskName.substring(0, taskName.length() - 1); //removes '\n' from the end
        String taskLink = inputLines[1];
        String taskLinkWords[] = taskLink.split("/");
        String taskID = taskLinkWords[taskLinkWords.length - 2];//The last one is '\n'

        PrintWriter writer = new PrintWriter(args[1]);

        int existingMarkdownPosition = 0;
        if(existingMarkdownLines.length > 1) {
            // Maybe it's not the best criteria of non-empty file
            writer.print(existingMarkdownLines[0]);
            writer.println();
            existingMarkdownPosition = 2; //3-rd line
            while(existingMarkdownLines[existingMarkdownPosition].charAt(0) == '+'){
                writer.print(existingMarkdownLines[existingMarkdownPosition]);
                existingMarkdownPosition++;
            }
        }
        else{
            writer.println("# Module name"); //This line should be edited in resulting file
            writer.println();
        }

        writer.println("+ [" + taskName + "](#" + taskID + ")");

        if(existingMarkdownLines.length > 2) {
            for (; existingMarkdownPosition < existingMarkdownLines.length; existingMarkdownPosition++) {
                writer.print(existingMarkdownLines[existingMarkdownPosition]);
            }
        }

        writer.println();
        writer.println("## " + taskName);
        writer.println();
        writer.print(taskLink);
        writer.println();
        writer.println("'''java");
        for(int inputLinesPosition = 2; inputLinesPosition < inputLines.length; inputLinesPosition++) {
            writer.print(inputLines[inputLinesPosition]);
        }

        writer.println("'''");

        writer.close();
    }
}
