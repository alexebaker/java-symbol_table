package Parser.Nodes;

import Compiler.CompilerState;
import Errors.SyntaxError;
import Tokenizer.TokenReader;

import java.util.Vector;


public class Block extends ASTNode {
    private Vector<ASTNode> defs;
    private Vector<ASTNode> stmts;

    public Block() {
        super();
        defs = new Vector<>();
        stmts = new Vector<>();
    }

    public void addDef(ASTNode def) {
        defs.add(def);
    }

    public void addStmt(ASTNode stmt) {
        stmts.add(stmt);
    }

    public Vector<ASTNode> getDefs() {
        return defs;
    }

    public Vector<ASTNode> getStmts() {
        return stmts;
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) {
        Block block = new Block();

        while (Def.beginsDef(tr.peek())) {
            try {
                block.addDef(Def.parse(tr));
            }
            catch (SyntaxError ex) {
                tr.skipToSemiColon();
                cs.addError(ex);
            }
        }

        while (Statement.beginsStmt(tr.peek())) {
            try {
                block.addStmt(Statement.parse(tr));
            }
            catch (SyntaxError ex) {
                tr.skipToClosedCurly();
                cs.addError(ex);
            }
        }
        return block;
    }

    public String getFPIFStr() {
        StringBuilder str = new StringBuilder("");
        for (ASTNode def : getDefs()) {
            if (def != null) {
                str.append(def.getFPIFStr());
            }
        }
        for (ASTNode stmt : getStmts()) {
            if (stmt != null) {
                str.append(stmt.getFPIFStr());
            }
        }
        return str.toString();
    }
}
