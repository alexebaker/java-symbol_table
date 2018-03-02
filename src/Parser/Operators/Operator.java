package Parser.Operators;

import Parser.Nodes.ParseNode;
import Tokenizer.Token;

public class Operator extends ParseNode {
    private String op;
    private ParseNode lhs;
    private ParseNode rhs;


    public Operator() {
        this("");
    }

    public Operator(Token token) {
        this(token.getValue());
    }

    public Operator(String op) {
        this.op = op;
        this.lhs = null;
        this.rhs = null;
    }

    public ParseNode getLhs() {
        return lhs;
    }

    public ParseNode getRhs() {
        return rhs;
    }

    public void setLhs(ParseNode lhs) {
        this.lhs = lhs;
    }

    public void setRhs(ParseNode rhs) {
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");

        str.append("(");
        if (lhs != null) str.append(lhs);
        str.append(op);
        if (rhs != null) str.append(rhs);
        str.append(")");
        return str.toString();
    }
}
