package Parser.Nodes;

import Parser.Operators.FactorOp;
import Parser.Operators.Operator;
import Parser.Operators.PreunOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class FactorNode extends ParseNode {
    private PostfixExprNode postfixExprNode;
    private PreunOp preunOp;
    private FactorNode factorNode;

    public FactorNode() {
        this.postfixExprNode = null;
        this.preunOp = null;
        this.factorNode = null;
    }

    public void setFactorNode(FactorNode factorNode) {
        this.factorNode = factorNode;
    }

    public void setPostfixExprNode(PostfixExprNode postfixExprNode) {
        this.postfixExprNode = postfixExprNode;
    }

    public void setPreunOp(PreunOp preunOp) {
        this.preunOp = preunOp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (postfixExprNode != null) {
            str.append(postfixExprNode);
        }
        else if (preunOp != null && factorNode != null) {
            str.append("(");
            str.append(preunOp);
            str.append(factorNode);
            str.append(")");
        }
        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        if (PreunOp.isOp(tr.peek())) {
            Operator op = new PreunOp(tr.read());
            ParseNode node = FactorNode.parse(tr);
            if (node != null) {
                op.setRhs(node);
                return op;
            }
            return null;
        }

        ParseNode postfixExprNode = PostfixExprNode.parse(tr);
        if (postfixExprNode != null) {
            return postfixExprNode;
        }
        return null;
    }
}
