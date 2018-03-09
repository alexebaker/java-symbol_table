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

    @Override
    public String getASTR(int indentDepth) {
        StringBuilder str = new StringBuilder("");
        if (condExpr != null) {
            if (asgnExpr != null) {
                str.append("(");
                str.append(condExpr.getASTR(indentDepth));
                str.append("=");
                str.append(asgnExpr.getASTR(indentDepth));
                str.append(")");
            }
            else {
                str.append(condExpr.getASTR(indentDepth));
            }
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        AsgnExpr asgnExpr = new AsgnExpr();
        asgnExpr.setCondExpr(CondExpr.parse(tr, cs));
        if (tr.peek().getValue().equals("=")) {
            tr.read();
            asgnExpr.setAsgnExpr(AsgnExpr.parse(tr, cs));
        }
        return asgnExpr;
    }
}
