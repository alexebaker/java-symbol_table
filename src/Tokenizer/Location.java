package Tokenizer;

import Compiler.CompilerState;

public class Location {
    private String path;
    private long lineCount;
    private long charCount;

    Location(CompilerState cs) {
        this.path = cs.getInputPath();
        this.lineCount = cs.getIO().getLineCount();
        this.charCount = cs.getIO().getCharCount();
    }

    public String getPath() {
        return path;
    }

    public long getLineCount() {
        return lineCount;
    }

    public long getCharCount() {
        return charCount;
    }
}
