package Parser.Nodes;

import Parser.Operators.Operator;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class LogAndExprNode extends ParseNode {
    private LogAndExprNode logAndExprNode;
    private EqExprNode eqExprNode;

    public LogAndExprNode() {
        this.logAndExprNode = null;
        this.eqExprNode = null;
    }

    public void setLogAndExprNode(LogAndExprNode logAndExprNode) {
        this.logAndExprNode = logAndExprNode;
    }

    public void setEqExprNode(EqExprNode eqExprNode) {
        this.eqExprNode = eqExprNode;
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode node = EqExprNode.parse(tr);
        if (node != null) {
            while (tr.peek().getValue().equals("&&")) {
                Operator temp = new Operator(tr.read());
                temp.setLhs(node);
                ParseNode node1 = EqExprNode.parse(tr);
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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (eqExprNode != null) {
            str.append(eqExprNode.toString());
            if (logAndExprNode != null) {
                str.append("&&");
                str.append(logAndExprNode.toString());
            }
        }
        return str.toString();
    }
}
