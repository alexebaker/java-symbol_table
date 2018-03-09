package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.PreunOp;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

public class Factor extends ASTNode {
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        if (PreunOp.isOp(tr.peek())) {
            Operator op = new PreunOp(tr.read());
            op.setRhs(Factor.parse(tr, cs));
            return op;
        }
        return PostfixExpr.parse(tr, cs);
    }
}
