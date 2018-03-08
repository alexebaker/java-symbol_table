package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Tokenizer.Tokens.IdentifierToken;
import Tokenizer.Tokens.Token;
import Compiler.CompilerState;

import java.util.Vector;

public class Def extends ASTNode {
    private ASTNode typeSpec;
    private Vector<ASTNode> varNames;

    public Def() {
        super();
        typeSpec = null;
        varNames = new Vector<>();
    }

    public void setTypeSpec(ASTNode typeSpec) {
        this.typeSpec = typeSpec;
    }

    public ASTNode getTypeSpec() {
        return typeSpec;
    }

    public void addVarName(ASTNode varName) {
        varNames.add(varName);
    }

    public Vector<ASTNode> getVarNames() {
        return varNames;
    }

    @Override
    public String getASTR() {
        return getTypeSpec().getASTR();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append(getTypeSpec());
        str.append(getVarNames().get(0));
        for (int idx = 1; idx < getVarNames().size(); idx++) {
            str.append(",");
            str.append(getVarNames().get(idx));
        }
        str.append(";");
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        Def def = new Def();
        def.setTypeSpec(TypeSpec.parse(tr, cs));

        if (!IdentifierToken.isToken(tr.peek())) {
            throw new SyntaxError(tr.read(), "IDENTIFIER");
        }

        while (IdentifierToken.isToken(tr.peek())) {
            def.addVarName(Identifier.parse(tr, cs));
            if (tr.peek().getValue().equals(",")) {
                tr.read();
            }
            else {
                break;
            }
        }

        if (tr.peek().getValue().equals(";")) {
            tr.read();
        }
        else {
            throw new SyntaxError(tr.read(), ";");
        }
        return def;
    }

    public static boolean beginsDef(Token token) {
        return Def.beginsDef(token.getValue());
    }

    public static boolean beginsDef(String str) {
        return PrimType.isType(str);
    }
}
