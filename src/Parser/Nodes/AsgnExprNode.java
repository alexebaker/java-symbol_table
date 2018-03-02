package Parser.Nodes;

import Parser.TokenParser;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class AsgnExprNode extends ParseNode {
    private ParseNode asgnExprNode;
    private ParseNode condExprNode;

    public AsgnExprNode() {
        this.asgnExprNode = null;
        this.condExprNode = null;
    }

    public void setAsgnExprNode(ParseNode asgnExprNode) {
        this.asgnExprNode = asgnExprNode;
    }

    public void setCondExprNode(ParseNode condExprNode) {
        this.condExprNode = condExprNode;
    }

    public static ParseNode parse(TokenReader tr) {
        //System.out.println("Parsing Assign Expression...");
        ParseNode condExprNode = CondExprNode.parse(tr);
        if (condExprNode != null) {
            Token token;
            AsgnExprNode asgnExprNode = new AsgnExprNode();
            asgnExprNode.setCondExprNode(condExprNode);
            token = tr.peek();
            if (token.getValue().equals("=")) {
                tr.read();
                ParseNode asgnExprNode1 = AsgnExprNode.parse(tr);
                if (asgnExprNode1 != null) {
                    asgnExprNode.setAsgnExprNode(asgnExprNode1);
                }
            }
            return asgnExprNode;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (condExprNode != null) {
            if (asgnExprNode != null) {
                str.append("(");
                str.append(condExprNode.toString());
                str.append("=");
                str.append(asgnExprNode.toString());
                str.append(")");
            }
            else {
                str.append(condExprNode);
            }
        }
        return str.toString();
    }
}
