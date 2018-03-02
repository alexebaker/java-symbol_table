package Parser.Nodes;

import Parser.Operators.EqOp;
import Parser.Operators.Operator;
import Parser.TokenParser;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class EqExprNode extends ParseNode {
    private RelExprNode relExprNode;
    private EqOp eqOp;
    private EqExprNode eqExprNode;

    public EqExprNode() {
        this.relExprNode = null;
        this.eqOp = null;
        this.eqExprNode = null;
    }

    public void setEqExprNode(EqExprNode eqExprNode) {
        this.eqExprNode = eqExprNode;
    }

    public void setEqOp(EqOp eqOp) {
        this.eqOp = eqOp;
    }

    public void setRelExprNode(RelExprNode relExprNode) {
        this.relExprNode = relExprNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (relExprNode != null) {
            str.append(relExprNode);
            if (eqOp != null && eqExprNode != null) {
                str.append(eqOp);
                str.append(eqExprNode);
            }
        }
        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode node = RelExprNode.parse(tr);
        if (node != null) {
            while (EqOp.isOp(tr.peek())) {
                Operator temp = new EqOp(tr.read());
                temp.setLhs(node);
                ParseNode node1 = RelExprNode.parse(tr);
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
