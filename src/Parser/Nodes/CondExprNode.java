package Parser.Nodes;

import Parser.TokenParser;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class CondExprNode extends ParseNode {
    private ParseNode logOrExprNode;
    private ParseNode exprNode;
    private ParseNode condExprNode;

    public CondExprNode() {
        logOrExprNode = null;
        exprNode = null;
        condExprNode = null;
    }

    public void setLogOrExprNode(ParseNode logOrExprNode) {
        this.logOrExprNode = logOrExprNode;
    }

    public void setCondExprNode(ParseNode condExprNode) {
        this.condExprNode = condExprNode;
    }

    public void setExprNode(ParseNode exprNode) {
        this.exprNode = exprNode;
    }

    public static ParseNode parse(TokenReader tr) {
        ParseNode logOrExprNode = LogOrExprNode.parse(tr);
        if (logOrExprNode != null) {
            Token token;
            CondExprNode condExprNode = new CondExprNode();
            condExprNode.setLogOrExprNode(logOrExprNode);
            token = tr.peek();
            if (token.getValue().equals("?")) {
                tr.read();
                ParseNode exprNode = ExprNode.parse(tr);
                if (exprNode != null) {
                    condExprNode.setExprNode(exprNode);
                    token = tr.peek();
                    if (token.getValue().equals(":")) {
                        tr.read();
                        ParseNode condExprNode1 = CondExprNode.parse(tr);
                        if (condExprNode1 != null) {
                            condExprNode.setCondExprNode(condExprNode1);
                            return condExprNode;
                        }
                    }
                    else {
                        System.err.println(TokenParser.getErrorMsg(tr.peek(), ":"));
                    }
                }
                return null;
            }
            return condExprNode;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (logOrExprNode != null) {
            if (exprNode != null && condExprNode != null) {
                str.append("(");
                str.append(logOrExprNode.toString());
                str.append("?");
                str.append(exprNode.toString());
                str.append(":");
                str.append(condExprNode.toString());
                str.append(")");
            }
            else {
                str.append(logOrExprNode);
            }
        }
        return str.toString();
    }
}
