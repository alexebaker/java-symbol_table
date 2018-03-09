package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;
import Compiler.CompilerState;
import Compiler.SymbolTable;


public class Expr extends ASTNode {
    /**
     * Function to be called to parse an Expression as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ASTNode parse(CompilerState cs, SymbolTable st) throws SyntaxError {
        TokenReader tr = cs.getTr();
        ASTNode node = AsgnExpr.parse(cs, st);
        while (tr.peek().getValue().equals(",")) {
            Operator temp = new Operator(tr.read());
            temp.setLhs(node);
            temp.setRhs(AsgnExpr.parse(cs, st));
            node = temp;
        }
        return node;
    }
}
