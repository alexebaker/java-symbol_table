package Compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Contains the current state of the compiler at any given time
 */
public class CompilerState {
    private String inputPath;
    private CompilerIO io;


    public CompilerState() {
        this.inputPath = "<stdin>";
        this.io = new CompilerIO();
    }

    public CompilerState(String fileName) {
        this.inputPath = fileName;

        try {
            this.io = new CompilerIO(new FileReader(fileName), System.out);
        }
        catch (FileNotFoundException ex) {
            System.err.println("Could not find file: " + fileName);
            ex.printStackTrace();
            this.io.close();
            System.exit(1);
        }
    }

    /**
     * Gets the Compiler.CompilerIO object for reading and writing
     *
     * @return the Compiler IO object
     */
    public CompilerIO getIO() {
        return this.io;
    }

    /**
     * Path the the file or input being read from
     *
     * @return Path to input stream
     */
    public String getInputPath() {
        return this.inputPath;
    }
}
