package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;


public class Expr extends ASTNode {
    /**
     * Function to be called to parse an Expression as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        ASTNode node = AsgnExpr.parse(tr);
        while (tr.peek().getValue().equals(",")) {
            Operator temp = new Operator(tr.read());
            temp.setLhs(node);
            temp.setRhs(AsgnExpr.parse(tr));
            node = temp;
        }
        return node;
    }
}
