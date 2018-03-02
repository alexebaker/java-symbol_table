package Parser.Nodes;

import Parser.Operators.FactorOp;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;

public class TermNode extends ParseNode {
    private TermNode termNode;
    private FactorOp factorOp;
    private FactorNode factorNode;

    public TermNode() {
        this.termNode = null;
        this.factorOp = null;
        this.factorNode = null;
    }

    public void setTermNode(TermNode termNode) {
        this.termNode = termNode;
    }

    public void setFactorNode(FactorNode factorNode) {
        this.factorNode = factorNode;
    }

    public void setFactorOp(FactorOp factorOp) {
        this.factorOp = factorOp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (factorNode != null) {
            if (factorOp != null && termNode != null) {
                str.append("(");
                str.append(factorNode.toString());
                str.append(factorOp.toString());
                str.append(termNode.toString());
                str.append(")");
            }
            else {
                str.append(factorNode);
            }
        }
        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode node = FactorNode.parse(tr);
        if (node != null) {
            while (FactorOp.isOp(tr.peek())) {
                Operator temp = new FactorOp(tr.read());
                temp.setLhs(node);
                ParseNode node1 = FactorNode.parse(tr);
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
