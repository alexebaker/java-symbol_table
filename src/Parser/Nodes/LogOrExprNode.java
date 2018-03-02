package Parser.Nodes;

import Parser.Operators.Operator;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class LogOrExprNode extends ParseNode {
    private LogAndExprNode logAndExprNode;
    private LogOrExprNode logOrExprNode;

    public LogOrExprNode() {
        this.logAndExprNode = null;
        this.logOrExprNode = null;
    }

    public void setLogAndExprNode(LogAndExprNode logAndExprNode) {
        this.logAndExprNode = logAndExprNode;
    }

    public void setLogOrExprNode(LogOrExprNode logOrExprNode) {
        this.logOrExprNode = logOrExprNode;
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode node = LogAndExprNode.parse(tr);
        if (node != null) {
            while (tr.peek().getValue().equals("||")) {
                Operator temp = new Operator(tr.read());
                temp.setLhs(node);
                ParseNode node1 = LogAndExprNode.parse(tr);
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
        if (logAndExprNode != null) {
            str.append(logAndExprNode.toString());
            if (logOrExprNode != null) {
                str.append("||");
                str.append(logOrExprNode.toString());
            }
        }
        return str.toString();
    }
}
