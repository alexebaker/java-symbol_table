package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Tokenizer.Tokens.Token;

public class PrimType extends ASTNode {
    private Token token;

    public PrimType(Token token) {
        super();
        this.token = token;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        str.append(token.getValue());
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
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
