package Parser.Nodes;

import Parser.TokenParser;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class ArraySpecNode extends ParseNode {
    private ParseNode optArraySizeNode;

    public ArraySpecNode() {
        this.optArraySizeNode = null;
    }

    public void setOptArraySizeNode(ParseNode optArraySizeNode) {
        this.optArraySizeNode = optArraySizeNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append("[");
        if (optArraySizeNode != null) {
            str.append(optArraySizeNode.toString());
        }
        str.append("]");
        return str.toString();
    }

    public static ParseNode parse(TokenReader tr) {
        Token token = tr.peek();
        if (token.getValue().equals("[")) {
            tr.read();
            ArraySpecNode arraySpecNode = new ArraySpecNode();
            ParseNode optArraySizeNode = OptArraySizeNode.parse(tr);
            if (optArraySizeNode != null) {
                arraySpecNode.setOptArraySizeNode(optArraySizeNode);
                token = tr.peek();
                if (token.getValue().equals("]")) {
                    tr.read();
                    return arraySpecNode;
                }
                else {
                    System.err.println(TokenParser.getErrorMsg(tr.peek(), "]"));
                }
            }
        }
        else {
            System.err.println(TokenParser.getErrorMsg(tr.peek(), "["));
        }
        return null;
    }
}
