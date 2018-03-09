package Parser.Nodes;

import Compiler.CompilerState;
import Errors.SyntaxError;
import Parser.Operators.PreunOp;
import Tokenizer.TokenReader;
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

    @Override
    public String getASTR(int indentDepth) {
        StringBuilder str = new StringBuilder("");
        String indentStr = super.getASTR(indentDepth);
        if (expr != null) {
            str.append(indentStr);
            str.append(expr.getASTR(indentDepth));
            str.append(";\n");
        }
        else {
            str.append(indentStr);
            str.append("{\n");
            for (ASTNode stmt : stmts) {
                str.append(stmt.getASTR(indentDepth+1));
            }
            str.append(indentStr);
            str.append("}\n");
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        Statement stmt = new Statement();
        if (tr.peek().getValue().equals("{")) {
            tr.read();

            if (!tr.peek().getValue().equals("}")) {
                while (Statement.beginsStmt(tr.peek())) {
                    stmt.addStmt(Statement.parse(tr, cs));
                }
            }

            if (tr.peek().getValue().equals("}")) {
                tr.read();
            }
            else {
                throw new SyntaxError(tr.read(), "}");
            }
        }
        else {
            try {
                stmt.setExpr(Expr.parse(tr, cs));
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
        return PrimaryExpr.beginsPrimaryExpr(str) || str.equals("{") || PreunOp.isOp(str);
    }
}
