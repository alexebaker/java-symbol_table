package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Tokenizer.Tokens.IdentifierToken;
import Tokenizer.Tokens.Token;

public class Identifier extends ASTNode {
    private Token token;

    public Identifier(Token token) {
        super();
        this.token = token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        str.append(token.getValue());
        return str.toString();
    }

    @Override
    public String toString() {
        return token.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        if (IdentifierToken.isToken(tr.peek())) {
            return new Identifier(tr.read());
        }
        else {
            throw new SyntaxError(tr.read(), "IDENTIFIER");
        }
    }
}
