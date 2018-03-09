package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

public class LogAndExpr extends ASTNode {
    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        ASTNode node = EqExpr.parse(tr, cs);
        while (tr.peek().getValue().equals("&&")) {
            Operator temp = new Operator(tr.read());
            temp.setLhs(node);
            temp.setRhs(EqExpr.parse(tr, cs));
            node = temp;
        }
        return node;
    }
}
