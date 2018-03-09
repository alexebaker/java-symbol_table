package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Parser.Operators.TermOp;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

public class SimpleExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        ASTNode node = Term.parse(tr, cs);
        while (TermOp.isOp(tr.peek())) {
            Operator temp = new TermOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(Term.parse(tr, cs));
            node = temp;
        }
        return node;
    }
}
