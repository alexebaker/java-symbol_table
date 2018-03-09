package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.FactorOp;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

public class Term extends ASTNode {
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError{
        ASTNode node = Factor.parse(tr, cs);
        while (FactorOp.isOp(tr.peek())) {
            Operator temp = new FactorOp(tr.read());
            temp.setLhs(node);
            temp.setRhs(Factor.parse(tr, cs));
            node = temp;
        }
        return node;
    }
}
