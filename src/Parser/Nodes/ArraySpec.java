package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

public class ArraySpec extends ASTNode {
    private ASTNode expr;

    public ArraySpec() {
        this.expr = null;
    }

    public void setExpr(ASTNode expr) {
        this.expr = expr;
    }

    @Override
    public String getASTR(int indentDepth) {
        StringBuilder str = new StringBuilder("");
        str.append("[");
        if (expr != null) {
            str.append(expr.getASTR(indentDepth));
        }
        str.append("]");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        if (tr.peek().getValue().equals("[")) {
            tr.read();
            ArraySpec arraySpec = new ArraySpec();
            if (tr.peek().getValue().equals("]")) {
                tr.read();
                return arraySpec;
            }
            else {
                arraySpec.setExpr(Expr.parse(tr, cs));
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
