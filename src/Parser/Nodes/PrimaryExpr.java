package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.Tokens.IdentifierToken;
import Tokenizer.Tokens.NumberToken;
import Tokenizer.TokenReader;

public class PrimaryExpr extends ASTNode {

    public PrimaryExpr() {
        super();
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        if (IdentifierToken.isToken(tr.peek())) {
            return Identifier.parse(tr);
        }
        else if (NumberToken.isToken(tr.peek())) {
            return Number.parse(tr);
        }
        else if (tr.peek().getValue().equals("(")) {
            tr.read();
            ASTNode node = Expr.parse(tr);
            if (tr.peek().getValue().equals(")")) {
                tr.read();
                return node;
            }
            else {
                throw new SyntaxError(tr.read(), ")");
            }
        }
        else {
            throw new SyntaxError(tr.read(), "IDENTIFIER, NUMBER, or (");
        }
    }
}
