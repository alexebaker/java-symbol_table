package Parser.Operators;

import Parser.Nodes.ASTNode;
import Tokenizer.Tokens.Token;

public class Operator extends ASTNode {
    private Token op;
    private ASTNode lhs;
    private ASTNode rhs;

    public Operator(Token op) {
        this.op = op;
        this.lhs = null;
        this.rhs = null;
    }

    public ASTNode getLhs() {
        return lhs;
    }

    public ASTNode getRhs() {
        return rhs;
    }

    public void setLhs(ASTNode lhs) {
        this.lhs = lhs;
    }

    public void setRhs(ASTNode rhs) {
        this.rhs = rhs;
    }

    public String getASTR() {
        StringBuilder str = new StringBuilder("");

        str.append("(");
        if (lhs != null) str.append(lhs.getASTR());
        str.append(op.getValue());
        if (rhs != null) str.append(rhs.getASTR());
        str.append(")");
        return str.toString();
    }
}
