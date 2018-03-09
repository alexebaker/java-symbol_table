package Compiler;

import Parser.Nodes.ASTNode;
import Tokenizer.Tokens.Token;

public class VDI {
    private Token name;
    private String status;
    private ASTNode type;

    public VDI(Token name, String status, ASTNode type) {
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public Token getName() {
        return name;
    }

    public ASTNode getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append(name);
        str.append(" ");
        str.append(status);
        str.append(" ");
        str.append(type.getASTR(0));
        return str.toString();
    }
}
