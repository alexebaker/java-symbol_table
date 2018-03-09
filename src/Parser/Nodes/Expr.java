package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;
import Compiler.CompilerState;


public class Expr extends ASTNode {
    /**
     * Function to be called to parse an Expression as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        ASTNode node = AsgnExpr.parse(tr, cs);
        while (tr.peek().getValue().equals(",")) {
            Operator temp = new Operator(tr.read());
            temp.setLhs(node);
            temp.setRhs(AsgnExpr.parse(tr, cs));
            node = temp;
        }
        return node;
    }
}
