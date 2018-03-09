package Compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;
import Errors.Error;
import Parser.Nodes.ASTNode;

/**
 * Contains the current state of the compiler at any given time
 */
public class CompilerState {
    private String inputPath;
    private CompilerIO io;
    private Vector<Error> errors;
    private ASTNode ast;


    public CompilerState() {
        this.inputPath = "<stdin>";
        this.io = new CompilerIO();
        this.errors = new Vector<>();
        this.ast = null;
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

        this.errors = new Vector<>();
        this.ast = null;
    }

    public Vector<Error> getErrors() {
        return errors;
    }

    public void addError(Error error) {
        errors.add(error);
    }

    public void setAST(ASTNode ast) {
        this.ast = ast;
    }

    public void printBOFPIF() {
        if (ast != null) {
            io.write(ast.getBOFPIF());
        }
    }

    public void printErrors() {
        for (Error error : errors) {
            System.err.println(error);
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
