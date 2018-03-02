package Parser;

import Compiler.CompilerState;
import Parser.Nodes.ParseNode;
import Parser.Nodes.ProgramNode;
import Tokenizer.*;

public class TokenParser {
    TokenReader tr;
    CompilerState cs;

    public TokenParser(TokenReader tr, CompilerState cs) {
        this.tr = tr;
        this.cs = cs;
    }

    public ParseNode parse() {
        return ProgramNode.parse(tr, cs.getIO());
    }

    public static String getErrorMsg(Token token, String expected) {
        return "Parse Error! Line: " + token.getLineCount() + " Character: " + token.getLineCount() + " Reason: Found '" + token + "' but expected '" + expected + "'";
    }
}
