package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.RelOp;
import Tokenizer.TokenReader;

public class RelExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        ASTNode node = SimpleExpr.parse(tr);
        while (RelOp.isOp(tr.peek())) {
            Operator temp = new RelOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(SimpleExpr.parse(tr));
            node = temp;
        }
        return node;
    }
}
