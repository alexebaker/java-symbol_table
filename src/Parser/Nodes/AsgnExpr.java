package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

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

    public String getASTR() {
        StringBuilder str = new StringBuilder("");
        if (condExpr != null) {
            if (asgnExpr != null) {
                str.append("(");
                str.append(condExpr.getASTR());
                str.append("=");
                str.append(asgnExpr.getASTR());
                str.append(")");
            }
            else {
                str.append(condExpr.getASTR());
            }
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (condExpr != null) {
            if (asgnExpr != null) {
                str.append(condExpr);
                str.append("=");
                str.append(asgnExpr);
            }
            else {
                str.append(condExpr.getASTR());
            }
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        AsgnExpr asgnExpr = new AsgnExpr();
        asgnExpr.setCondExpr(CondExpr.parse(tr));
        if (tr.peek().getValue().equals("=")) {
            tr.read();
            asgnExpr.setAsgnExpr(AsgnExpr.parse(tr, cs));
        }
        return asgnExpr;
    }
}
