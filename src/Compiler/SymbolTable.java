package Compiler;

import Parser.Nodes.ASTNode;
import Tokenizer.Tokens.Token;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class SymbolTable {
    private HashMap<Token, VDI> symbolTable;
    private boolean inDef;

    public SymbolTable() {
        symbolTable = new HashMap<>();
        inDef = false;
    }

    public boolean isInDef() {
        return inDef;
    }

    public void setInDef(boolean inDef) {
        this.inDef = inDef;
    }

    public void addDeclaration(Token name, ASTNode type) {
       symbolTable.put(name, new VDI(name, "unused", type));
    }

    public void setUsed(Token name) {
        if (!inDef) {
            VDI vdi;
            if (symbolTable.containsKey(name)) {
                vdi = symbolTable.get(name);
                if (vdi.getType() != null) {
                    vdi.setStatus("okay");
                }
            } else {
                addDeclaration(name, null);
                vdi = symbolTable.get(name);
                vdi.setStatus("undeclared");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        SortedSet<Token> keys = new TreeSet<>(symbolTable.keySet());
        for (Token name : keys) {
            str.append(symbolTable.get(name));
            str.append("\n");
        }
        return str.toString();
    }
}
