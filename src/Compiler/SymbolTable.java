package Compiler;

import Tokenizer.Tokens.Token;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<Token, SymbolInfo> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<>();
    }
}
