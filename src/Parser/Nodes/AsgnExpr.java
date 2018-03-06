package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;

public class AsgnExpr extends ASTNode {
    private ASTNode asgnExpr;
    private ASTNode condExpr;

    public AsgnExpr() {
        this.asgnExpr = null;
        this.condExpr = null;
    }

    public void setAsgnExpr(ASTNode asgnExpr) {
        this.asgnExpr = asgnExpr;
    }

    public void setCondExpr(ASTNode condExpr) {
        this.condExpr = condExpr;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        if (condExpr != null) {
            if (asgnExpr != null) {
                str.append("(");
                str.append(condExpr.getFPIFStr());
                str.append("=");
                str.append(asgnExpr.getFPIFStr());
                str.append(")");
            }
            else {
                str.append(condExpr);
            }
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        AsgnExpr asgnExpr = new AsgnExpr();
        asgnExpr.setCondExpr(CondExpr.parse(tr));
        if (tr.peek().getValue().equals("=")) {
            tr.read();
            asgnExpr.setAsgnExpr(AsgnExpr.parse(tr));
        }
        return asgnExpr;
    }
}
