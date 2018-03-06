package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.FactorOp;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;

public class Term extends ASTNode {

    public Term() {
        super();
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError{
        ASTNode node = Factor.parse(tr);
        while (FactorOp.isOp(tr.peek())) {
            Operator temp = new FactorOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(Factor.parse(tr));
            node = temp;
        }
        return node;
    }
}
