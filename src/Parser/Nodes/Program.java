package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Compiler.CompilerState;
import Tokenizer.Tokens.EOFToken;

import java.util.Vector;


public class Program extends ASTNode {
    private Vector<ASTNode> blocks;

    public Program() {
        blocks = new Vector<>();
    }

    public void addBlock(ASTNode block) {
        blocks.add(block);
    }

    public Vector<ASTNode> getBlocks() {
        return blocks;
    }

    @Override
    public String getASTR() {
        StringBuilder str = new StringBuilder("");
        for (ASTNode block : getBlocks()) {
            str.append(block.getASTR());
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (ASTNode block : getBlocks()) {
            str.append(block);
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * Function to be called to parse a program. This will parse multiple statements.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ASTNode parse(TokenReader tr, CompilerState cs) {
        Program program = new Program();
        program.addBlock(Block.parse(tr, cs));

        if (EOFToken.isToken(tr.peek())) {
            tr.read();
        }
        else {
            cs.addError(new SyntaxError(tr.read(), "EOF"));
        }
        return program;
    }
}
