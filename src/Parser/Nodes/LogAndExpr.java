package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;

public class LogAndExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        ASTNode node = EqExpr.parse(tr);
        while (tr.peek().getValue().equals("&&")) {
            Operator temp = new Operator(tr.read());
            temp.setLhs(node);
            temp.setRhs(EqExpr.parse(tr));
            node = temp;
        }
        return node;
    }


}
