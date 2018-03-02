package Compiler;

import Parser.Nodes.ProgramNode;
import Parser.TokenParser;
import Tokenizer.TokenReader;

public class LCC {
    CompilerState cs;

    public LCC(CompilerState cs) {
        this.cs = cs;
    }

    public int compile() {
        TokenParser tp = new TokenParser(new TokenReader(cs), cs);
        ProgramNode node = (ProgramNode) tp.parse();
        return node.getNumErrors();
    }
}
