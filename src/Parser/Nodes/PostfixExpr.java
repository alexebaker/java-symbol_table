package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.PostunOp;
import Tokenizer.TokenReader;

public class PostfixExpr extends ASTNode {
    private ASTNode primaryExpr;
    private ASTNode postfixExpr;
    private ASTNode arraySpec;

    public PostfixExpr() {
        this.primaryExpr = null;
        this.postfixExpr = null;
        this.arraySpec = null;
    }

    public void setPostfixExpr(ASTNode postfixExpr) {
        this.postfixExpr = postfixExpr;
    }

    public void setArraySpec(ASTNode arraySpec) {
        this.arraySpec = arraySpec;
    }

    public void setPrimaryExpr(ASTNode primaryExpr) {
        this.primaryExpr = primaryExpr;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        if (primaryExpr != null) {
            str.append("(");
            str.append(primaryExpr);

            if (arraySpec != null) {
                str.append(arraySpec);
            }

            if (postfixExpr != null) {
                str.append(postfixExpr);
            }
            str.append(")");
        }

        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        ASTNode node = PrimaryExpr.parse(tr);
        while (PostunOp.isOp(tr.peek()) || tr.peek().getValue().equals("[")) {
            if (PostunOp.isOp(tr.peek())) {
                Operator temp = new PostunOp(tr.read());
                temp.setLhs(node);
                temp.setRhs(PostfixExpr.parse(tr));
                node = temp;
            }
            else {
                ASTNode temp = new PostfixExpr();
                ((PostfixExpr) temp).setPrimaryExpr(node);
                ((PostfixExpr) temp).setArraySpec(ArraySpec.parse(tr));
                ((PostfixExpr) temp).setPostfixExpr(PostfixExpr.parse(tr));
                node = temp;
            }
        }
        return node;
    }

}
