package Parser.Nodes;

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

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        if (expr != null) {
            str.append(expr.getFPIFStr());
        }
        else {
            for (ASTNode stmt : getStmts()) {
                str.append("{");
                str.append(stmt.getFPIFStr());
                str.append("}");
            }
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr) throws SyntaxError {
        Statement stmt = new Statement();
        if (tr.peek().equals("{")) {
            tr.read();
            stmt.addStmt(Statement.parse(tr));
            if (tr.peek().equals("}")) {
                tr.read();
            }
            else {
                throw new SyntaxError(tr.read(), "}");
            }
        }
        else {
            stmt.setExpr(Expr.parse(tr));
            if (tr.peek().equals(";")) {
                tr.read();
            }
            else {
                throw new SyntaxError(tr.read(), ";");
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
