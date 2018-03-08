package Parser.Nodes;

import Compiler.CompilerState;
import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Tokenizer.Tokens.IdentifierToken;
import Tokenizer.Tokens.NumberToken;
import Tokenizer.Tokens.Token;

import java.util.Vector;

public class Statement extends ASTNode {
    private ASTNode expr;
    private Vector<ASTNode> stmts;

    public Statement() {
        super();
        expr = null;
        stmts = new Vector<>();
    }

    public void setExpr(ASTNode expr) {
        this.expr = expr;
    }

    public ASTNode getExpr() {
        return expr;
    }

    public void addStmt(ASTNode stmt) {
        stmts.add(stmt);
    }

    public Vector<ASTNode> getStmts() {
        return stmts;
    }

    public String getASTR() {
        StringBuilder str = new StringBuilder("");
        if (expr != null) {
            str.append(expr.getASTR());
        }
        else {
            for (ASTNode stmt : getStmts()) {
                str.append("{");
                str.append(stmt.getASTR());
                str.append("}");
            }
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        Statement stmt = new Statement();
        if (tr.peek().equals("{")) {
            tr.read();
            stmt.addStmt(Statement.parse(tr, cs));
            if (tr.peek().equals("}")) {
                tr.read();
            }
            else {
                throw new SyntaxError(tr.read(), "}");
            }
        }
        else {
            try {
                stmt.setExpr(Expr.parse(tr));
                if (tr.peek().getValue().equals(";")) {
                    tr.read();
                }
                else {
                    cs.addError(new SyntaxError(tr.read(), ";"));
                    tr.skipToSemiColon();
                }
            }
            catch (SyntaxError ex) {
                cs.addError(ex);
                tr.skipToSemiColon();
            }
        }
        return stmt;
    }

    public static boolean beginsStmt(Token token) {
        return Statement.beginsStmt(token.getValue());
    }

    public static boolean beginsStmt(String str) {
        return IdentifierToken.isToken(str) || NumberToken.isToken(str) || str.equals("(") || str.equals("{");
    }
}
