package Parser.Nodes;

import Errors.SyntaxError;
import Parser.Operators.Operator;
import Tokenizer.TokenReader;
import Compiler.CompilerState;
import Compiler.SymbolTable;

public class LogOrExpr extends ASTNode {
    public static ASTNode parse(CompilerState cs, SymbolTable st) throws SyntaxError {
        TokenReader tr = cs.getTr();
        ASTNode node = LogAndExpr.parse(cs, st);
        while (tr.peek().getValue().equals("||")) {
            Operator temp = new Operator(tr.read());
            temp.setLhs(node);
            temp.setRhs(LogAndExpr.parse(cs, st));
            node = temp;
        }
        return node;
    }
}
