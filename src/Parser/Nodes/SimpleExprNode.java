package Parser.Nodes;

import Parser.Operators.Operator;
import Parser.Operators.TermOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class SimpleExprNode extends ParseNode {
    private TermNode termNode;
    private TermOp termOp;
    private SimpleExprNode simpleExprNode;

    public SimpleExprNode() {
        this.termNode = null;
        this.termOp = null;
        this.simpleExprNode = null;
    }

    public void setSimpleExprNode(SimpleExprNode simpleExprNode) {
        this.simpleExprNode = simpleExprNode;
    }

    public void setTermNode(TermNode termNode) {
        this.termNode = termNode;
    }

    public void setTermOp(TermOp termOp) {
        this.termOp = termOp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (termNode != null) {
            if (termOp != null && simpleExprNode != null) {
                str.append("(");
                str.append(termNode);
                str.append(termOp);
                str.append(simpleExprNode);
                str.append(")");
            }
            else {
                str.append(termNode);
            }
        }
        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode node = TermNode.parse(tr);
        if (node != null) {
            while (TermOp.isOp(tr.peek())) {
                Operator temp = new TermOp(tr.read());
                temp.setLhs(node);
                ParseNode node1 = TermNode.parse(tr);
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
