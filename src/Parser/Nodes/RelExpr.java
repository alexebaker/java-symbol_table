package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.RelOp;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

public class RelExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        ASTNode node = SimpleExpr.parse(tr, cs);
        while (RelOp.isOp(tr.peek())) {
            Operator temp = new RelOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(SimpleExpr.parse(tr, cs));
            node = temp;
        }
        return node;
    }
}
