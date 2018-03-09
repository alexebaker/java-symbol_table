package Parser.Nodes;

import Compiler.CompilerState;
import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Compiler.SymbolTable;

import java.util.Vector;


public class Block extends ASTNode {
    private Vector<ASTNode> defs;
    private Vector<ASTNode> stmts;
    private SymbolTable symbolTable;

    public Block() {
        super();
        defs = new Vector<>();
        stmts = new Vector<>();
        symbolTable = new SymbolTable();
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

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public String getVSR() {
        StringBuilder str = new StringBuilder("");
        str.append(symbolTable);
        str.append("\n");
        return str.toString();
    }

    @Override
    public String getASTR(int indentDepth) {
        StringBuilder str = new StringBuilder("");
        for (ASTNode stmt : stmts) {
            if (stmt != null) str.append(stmt.getASTR(indentDepth));
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (ASTNode def : defs) {
            if (def != null) str.append(def);
        }
        for (ASTNode stmt : stmts) {
            if (stmt != null) str.append(stmt);
        }
        return str.toString();
    }

    public static ASTNode parse(CompilerState cs, SymbolTable st) {
        TokenReader tr = cs.getTr();
        Block block = new Block();

        block.getSymbolTable().setInDef(true);
        while (Def.beginsDef(tr.peek())) {
            try {
                block.addDef(Def.parse(cs, block.getSymbolTable()));
            }
            catch (SyntaxError ex) {
                tr.skipToSemiColon();
                cs.addError(ex);
            }
        }

        block.getSymbolTable().setInDef(false);
        while (Statement.beginsStmt(tr.peek())) {
            try {
                block.addStmt(Statement.parse(cs, block.getSymbolTable()));
            }
            catch (SyntaxError ex) {
                tr.skipToClosedCurly();
                cs.addError(ex);
            }
        }
        return block;
    }
}
