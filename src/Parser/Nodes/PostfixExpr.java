package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.PostunOp;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

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

    @Override
    public String getASTR(int indentDepth) {
        StringBuilder str = new StringBuilder("");
        if (primaryExpr != null) {
            str.append("(");
            str.append(primaryExpr.getASTR(indentDepth));

            if (arraySpec != null) {
                str.append(arraySpec.getASTR(indentDepth));
            }

            if (postfixExpr != null) {
                str.append(postfixExpr.getASTR(indentDepth));
            }
            str.append(")");
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        ASTNode node = null;
        if (PrimaryExpr.beginsPrimaryExpr(tr.peek())) {
            node = PrimaryExpr.parse(tr, cs);
            while (PostunOp.isOp(tr.peek()) || tr.peek().getValue().equals("[")) {
                if (PostunOp.isOp(tr.peek())) {
                    Operator temp = new PostunOp(tr.read());
                    temp.setLhs(node);
                    temp.setRhs(PostfixExpr.parse(tr, cs));
                    node = temp;
                } else {
                    PostfixExpr temp = new PostfixExpr();
                    temp.setPrimaryExpr(node);
                    temp.setArraySpec(ArraySpec.parse(tr, cs));
                    temp.setPostfixExpr(PostfixExpr.parse(tr, cs));
                    node = temp;
                }
            }
        }
        return node;
    }

}
