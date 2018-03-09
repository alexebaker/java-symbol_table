package Parser.Nodes;

import Errors.SyntaxError;
import Tokenizer.TokenReader;
import Compiler.CompilerState;

import java.util.Vector;

public class TypeSpec extends ASTNode {
    private ASTNode typeName;
    private Vector<ASTNode> arraySpecs;

    public TypeSpec() {
        typeName = null;
        arraySpecs = new Vector<>();
    }

    public void addArraySpec(ASTNode arraySpec) {
        arraySpecs.add(arraySpec);
    }

    public Vector<ASTNode> getArraySpecs() {
        return arraySpecs;
    }

    public void setTypeName(ASTNode typeName) {
        this.typeName = typeName;
    }

    public ASTNode getTypeName() {
        return typeName;
    }

    @Override
    public String getASTR(int indentDepth) {
        StringBuilder str = new StringBuilder("");
        str.append(typeName.getASTR(indentDepth));
        for (ASTNode arraySpec : arraySpecs) {
            str.append(arraySpec.getASTR(indentDepth));
        }
        return str.toString();
    }

    public static ASTNode parse(TokenReader tr, CompilerState cs) throws SyntaxError {
        TypeSpec typeSpec = new TypeSpec();
        typeSpec.setTypeName(PrimType.parse(tr, cs));

        while (tr.peek().getValue().equals("[")) {
            typeSpec.addArraySpec(ArraySpec.parse(tr, cs));
        }
        return typeSpec;
    }
}
