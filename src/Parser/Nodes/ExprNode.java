package Parser.Nodes;

import Parser.Operators.Operator;
import Tokenizer.TokenReader;

import java.util.Vector;

public class ExprNode extends ParseNode {
    private Vector<ParseNode> asgnExprs;

    public ExprNode() {
        this.asgnExprs = new Vector<>();
    }

    public void addAsgnExpr(ParseNode asgnExpr) {
        asgnExprs.add(asgnExpr);
    }

    public Vector<ParseNode> getAsgnExprs() {
        return asgnExprs;
    }

    /**
     * Function to be called to parse an Expression as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ParseNode parse(TokenReader tr) {
        //System.out.println("Parsing Expression...");
        ParseNode node = AsgnExprNode.parse(tr);
        if (node != null) {
            while (tr.peek().getValue().equals(",")) {
                Operator temp = new Operator(tr.read());
                temp.setLhs(node);
                ParseNode node1 = AsgnExprNode.parse(tr);
                if (node1 != null) {
                    temp.setRhs(node1);
                    node = temp;
                }
                else {
                    return null;
                }
            }
            return node;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (ParseNode asgnExpr : getAsgnExprs()) {
            str.append(asgnExpr.toString());
        }
        return str.toString();
    }
}
