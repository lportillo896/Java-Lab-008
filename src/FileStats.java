/**
 *
 * @author Trevor Hartman
 * @author Logan Portillo
 *
 * @since Version 1.0
 *
 */

import java.io.*;

public class FileStats {
    private int numLines;
    private int numWords;
    private int numChars;
    private boolean skipWhiteSpace;
    private File f;

    public FileStats(File f, boolean skipWhiteSpace) throws FileNotFoundException {

        /*
         * Use the File objects exists method to determine if the File passed in actually exists.
         * If it does not exist, throw the FileNotFoundException as shown below:
         *
         * throw new FileNotFoundException(String.format("File: %s does not exist.", f.getName()));
         */

        numLines = 0;
        numWords = 0;
        numChars = 0;
        this.skipWhiteSpace = skipWhiteSpace;
        this.f = f;

        if (!f.exists()) {
            throw new FileNotFoundException(String.format("The file %s does not exist.",f.getName()));
        }
    }
    private static int countWords(String line) {
        if (line == null || line.isEmpty()) {
            return 0;
        }
        else {
            String[] words = line.split("\\s+");
            return words.length;
        }
    }
    private static String removeSpaces(String line) {
        if (line == null || line.isEmpty()) {
            return "";
        }
        else {
            return String.join("", line.split("\\s+"));
        }
    }
    private static int countChars(String line, boolean skipWhiteSpace) {
        if (skipWhiteSpace == true) {
            line = removeSpaces(line);
        }
        int numChars = 0;
        for (int i = 0; i <= line.length(); i++) {
            numChars++;
        }
        return numChars;
    }

    public void read(File f) throws FileNotFoundException, IOException {
        if( !f.exists() ) {
            throw new FileNotFoundException(String.format("File: %s does not exist.", f.getName()));
        }
        this.f = f;
        this.read();
    }

    public void read() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        int lineNum = 0;

        while (br.ready()) {
            lineNum++;
            String line= br.readLine();
            numChars += countChars(line, skipWhiteSpace);
            numWords += countWords(line);
            numLines = lineNum;
        }
    }

    public int getNumLines() {
        return numLines;
    }

    public int getNumWords() {
        return numWords;
    }

    public int getNumChars() {
        return numChars;
    }

    public String getFileName() {
        return this.f.getAbsolutePath();
    }
}
