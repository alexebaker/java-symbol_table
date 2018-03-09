package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Tokenizer.Tokens.Token;
import Compiler.CompilerState;

public class PrimType extends ASTNode {
    private Token token;

    public PrimType(Token token) {
        this.token = token;
    }

    @Override
    public String getASTR(int indentDepth) {
        return token.getValue();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        if (PrimType.isType(tr.peek())) {
            return new PrimType(tr.read());
        }
        else {
            throw new SyntaxError(tr.read(), "bool, unsigned, or signed");
        }
    }

    public static boolean isType(Token token) {
        return PrimType.isType(token.getValue());
    }

    public static boolean isType(String str) {
        switch (str) {
            case "bool":
            case "unsigned":
            case "signed":
                return true;
            default:
                return false;
        }
    }
}
