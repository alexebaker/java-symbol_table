package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.PreunOp;
import Tokenizer.TokenReader;

public class Factor extends ASTNode {

    public Factor() {
        super();
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        if (PreunOp.isOp(tr.peek())) {
            Operator op = new PreunOp(tr.read());
            op.setRhs(Factor.parse(tr));
            return op;
        }
        return PostfixExpr.parse(tr);
    }
}
