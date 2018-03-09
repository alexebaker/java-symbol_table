package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.Tokens.IdentifierToken;
import Tokenizer.Tokens.NumberToken;
import Tokenizer.TokenReader;
import Compiler.CompilerState;
import Compiler.SymbolTable;
import Tokenizer.Tokens.Token;

public class PrimaryExpr extends ASTNode {
    public static ASTNode parse(CompilerState cs, SymbolTable st) throws SyntaxError {
        TokenReader tr = cs.getTr();
        if (IdentifierToken.isToken(tr.peek())) {
            return Identifier.parse(cs, st);
        }
        else if (NumberToken.isToken(tr.peek())) {
            return Number.parse(cs, st);
        }
        else if (tr.peek().getValue().equals("(")) {
            tr.read();
            ASTNode node = Expr.parse(cs, st);
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
