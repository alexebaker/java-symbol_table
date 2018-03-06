package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.EqOp;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;

public class EqExpr extends ASTNode {
    public EqExpr() {
        super();
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        ASTNode node = RelExpr.parse(tr);
        while (EqOp.isOp(tr.peek())) {
            Operator temp = new EqOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(RelExpr.parse(tr));
            node = temp;
        }
        return node;
    }
}
