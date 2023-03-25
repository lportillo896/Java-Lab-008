/**
 *
 * @author Trevor Hartman
 * @author Logan Portillo
 *
 * @since Version 1.0
 *
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a file path or press Q to exit: ");
            String input = console.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            }
            Path path = Paths.get(input);
            File file = path.toFile();

            System.out.println("Would you like to skip whitespace? (y/n): ");
            String white = console.nextLine();
            boolean skipWs;
            skipWs = white.equalsIgnoreCase("y");

            /*
             * Within this try/catch block, which is used to handle possible errors thrown by the code in the try block,
             * write code to get the line, word, and character count of the File object created above!
             */

            try {
                FileStats fs = new FileStats(file, skipWs);
                fs.read();

                /*
                 * You will access the FileStats object's getter methods to get the file's line, word, character count and
                 * the files name. You should use a format specifier to print it all out similar to the following example:
                 *
                 * Stats: lines - 6, words - 46, chars - 237 /path/to/file/fileName.txt
                 */

                System.out.printf("Statistics: %d Lines, %d Words, %d Chars %s%n", fs.getNumLines(),
                        fs.getNumWords(), fs.getNumChars(), fs.getFileName());

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }
}
