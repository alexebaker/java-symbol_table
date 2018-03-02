package Parser.Nodes;

import Parser.TokenParser;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class PrimaryExprNode extends ParseNode {
    private IdentifierNode identifierNode;
    private NumberNode numberNode;
    private ParseNode exprNode;

    public PrimaryExprNode() {
        this.identifierNode = null;
        this.numberNode = null;
        this.exprNode = null;
    }

    public void setExprNode(ParseNode exprNode) {
        this.exprNode = exprNode;
    }

    public void setIdentifierNode(IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }

    public void setNumberNode(NumberNode numberNode) {
        this.numberNode = numberNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (identifierNode != null) {
            str.append(identifierNode.toString());
        }
        else if (numberNode != null) {
            str.append(numberNode.toString());
        }
        else if (exprNode != null) {
            //str.append("(");
            str.append(exprNode.toString());
            //str.append(")");
        }
        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        Token token = tr.peek();
        PrimaryExprNode primaryExprNode = new PrimaryExprNode();
        if (IdentifierNode.isID(token)) {
            primaryExprNode.setIdentifierNode(new IdentifierNode(tr.read()));
            return primaryExprNode;
        }
        else if (NumberNode.isNum(token)) {
            primaryExprNode.setNumberNode(new NumberNode(tr.read()));
            return primaryExprNode;
        }
        else if (token.getValue().equals("(")) {
            tr.read();
            ParseNode exprNode = ExprNode.parse(tr);
            if (exprNode != null && tr.peek().getValue().equals(")")) {
                tr.read();
                primaryExprNode.setExprNode(exprNode);
                return primaryExprNode;
            }
            else {
                System.err.println(TokenParser.getErrorMsg(tr.peek(), ")"));
            }
        }
        //System.err.println(TokenParser.getErrorMsg(tr.peek(), "IDENTIFIER, NUMBER, or ("));
        return null;
    }
}
