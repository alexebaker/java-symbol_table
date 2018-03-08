package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.TermOp;
import Tokenizer.TokenReader;

public class SimpleExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        ASTNode node = Term.parse(tr);
        while (TermOp.isOp(tr.peek())) {
            Operator temp = new TermOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(Term.parse(tr));
            node = temp;
        }
        return node;
    }
}
