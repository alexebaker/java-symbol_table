package Parser.Nodes;

import Parser.Operators.Operator;
import Parser.Operators.RelOp;
import Tokenizer.TokenReader;

public class RelExprNode extends ParseNode {
    private RelExprNode relExprNode;
    private RelOp relOp;
    private SimpleExprNode simpleExprNode;

    public RelExprNode() {
        this.relExprNode = null;
        this.relOp = null;
        this.simpleExprNode = null;
    }

    public void setRelExprNode(RelExprNode relExprNode) {
        this.relExprNode = relExprNode;
    }

    public void setRelOp(RelOp relOp) {
        this.relOp = relOp;
    }

    public void setSimpleExprNode(SimpleExprNode simpleExprNode) {
        this.simpleExprNode = simpleExprNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (simpleExprNode != null) {
            str.append(simpleExprNode);
            if (relOp != null && relExprNode != null) {
                str.append(relOp);
                str.append(relExprNode);
            }
        }
        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode node = SimpleExprNode.parse(tr);
        if (node != null) {
            while (RelOp.isOp(tr.peek())) {
                Operator temp = new RelOp(tr.read());
                temp.setLhs(node);
                ParseNode node1 = SimpleExprNode.parse(tr);
                if (node1 != null) {
                    temp.setRhs(node1);
                    node = temp;
                    continue;
                }
                return null;
            }
            return node;
        }
        return null;
    }
}
