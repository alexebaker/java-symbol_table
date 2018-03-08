package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Tokenizer.Tokens.NumberToken;
import Tokenizer.Tokens.Token;

public class Number extends ASTNode {
    private Token token;

    public Number(Token token) {
        this.token = token;
    }

    public String getASTR() {
        return token.getValue();
    }

    @Override
    public String toString() {
        return token.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        if (NumberToken.isToken(tr.peek())) {
            return new Number(tr.read());
        }
        else {
            throw new SyntaxError(tr.read(), "NUMBER");
        }
    }
}
