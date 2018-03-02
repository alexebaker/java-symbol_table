package Parser.Nodes;

import Parser.TokenParser;
import Tokenizer.EOFToken;
import Tokenizer.TokenReader;
import Compiler.CompilerIO;

import java.util.Vector;

public class ProgramNode extends ParseNode {
    private Vector<ParseNode> exprs;
    private int numErrors;

    public ProgramNode() {
        this.exprs = new Vector<>();
        this.numErrors = 0;
    }

    public void addExpr(ParseNode expr) {
        exprs.add(expr);
    }

    public void setNumErrors(int numErrors) {
        this.numErrors = numErrors;
    }

    public int getNumErrors() {
        return numErrors;
    }

    public Vector<ParseNode> getExprs() {
        return exprs;
    }

    /**
     * Function to be called to parse a program. This will parse multiple statements.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ParseNode parse(TokenReader tr, CompilerIO io) {
        //System.out.println("Parsing Program...");
        ProgramNode pNode = new ProgramNode();
        int numErrors = 0;

        while (true) {
            if (EOFToken.isToken(tr.peek())) {
                // End of the input stream. If we got this far without an error, then the parse was good
                break;
            }

            ParseNode exprNode = ExprNode.parse(tr);
            if (exprNode != null) {
                if (tr.peek().getValue().equals(";")) {
                    tr.read();
                    pNode.addExpr(exprNode);
                    io.write(exprNode.toString());
                    continue;
                }
                System.err.println(TokenParser.getErrorMsg(tr.peek(), ";"));
                // Only here to print out a partial expression to match the test cases
                io.write(exprNode.toString());
            }
            tr.handleError();
            numErrors += 1;
        }
        pNode.setNumErrors(numErrors);
        return pNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (ParseNode expr : getExprs()) {
            str.append(expr.toString());
            str.append("\n");
        }
        return str.toString();
    }
}
