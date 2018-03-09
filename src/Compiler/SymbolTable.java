package Compiler;

import Parser.Nodes.ASTNode;
import Tokenizer.Tokens.Token;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<Token, VDI> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<>();
    }

    public void addDeclaration(Token name, ASTNode type) {
       symbolTable.put(name, new VDI(name, "unused", type));
    }

    public void setUsed(Token name) {
        VDI vdi;
        if (symbolTable.containsKey(name)) {
            vdi = symbolTable.get(name);
            vdi.setStatus("okay");
        }
        else {
            addDeclaration(name, null);
            vdi = symbolTable.get(name);
            vdi.setStatus("undeclared");
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (Token name : symbolTable.keySet()) {
            str.append(symbolTable.get(name));
            str.append("\n");
        }
        return str.toString();
    }
}
