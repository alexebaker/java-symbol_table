package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.PreunOp;
import Tokenizer.Tokens.IdentifierToken;
import Tokenizer.Tokens.NumberToken;
import Tokenizer.TokenReader;
import Compiler.CompilerState;
import Tokenizer.Tokens.Token;

public class PrimaryExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        if (IdentifierToken.isToken(tr.peek())) {
            return Identifier.parse(tr, cs);
        }
        else if (NumberToken.isToken(tr.peek())) {
            return Number.parse(tr, cs);
        }
        else if (tr.peek().getValue().equals("(")) {
            tr.read();
            ASTNode node = Expr.parse(tr, cs);
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

    public static boolean beginsPrimaryExpr(Token token) {
        return PrimaryExpr.beginsPrimaryExpr(token.getValue());
    }

    public static boolean beginsPrimaryExpr(String str) {
        return IdentifierToken.isToken(str) || NumberToken.isToken(str) || str.equals("(");
    }
}
