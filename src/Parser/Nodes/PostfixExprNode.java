package Parser.Nodes;

import Parser.Operators.Operator;
import Parser.Operators.PostunOp;
import Tokenizer.TokenReader;

public class PostfixExprNode extends ParseNode {
    private ParseNode primaryExprNode;
    private ParseNode postfixExprNode;
    private Operator postunOp;
    private ParseNode arraySpecNode;

    public PostfixExprNode() {
        this.primaryExprNode = null;
        this.postfixExprNode = null;
        this.postunOp = null;
        this.arraySpecNode = null;
    }

    public void setPostfixExprNode(ParseNode postfixExprNode) {
        this.postfixExprNode = postfixExprNode;
    }

    public void setArraySpecNode(ParseNode arraySpecNode) {
        this.arraySpecNode = arraySpecNode;
    }

    public void setPostunOp(Operator postunOp) {
        this.postunOp = postunOp;
    }

    public void setPrimaryExprNode(ParseNode primaryExprNode) {
        this.primaryExprNode = primaryExprNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (primaryExprNode != null) {
            str.append("(");
            str.append(primaryExprNode);

            if (arraySpecNode != null) {
                str.append(arraySpecNode);
            }

            if (postfixExprNode != null) {
                str.append(postfixExprNode);
            }
            str.append(")");
        }

        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode node = PrimaryExprNode.parse(tr);
        if (node != null) {
            while (PostunOp.isOp(tr.peek()) || tr.peek().getValue().equals("[")) {
                if (PostunOp.isOp(tr.peek())) {
                    Operator temp = new PostunOp(tr.read());
                    temp.setLhs(node);
                    temp.setRhs(PostfixExprNode.parse(tr));
                    node = temp;
                }
                else {
                    ParseNode temp = new PostfixExprNode();
                    ((PostfixExprNode) temp).setPrimaryExprNode(node);
                    ((PostfixExprNode) temp).setArraySpecNode(ArraySpecNode.parse(tr));
                    ((PostfixExprNode) temp).setPostfixExprNode(PostfixExprNode.parse(tr));
                    node = temp;
                }
            }
            return node;
        }
        return null;
    }

}
