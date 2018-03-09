package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.EqOp;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

public class EqExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        ASTNode node = RelExpr.parse(tr, cs);
        while (EqOp.isOp(tr.peek())) {
            Operator temp = new EqOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(RelExpr.parse(tr, cs));
            node = temp;
        }
        return node;
    }
}
