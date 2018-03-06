package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;

public class LogOrExpr extends ASTNode {

    public LogOrExpr() {
        super();
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        ASTNode node = LogAndExpr.parse(tr);
        while (tr.peek().getValue().equals("||")) {
            Operator temp = new Operator(tr.read());
            temp.setLhs(node);
            temp.setRhs(LogAndExpr.parse(tr));
            node = temp;
        }
        return node;
    }
}
