package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;

public class CondExpr extends ASTNode {
    private ASTNode logOrExpr;
    private ASTNode expr;
    private ASTNode condExpr;

    public CondExpr() {
        logOrExpr = null;
        expr = null;
        condExpr = null;
    }

    public void setLogOrExpr(ASTNode logOrExpr) {
        this.logOrExpr = logOrExpr;
    }

    public void setCondExpr(ASTNode condExpr) {
        this.condExpr = condExpr;
    }

    public void setExpr(ASTNode expr) {
        this.expr = expr;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        if (logOrExpr != null) {
            if (expr != null && condExpr != null) {
                str.append("(");
                str.append(logOrExpr.getFPIFStr());
                str.append("?");
                str.append(expr.getFPIFStr());
                str.append(":");
                str.append(condExpr.getFPIFStr());
                str.append(")");
            }
            else {
                str.append(logOrExpr.getFPIFStr());
            }
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        CondExpr condExpr = new CondExpr();
        condExpr.setLogOrExpr(LogOrExpr.parse(tr));
        if (tr.peek().getValue().equals("?")) {
            tr.read();
            condExpr.setExpr(Expr.parse(tr));
            if (tr.peek().getValue().equals(":")) {
                tr.read();
                condExpr.setCondExpr(CondExpr.parse(tr));
            }
            else {
                throw new SyntaxError(tr.read(), ":");
            }
        }
        return condExpr;
    }


}
