package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;

public class ArraySpec extends ASTNode {
    private ASTNode expr;

    public ArraySpec() {
        this.expr = null;
    }

    public void setExpr(ASTNode expr) {
        this.expr = expr;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        str.append("[");
        if (expr != null) {
            str.append(expr.getFPIFStr());
        }
        str.append("]");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        if (tr.peek().getValue().equals("[")) {
            tr.read();
            ArraySpec arraySpec = new ArraySpec();
            if (tr.peek().getValue().equals("]")) {
                tr.read();
                return arraySpec;
            }
            else {
                arraySpec.setExpr(Expr.parse(tr));
                if (tr.peek().getValue().equals("]")) {
                    tr.read();
                    return arraySpec;
                }
                else {
                    throw new SyntaxError(tr.read(), "]");
                }
            }
        }
        else {
            throw new SyntaxError(tr.read(), "[");
        }
    }
}
